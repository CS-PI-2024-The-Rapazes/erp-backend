package org.therapazes.luisaoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {OAuth2ClientAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableAsync
public class LuisaoprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuisaoprojectApplication.class, args);
	}

}
