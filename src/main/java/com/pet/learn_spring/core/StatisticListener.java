package com.pet.learn_spring.core;

import com.pet.learn_spring.core.event.NewPhotoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@EnableAsync
@Component
public class StatisticListener {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @EventListener
    public void onNewPhotoEvent(NewPhotoEvent event) {
        log.info("New photo: {}", event);
    }

    @Async
    @EventListener
    public void onNewFsCommandEventListener(PayloadApplicationEvent<String> event) {
        log.info("The new File system command: " + event.getPayload());
    }
}
