package ru.endienasg.artlandia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ArtlandiaApplication {
	public static final Logger log =
			LoggerFactory.getLogger(ArtlandiaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ArtlandiaApplication.class, args);
	}

}
