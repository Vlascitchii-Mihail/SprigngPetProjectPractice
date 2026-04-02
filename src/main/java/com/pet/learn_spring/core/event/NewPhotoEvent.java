package com.pet.learn_spring.core.event;

import java.time.OffsetDateTime;

public record NewPhotoEvent(String name, OffsetDateTime dateTime) { }
