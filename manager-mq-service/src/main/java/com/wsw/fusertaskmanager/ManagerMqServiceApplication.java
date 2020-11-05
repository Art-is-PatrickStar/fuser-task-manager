package com.wsw.fusertaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ManagerMqServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerMqServiceApplication.class, args);
	}

}
