package org.module.consumer.feign;
//import cn.xiangxue.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "module-provider", configuration = RibbonConfig.class)
@EnableFeignClients("org.module.service")
public class ConsumerFeignApp {
	
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignApp.class,args);
    }
    
}