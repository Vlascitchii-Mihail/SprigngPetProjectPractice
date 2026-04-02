package com.pet.learn_spring.spring_shell;

import com.pet.learn_spring.core.FileSystem;
import com.pet.learn_spring.core.configuration.CustomConfigurationProperty;
import com.pet.learn_spring.core.configuration.CustomRecordProperty;
import com.pet.learn_spring.core.configuration.yaml_config.YamlConfigurationProperties;
import com.pet.learn_spring.core.configuration.yaml_config.YamlCustomValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.unit.DataSize;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@ShellComponent
public class FsCommands {
    private final FileSystem fileSystem;
    private final Environment environment;
    private final CustomConfigurationProperty customConfigurationProperty;
    private final CustomRecordProperty customRecordProperty;
    private final YamlConfigurationProperties yamlConfigurationProperties;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

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

    @ShellMethod("Get servers IP list")
    public List<CustomConfigurationProperty.Server> getServersIp() {
        return customConfigurationProperty.getServers();
    }

    @ShellMethod("Get yaml servers IP list")
    public List<YamlCustomValue.Server> getYamlServersIp() {
        return yamlConfigurationProperties.getYamlCustomValue().getServersList();
    }

    @ShellMethod("Get servers IP Map")
    public Map<String, CustomConfigurationProperty.Server> getServerMapIp() {
        return customConfigurationProperty.getServerMap();
    }

    @ShellMethod("Get yaml servers IP Map")
    public Map<String, YamlCustomValue.Server> getYamlServersMapIp() {
        sendFsCommandsEvent("Get yaml servers IP Map");
        return yamlConfigurationProperties.getYamlCustomValue().getServersMap();
    }

    private void sendFsCommandsEvent(String commandName) {
        PayloadApplicationEvent<String> event = new PayloadApplicationEvent<>(
                this,
                commandName
        );

        eventPublisher.publishEvent(event);
    }

    @ShellMethod("Display if a path exists")
    public String exists(Path path) {
        Boolean exists;
        try {

            var pathRoot = path.getRoot();
            if (pathRoot == null) exists = false;
            else exists = true;
        } catch (InvalidPathException exception) {
            exists = false;
        }
        return String.format(
                "Path to '%s' %s exist: ", path, exists ? "does" : "doesn't"
        );
    }
}
