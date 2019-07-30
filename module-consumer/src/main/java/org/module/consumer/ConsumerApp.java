package org.module.consumer;
import org.module.selfRibbon.config.SelfRibbonPolicy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "module-provider", configuration = SelfRibbonPolicy.class)	
//@RibbonClient 自定义Ribbon路由策略，@RibbonClients定义多个
public class ConsumerApp {
	
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }
}