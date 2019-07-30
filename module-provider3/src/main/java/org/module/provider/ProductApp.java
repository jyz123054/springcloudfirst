package org.module.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("org.module.provider.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker //Hystrix熔断机制
public class ProductApp {
	
	public static void main(String[] args) {
		SpringApplication.run(ProductApp.class, args);
	}
	
}
