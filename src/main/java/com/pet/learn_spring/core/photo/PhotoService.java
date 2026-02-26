package com.pet.learn_spring.core.photo;

import com.pet.learn_spring.core.FileSystem;
import com.pet.learn_spring.core.photo.thumbnail.Thumbnail;
import com.pet.learn_spring.core.photo.thumbnail.ThumbnailRendering;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PhotoService {

    private final FileSystem fileSystem;
    private final Thumbnail thumbnail;

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

        return imageName;
    }
}
