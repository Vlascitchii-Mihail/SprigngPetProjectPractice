package com.pet.learn_spring.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com.custom-value")
public class CustomConfigurationProperty {

    // the property name must be equal to
    // the application.properties file property name
    private int trees = 0;
    private long filesystemMinimumFreeDiskSpace = 0;

    public void setTrees(int trees) {
        this.trees = trees;
    }

    public int getTrees() { return trees; }

    public long getFilesystemMinimumFreeDiskSpace() {
        return filesystemMinimumFreeDiskSpace;
    }

    public void setFilesystemMinimumFreeDiskSpace(long newDiskSpace) {
        filesystemMinimumFreeDiskSpace = newDiskSpace;
    }
}
