package com.example.eco_track_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(jsr250Enabled = true,prePostEnabled = true,securedEnabled = true)
public class EcoTrackBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoTrackBackendApplication.class, args);
	}

}
