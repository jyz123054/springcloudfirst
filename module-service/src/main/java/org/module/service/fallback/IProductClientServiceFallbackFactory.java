package org.module.service.fallback;


import feign.hystrix.FallbackFactory;
import org.module.api.Product;
import org.module.service.IProductClientServiceInter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IProductClientServiceFallbackFactory implements FallbackFactory<IProductClientServiceInter> {
    @Override
    public IProductClientServiceInter create(Throwable throwable) {
        return new IProductClientServiceInter() {
        	//对所有IProductClientServiceInter方法进行fallback定义
            @Override
            public Product getProduct(long id) {
                Product product = new Product();
                product.setProductId(999999L);
                product.setProductName("feign-hystrixName");
                product.setProductDesc("feign-hystrixDesc");
                return  product;
            }

            @Override
            public List<Product> listProduct() {
                return null;
            }

            @Override
            public boolean addPorduct(Product product) {
                return false;
            }
        };
    }
}