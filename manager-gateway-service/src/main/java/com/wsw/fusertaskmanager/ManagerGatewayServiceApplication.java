package com.wsw.fusertaskmanager;

//import com.wsw.fusertaskmanager.config.IgnoreUrlsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableConfigurationProperties(IgnoreUrlsConfig.class)
public class ManagerGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerGatewayServiceApplication.class, args);
	}

}
