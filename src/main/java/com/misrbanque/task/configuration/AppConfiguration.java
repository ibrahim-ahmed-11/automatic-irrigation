package com.misrbanque.task.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@Configuration
public class AppConfiguration {

    @Getter
    @Value("${server.integration.max-retries-count}")
    int maxRetriesCount;

}
