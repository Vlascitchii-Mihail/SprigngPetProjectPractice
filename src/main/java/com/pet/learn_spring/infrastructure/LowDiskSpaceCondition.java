package com.pet.learn_spring.infrastructure;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.unit.DataSize;

import java.io.File;

public class LowDiskSpaceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return DataSize.ofBytes(new File("C:/").getFreeSpace())
                .toGigabytes() > 10;
    }
}
