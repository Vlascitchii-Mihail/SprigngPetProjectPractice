package com.pet.learn_spring.core.configuration.yaml_config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("com")
public class YamlConfigurationProperties {
    private YamlCustomValue yamlCustomValue;

    public YamlCustomValue getYamlCustomValue() { return  yamlCustomValue; }
    public void setYamlCustomValue(YamlCustomValue yamlCustomValue) {
        this.yamlCustomValue = yamlCustomValue;
    }
}
