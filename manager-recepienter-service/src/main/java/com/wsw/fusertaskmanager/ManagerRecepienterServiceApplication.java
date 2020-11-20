package com.wsw.fusertaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ManagerRecepienterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerRecepienterServiceApplication.class, args);
	}

}
