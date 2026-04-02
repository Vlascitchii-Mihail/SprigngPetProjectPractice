package com.pet.learn_spring.core.photo;

import com.pet.learn_spring.core.FileSystem;
import com.pet.learn_spring.core.event.NewPhotoEvent;
import com.pet.learn_spring.core.photo.thumbnail.Thumbnail;
import com.pet.learn_spring.core.photo.thumbnail.ThumbnailRendering;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {

    private final FileSystem fileSystem;
    private final Thumbnail thumbnail;
    @Autowired
    private ApplicationEventPublisher publisher;

    public PhotoService(
            @NotNull FileSystem fileSystem,
            @ThumbnailRendering(ThumbnailRendering.RenderQuality.FAST)
            Thumbnail thumbnail
    ) {
        this.fileSystem = fileSystem;
        this.thumbnail = thumbnail;
    }

    public Optional<byte[]> download(String name) {
        try {
            return Optional.of(fileSystem.load(name + ".jpg"));
        } catch(UncheckedIOException ex) {
            return Optional.empty();
        }
    }

    public String upload(byte[] imageBytes) {
        String imageName = UUID.randomUUID().toString();

        fileSystem.store(imageName + ".jpg", imageBytes);
        byte[] thumbnailBytes = thumbnail.thumbnail(imageBytes);
        fileSystem.store(imageName + "-thumb.jpg", thumbnailBytes);

        sendPhotoEvent(imageName, OffsetDateTime.now());

        return imageName;
    }

    private void sendPhotoEvent(String imageName, OffsetDateTime dateTime) {
        NewPhotoEvent newPhotoEvent = new NewPhotoEvent(imageName, dateTime);
        PayloadApplicationEvent<NewPhotoEvent> event = new PayloadApplicationEvent<>(
                this,
                newPhotoEvent
        );

        publisher.publishEvent(event);
    }
}
