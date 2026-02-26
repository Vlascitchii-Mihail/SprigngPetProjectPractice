package com.pet.learn_spring.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("com.custom-value")
public record CustomRecordProperty(

        @DefaultValue("300")
        int trees,
        @DefaultValue("0")
        long filesystemMinimumFreeDiskSpace
) {
}
