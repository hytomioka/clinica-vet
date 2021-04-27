package br.com.tomioka.clinicavet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ClinicaVetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicaVetApplication.class, args);
	}

}
