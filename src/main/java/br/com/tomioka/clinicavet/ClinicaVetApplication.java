package br.com.tomioka.clinicavet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Profile;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class ClinicaVetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaVetApplication.class, args);
	}

}
