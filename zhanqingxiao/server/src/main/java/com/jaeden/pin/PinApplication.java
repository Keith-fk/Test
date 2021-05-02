package com.jaeden.pin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableWebSocket
public class PinApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinApplication.class, args);
    }

}

