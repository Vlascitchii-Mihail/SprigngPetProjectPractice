package com.pet.learn_spring.spring_shell;

import com.pet.learn_spring.core.FileSystem;
import com.pet.learn_spring.core.configuration.CustomConfigurationProperty;
import com.pet.learn_spring.core.configuration.CustomRecordProperty;
import com.pet.learn_spring.core.configuration.yaml_config.YamlConfigurationProperties;
import com.pet.learn_spring.core.configuration.yaml_config.YamlCustomValue;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

@ShellComponent
public class FsCommands {
    private final FileSystem fileSystem;
    private final Environment environment;
    private final CustomConfigurationProperty customConfigurationProperty;
    private final CustomRecordProperty customRecordProperty;
    private final YamlConfigurationProperties yamlConfigurationProperties;

    public FsCommands(
            FileSystem fileSystem,
            Environment environment,
            CustomConfigurationProperty customConfigurationProperty,
            CustomRecordProperty customRecordProperty,
            YamlConfigurationProperties yamlConfigurationProperties

    ) {
        this.fileSystem = fileSystem;
        this.environment = environment;
        this.customConfigurationProperty = customConfigurationProperty;
        this.customRecordProperty = customRecordProperty;
        this.yamlConfigurationProperties = yamlConfigurationProperties;
    }

    @ShellMethod("Display required disk space")
    public long minimumFreeSpace() {
        return customRecordProperty.filesystemMinimumFreeDiskSpace();
    }

    @ShellMethod("Convert to lowercase string")
    public String toLowercase(String input) {
        return input.toLowerCase();
    }

    @ShellMethod("Display free disk space in GB")
    public String freeDiskSpace() {
        return DataSize.ofBytes(fileSystem.getFreeDiskSpace()).toGigabytes() + " GB";
    }

    @ShellMethod("Display user home")
    public String getUserHomeName() {
        return environment.getProperty("user.home");
    }

    @ShellMethod("Get custom property value")
    public int getCustomPropertyValue() {
        return customRecordProperty.trees();
    }

    @ShellMethod("Get yaml custom property value")
    public int getYamlCustomPropertyValue() {
        YamlCustomValue yamlCustomValue = yamlConfigurationProperties.getYamlCustomValue();
        return yamlCustomValue.getYamlTrees();
    }
}
