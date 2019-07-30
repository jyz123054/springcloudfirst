package org.module.consumer.controller;

import org.module.api.Product;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {
//    public static final String PRODUCT_GET_URL = "http://localhost:8080/provider/get/";
//    public static final String PRODUCT_LIST_URL="http://localhost:8080/provider/list/";
//    public static final String PRODUCT_ADD_URL = "http://localhost:8080/provider/add/";
	
	//注意跟上面对比，上面的使用是直接调用固定的服务提供方进行访问，并非是通过Ribbon做负载均衡
	//我们应该使用具体的应用服务名（即服务提供方在注册到Eureka中的服务名称）
	//例如：module-provider 的 application.yml配置
	//spring:
	//  application:
	//	   name: module-provider  #注册中心显示的应用服务名称
    public static final String PRODUCT_GET_URL = "http://module-provider/provider/get/";
    public static final String PRODUCT_LIST_URL= "http://module-provider/provider/list/";
    public static final String PRODUCT_ADD_URL = "http://module-provider/provider/add/";
    
    @Resource
    private RestTemplate restTemplate;
    
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Resource
    private HttpHeaders httpHeaders;

    @RequestMapping("/provider/get")
    public Object getProduct(long id) {
        Product product = restTemplate.exchange(PRODUCT_GET_URL + id, HttpMethod.GET,
        		new HttpEntity<Object>(httpHeaders), Product.class).getBody();
        return product;
    }

    @SuppressWarnings("unchecked")
	@RequestMapping("/provider/list")
    public Object listProduct() {
    	
    	ServiceInstance instance = loadBalancerClient.choose("module-provider");
    	
    	System.out.println(
                "【*** ServiceInstance ***】host = " + instance.getHost()
                        + "、port = " + instance.getPort()
                        + "、serviceId = " + instance.getServiceId());
    	
        List<Product> list = restTemplate.exchange(PRODUCT_LIST_URL, HttpMethod.GET,
                new HttpEntity<Object>(httpHeaders), List.class).getBody();
        return list;
    }

    @RequestMapping("/provider/add")
    public Object addPorduct(Product product) {
        Boolean result = restTemplate.exchange(PRODUCT_ADD_URL, HttpMethod.POST,
                new HttpEntity<Object>(product,httpHeaders), Boolean.class).getBody();
        return result;
    }
}