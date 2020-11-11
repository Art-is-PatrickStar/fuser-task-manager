package com.wsw.fusertaskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableScheduling
@EnableCaching
public class ManagerTaskServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerTaskServiceApplication.class, args);
	}

}
