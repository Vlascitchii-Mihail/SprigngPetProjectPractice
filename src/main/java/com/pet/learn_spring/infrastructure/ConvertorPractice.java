package com.pet.learn_spring.infrastructure;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConvertorPractice {

    private final ConversionService conversionService = new DefaultConversionService();

    public ConversionService getConversionService() {
        return conversionService;
    }
}
