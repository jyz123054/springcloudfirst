package org.module.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;

import java.nio.charset.Charset;
import java.util.Base64;

@Configuration
public class RestConfig {
	
    @Bean
    @LoadBalanced	//通过Ribbon 做负载均衡
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }
    
    /**
     * 全局设置，Ribbon的 路由策略算法修改为：随机策略。
     * 如果需要自定义单个的路由策略，应注释此方法。
     * @return
     */
//    @Bean
//    public IRule ribbonRule() { // 其中IRule就是所有规则的标准
//    	return new com.netflix.loadbalancer.RandomRule(); // 随机的访问策略
//    }

    @Bean
    public HttpHeaders getHeaders() { // 要进行一个Http头信息配置
        HttpHeaders headers = new HttpHeaders(); // 定义一个HTTP的头信息
        String auth = "admin:enjoy"; // 认证的原始信息
        byte[] encodedAuth = Base64.getEncoder()
                .encode(auth.getBytes(Charset.forName("US-ASCII"))); // 进行一个加密的处理
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }
}