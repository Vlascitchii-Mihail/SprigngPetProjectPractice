package com.pet.learn_spring;

import com.pet.learn_spring.core.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

@ShellComponent
public class FsCommands {

    private final FileSystem fileSystem;
    @Autowired
    public FsCommands(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @ShellMethod("Display required disk space")
    public long minimumFreeSpace() {
        return 1_000_000L;
    }

    @ShellMethod("Convert to lowercase string")
    public String toLowercase(String input) {
        return input.toLowerCase();
    }

    @ShellMethod("Display free disk space in GB")
    public String freeDiskSpace() {
        return DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toGigabytes() + " GB";
    }
}
