package org.module.service;

import org.module.api.Product;
import org.module.feign.FeignClientConfig;
import org.module.service.fallback.IProductClientServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//name 对应的是 eureka中某个服务的实例名字，例如：
//module-provider 服务的application.yml配置:
//spring:
//  application:
//	  name: module-provider  #注册中心显示的应用服务的名称
@FeignClient(name = "module-provider", 
		configuration = FeignClientConfig.class,
		fallbackFactory = IProductClientServiceFallbackFactory.class)
public interface IProductClientServiceInter {
	
    @RequestMapping("/provider/get/{id}")
    public Product getProduct(@PathVariable("id")long id);

    @RequestMapping("/provider/list")
    public  List<Product> listProduct();

    @RequestMapping("/provider/add")
    public boolean addPorduct(Product product);

}