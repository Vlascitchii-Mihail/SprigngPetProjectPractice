package com.pet.learn_spring.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public record Server(String ip) {}
    private List<Server> servers = new ArrayList<>();
    private Map<String, Server> serverMap = new HashMap<>();

    public void setFilesystemMinimumFreeDiskSpace(long newDiskSpace) {
        filesystemMinimumFreeDiskSpace = newDiskSpace;
    }

    public List<Server> getServers() {
        return  servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public Map<String, Server> getServerMap() {
        return serverMap;
    }

    public void setServerMap(Map<String, Server> servers) {
        this.serverMap = servers;
    }
}
