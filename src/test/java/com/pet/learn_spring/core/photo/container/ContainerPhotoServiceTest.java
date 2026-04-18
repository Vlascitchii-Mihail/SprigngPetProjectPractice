package com.pet.learn_spring.core.photo.container;

import com.pet.learn_spring.core.configuration.CustomConfigurationProperty;
import com.pet.learn_spring.core.configuration.yaml_config.YamlConfigurationProperties;
import com.pet.learn_spring.core.photo.PhotoService;
import com.pet.learn_spring.core.photo.thumbnail.AwtBicubicThumbnail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.pet.learn_spring.core.photo.container.DummyFileSystem.MINIMAL_JPG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(
        classes = {
                DummyFileSystem.class,
                PhotoService.class,
        },
        properties = {
                "spring.shell.interactive.enabled=false",
        }
)
@ActiveProfiles("test2")
@EnableConfigurationProperties({
        CustomConfigurationProperty.class,
        YamlConfigurationProperties.class
})
class ContainerPhotoServiceTest {

    @Autowired
    DummyFileSystem fileSystem;
    @Autowired
    YamlConfigurationProperties yamlConfigurationProperties;

    PhotoService photoService;

    @Value("${com.custom-value.filesystem-minimum-free-disk-space}")
    private int minimumFreeDiskSpace;
    @Value("${com.yaml-custom-value.yaml-trees}")
    private int treesYaml;

    @Autowired
    private CustomConfigurationProperty customConfigurationProperty;

    private void printPropertyValues() {
        System.out.println("Minimum free disk space is " + minimumFreeDiskSpace);
        System.out.println("Yaml file = " + treesYaml);

        System.out.println("App properties trees = " + customConfigurationProperty.getTrees());
        System.out.println("Yaml properties yaml-tree = "
                + yamlConfigurationProperties.getYamlCustomValue().getYamlTrees() + "\n");
    }

    @BeforeEach
    void setup() {
        photoService = new PhotoService(fileSystem, new AwtBicubicThumbnail());
        printPropertyValues();
    }

    @Test
    void upload_successful_photo() {
        String imageName = photoService.upload(MINIMAL_JPG);

        assertThat(imageName).isNotEmpty();
    }

    @Test
    void download_returns_image() {
        String testImageName = "Test name";
        Optional<byte[]> expectedDownloadResult = Optional.of(MINIMAL_JPG);

        Optional<byte[]> actualDownloadResult = photoService.download(testImageName);

        assertThat(actualDownloadResult).isEqualTo(expectedDownloadResult);
    }
}
