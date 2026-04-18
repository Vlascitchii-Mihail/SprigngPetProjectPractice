package com.pet.learn_spring.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FileSystemTest {
    FileSystem fileSystem;

    @BeforeEach
    public void init() {
        fileSystem = new FileSystem();
    }

    @Test
    @DisplayName("free disk space has to be positive")
    public void free_disk_space_has_to_be_positive() {
        long actual = fileSystem.getFreeDiskSpace();

        assertThat(actual).isGreaterThan(0);
    }

    @Test
    public void store_and_load_successfully() {
        String fileName = "test.txt";
        byte[] expectedResult = "Hello World!".getBytes();

        fileSystem.store(fileName, expectedResult);
        byte[] actual = fileSystem.load(fileName);

        System.out.println(Arrays.toString(actual));

        assertThat(actual).isEqualTo(expectedResult);
    }

    @Test
    public void load_unknown_file_throws_exception() {
        assertThatThrownBy( () -> {
            fileSystem.load(UUID.randomUUID().toString());
        }).isInstanceOf(UncheckedIOException.class);
    }

    @Test
    public void load_arbitrary_file() {
        assertThatThrownBy( () -> {
            fileSystem.load("../../../../../../../../../../Windows/notepad.exe");
        }).isInstanceOf(SecurityException.class)
                .hasMessageContaining("Access to")
                .hasMessageContaining("denied");
    }
}