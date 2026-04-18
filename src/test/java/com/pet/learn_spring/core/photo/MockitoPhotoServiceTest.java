package com.pet.learn_spring.core.photo;

import com.pet.learn_spring.core.FileSystem;
import com.pet.learn_spring.core.photo.thumbnail.AwtBicubicThumbnail;
import com.pet.learn_spring.core.photo.thumbnail.Thumbnail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MockitoPhotoServiceTest {
//    FileSystem fileSystem = mock(FileSystem.class);
//    Thumbnail awtBicubicThumbnail = Mockito.spy(new AwtBicubicThumbnail());
//    PhotoService photoService = new PhotoService(fileSystem, awtBicubicThumbnail);

    //OR

    @Mock
    FileSystem fileSystem;

    @Spy
    Thumbnail awtBicubicThumbnail = new AwtBicubicThumbnail();

    @InjectMocks
    PhotoService photoService;

    private static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
            "/9j/4AAQSkZJRgABAQEASABIAAD"
                    + "/2wBDAP////////////////////////////////////////////////////////////"
                    + "//////////////////////////wgALCAABAAEBAREA/8QAFBABAAAAAAAAAAAAAAAAA"
                    + "AAAAP/aAAgBAQABPxA=");
    private static final Logger logger = LoggerFactory.getLogger(MockitoPhotoServiceTest.class);

    @BeforeEach
    void setup() {
        given(fileSystem.getFreeDiskSpace()).willReturn(1L);
        given(fileSystem.load(anyString())).willReturn(MINIMAL_JPG);
        stubData();
    }

    private void stubData() {
        logger.info("fileSystem.getFreeDiskSpace() = {} \n fileSystem.load(\"\") = {}",
                fileSystem.getFreeDiskSpace(), fileSystem.load(anyString()));
    }

    @Test
    void successful_photo_upload() {
        String testImageName = photoService.upload(MINIMAL_JPG);

        assertThat(testImageName).isNotNull();

        verify(fileSystem, times(2)).store(anyString(), any(byte[].class));
        verify(awtBicubicThumbnail).thumbnail(any(byte[].class));
    }
}
