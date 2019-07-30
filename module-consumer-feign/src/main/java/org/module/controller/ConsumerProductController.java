package org.module.controller;

import org.module.api.Product;
import org.module.service.IProductClientServiceInter;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
public class ConsumerProductController {

    @Resource
    private IProductClientServiceInter iProductClientServiceInter;

    @Resource
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/provider/get")
    public Object getProduct(long id) {
        return  iProductClientServiceInter.getProduct(id);
    }

    @RequestMapping("/provider/list")
    public  Object listProduct() {
    	ServiceInstance instance = loadBalancerClient.choose("module-provider");
    	
    	System.out.println(
                "【*** Feign ServiceInstance ***】host = " + instance.getHost()
                        + "、port = " + instance.getPort()
                        + "、serviceId = " + instance.getServiceId());
    	
        return iProductClientServiceInter.listProduct();
    }

    @RequestMapping("/provider/add")
    public Object addPorduct(Product product) {
        return  iProductClientServiceInter.addPorduct(product);
    }
}