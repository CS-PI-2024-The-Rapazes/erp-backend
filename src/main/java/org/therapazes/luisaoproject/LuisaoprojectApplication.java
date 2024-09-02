package org.therapazes.luisaoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LuisaoprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LuisaoprojectApplication.class, args);
    }

}
