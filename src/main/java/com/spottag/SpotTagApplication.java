package com.spottag;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

@SpringBootApplication
@PropertySource("classpath:application-private.yml")
@RequiredArgsConstructor
public class SpotTagApplication {
    private final Environment env;


    public static void main(String[] args) {
        SpringApplication.run(SpotTagApplication.class, args);
    }

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

}
