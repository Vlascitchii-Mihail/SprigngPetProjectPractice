package com.pet.learn_spring.core.configuration.yaml_config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlCustomValue {
    private int yamlTrees;
    private int yamlFilesystemMinimumFreeDiskSpace;

    public record Server(String ip) {}
    private List<Server> servers = new ArrayList<>();
    private Map<String, Server> serversMap = new HashMap<>();

    public int getYamlTrees() { return yamlTrees; }
    public int getYamlFilesystemMinimumFreeDiskSpace() {
        return yamlFilesystemMinimumFreeDiskSpace;
    }

    public void setYamlTrees(int yamlTrees) { this.yamlTrees = yamlTrees; }
    public void setYamlFilesystemMinimumFreeDiskSpace(int yamlFilesystemMinimumFreeDiskSpace) {
        this.yamlFilesystemMinimumFreeDiskSpace = yamlFilesystemMinimumFreeDiskSpace;
    }

    public List<Server> getServers() {
        return  servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

    public Map<String, Server> getServersMap() {
        return  serversMap;
    }

    public void setServersMap(Map<String, Server> servers) {
        this.serversMap = servers;
    }
}
