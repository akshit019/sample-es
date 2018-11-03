package com.sample.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sample.es")
@EnableAutoConfiguration
public class SampleEsApplication {
	public static void main(String[] args) {
		SpringApplication.run(SampleEsApplication.class, args);
	}
}
