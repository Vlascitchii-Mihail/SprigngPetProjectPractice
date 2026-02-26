package com.pet.learn_spring.core.configuration.yaml_config;

public class YamlCustomValue {
    private int yamlTrees;
    private int yamlFilesystemMinimumFreeDiskSpace;

    public int getYamlTrees() { return yamlTrees; }
    public int getYamlFilesystemMinimumFreeDiskSpace() {
        return yamlFilesystemMinimumFreeDiskSpace;
    }

    public void setYamlTrees(int yamlTrees) { this.yamlTrees = yamlTrees; }
    public void setYamlFilesystemMinimumFreeDiskSpace(int yamlFilesystemMinimumFreeDiskSpace) {
        this.yamlFilesystemMinimumFreeDiskSpace = yamlFilesystemMinimumFreeDiskSpace;
    }
}
