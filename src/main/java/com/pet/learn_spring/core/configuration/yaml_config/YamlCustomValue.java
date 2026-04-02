package com.pet.learn_spring.core.configuration.yaml_config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlCustomValue {
    private int yamlTrees;
    private int yamlFilesystemMinimumFreeDiskSpace;

    public record Server(String ip) {}
    private List<Server> serversList = new ArrayList<>();
    private Map<String, Server> serversMap = new HashMap<>();

    public int getYamlTrees() { return yamlTrees; }
    public int getYamlFilesystemMinimumFreeDiskSpace() {
        return yamlFilesystemMinimumFreeDiskSpace;
    }

    public void setYamlTrees(int yamlTrees) { this.yamlTrees = yamlTrees; }
    public void setYamlFilesystemMinimumFreeDiskSpace(int yamlFilesystemMinimumFreeDiskSpace) {
        this.yamlFilesystemMinimumFreeDiskSpace = yamlFilesystemMinimumFreeDiskSpace;
    }

    public List<Server> getServersList() {
        return serversList;
    }

    public void setServersList(List<Server> serversList) {
        this.serversList = serversList;
    }

    public Map<String, Server> getServersMap() {
        return  serversMap;
    }

    public void setServersMap(Map<String, Server> servers) {
        this.serversMap = servers;
    }
}
