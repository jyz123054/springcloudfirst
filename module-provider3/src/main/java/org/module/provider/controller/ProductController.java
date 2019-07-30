package org.module.provider.controller;
import org.module.api.Product;
import org.module.provider.service.IProductService;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;

import javax.annotation.Resource;
@RestController
@RequestMapping("/provider")
public class ProductController {

    @Resource
    private IProductService iProductService;

//    import com.netflix.discovery.DiscoveryClient;
//    import org.springframework.cloud.client.discovery.DiscoveryClient;
    @Resource
    private DiscoveryClient client; // 进行Eureka的发现服务

    @RequestMapping(value="/get/{id}")
    @HystrixCommand(fallbackMethod = "testFallbackMethod")
    public Object get(@PathVariable("id") long id) {
    	Product obj = this.iProductService.get(id);
    	if (obj == null) {
    		throw new RuntimeException("产品已经下架-降级");
    	}
        return obj;
    }
    
    /**
     * Hystrix处理，降级
     * @param id
     * @return
     */
    public Object testFallbackMethod(@PathVariable("id") long id) {
    	Product product = new Product();
        product.setProductName("Hystrix");
        product.setProductDesc("该产品已经下架！-降级");
        product.setProductId(0L);
        return product;
    }
    
    @RequestMapping(value="/add")
    public Object add(@RequestBody Product product) {
        return this.iProductService.add(product) ;
    }
    
    @RequestMapping(value="/list")
    public Object list() {
        return this.iProductService.list();
    }

    @RequestMapping("/discover")
    public Object discover() { // 直接返回发现服务信息
        return this.client ;
    }
}