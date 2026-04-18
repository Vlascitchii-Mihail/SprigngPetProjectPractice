package com.pet.learn_spring.core.photo;

import com.pet.learn_spring.core.FileSystem;
import com.pet.learn_spring.core.photo.thumbnail.AwtBicubicThumbnail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DummyPhotoServiceTest {
    FileSystem fileSystem;
    PhotoService photoService;
    private static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD"
                    + "/2wBDAP////////////////////////////////////////////////////////////"
                    + "//////////////////////////wgALCAABAAEBAREA/8QAFBABAAAAAAAAAAAAAAAAA"
                    + "AAAAP/aAAgBAQABPxA=");

    private static class DummyFileSystem extends FileSystem {
        @Override public long getFreeDiskSpace() { return 1; }
        @Override public byte[] load(String fileName) { return MINIMAL_JPG; }
        @Override public void store(String fileName, byte[] data) {}
    }

    @BeforeEach
    void setup() {
        fileSystem = new DummyFileSystem();
        photoService = new PhotoService(fileSystem, new AwtBicubicThumbnail());
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