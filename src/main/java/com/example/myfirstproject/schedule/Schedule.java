package com.example.myfirstproject.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {

    private Logger LOGGER = LoggerFactory.getLogger(Schedule.class);

    @Scheduled(cron = "0 1 1 * * *")
    public void schedule() {
        LOGGER.info("Everything is alright!");
    }
}
