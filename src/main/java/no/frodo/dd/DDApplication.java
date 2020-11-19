package no.frodo.dd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class DDApplication {

	public static void main(String[] args) {
		SpringApplication.run(DDApplication.class, args);
	}

}
