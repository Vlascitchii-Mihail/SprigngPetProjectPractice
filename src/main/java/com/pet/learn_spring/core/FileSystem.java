package com.pet.learn_spring.core;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileSystem {

    private final Path root = Paths.get(System.getProperty("user.home"))
            .resolve("SpringDirectory");

    public FileSystem() {
        if (!Files.isDirectory(root)) {
            try {
                Files.createDirectory(root);
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Path resolvePath(String fileName) {
        Path path = root.resolve(fileName).toAbsolutePath().normalize();

        if (!path.startsWith(root)) {
            throw new SecurityException("Access to " + path + " denied" );
        }
        return path;
    }

    public long getFreeDiskSpace() { return root.toFile().getFreeSpace(); }

    public byte[] load(String fileName) {
        try {
            Path path = resolvePath(fileName);
            return Files.readAllBytes(path);
        }catch(IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public void store(String fileName, byte[] bytes) {
        try {
            Files.write(resolvePath(fileName), bytes);
        } catch(IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
