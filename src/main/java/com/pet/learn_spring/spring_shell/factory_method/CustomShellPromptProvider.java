package com.pet.learn_spring.spring_shell.factory_method;

import com.pet.learn_spring.core.FileSystem;
import org.jetbrains.annotations.NotNull;
import org.jline.utils.AttributedString;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.jline.PromptProvider;

import java.lang.reflect.AnnotatedElement;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Random;

import static java.util.Objects.nonNull;

@Configuration
public class CustomShellPromptProvider  {

    private static final PromptProvider userPromptProvider =
            () -> new AttributedString("User Shell:> ");

    private static final PromptProvider adminPromptProvider =
            () -> new AttributedString("Admin Shell:> ");

    @Bean
    PromptProvider promptProviderFactory(FileSystem fileSystem) {
        return adminPromptProvider;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Random getRandom(@NotNull InjectionPoint injectionPoint) {
        return nonNull(injectionPoint.getAnnotation(CryptographicalAnnotation.class))
                || (injectionPoint.getMember() instanceof AnnotatedElement member
        && member.isAnnotationPresent(CryptographicalAnnotation.class))
                ? new SecureRandom()
        : new Random();
    }

    @Bean
    public Converter<String, Path> convertPathFromString() {
        return new Converter<String, Path>() {
            @Override
            public Path convert(String source) {
                return Path.of(source);
            }
        };
    }
}
