package com.pet.learn_spring.core.photo;

import com.pet.learn_spring.core.FileSystem;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.UncheckedIOException;
import java.util.Optional;

@Service
public class PhotoService {

    private final FileSystem fileSystem;

    public PhotoService(@NotNull FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public Optional<byte[]> download(String name) {
        try {
            return Optional.of(fileSystem.load(name + ".jpg"));
        } catch(UncheckedIOException ex) {
            return Optional.empty();
        }
    }
}
