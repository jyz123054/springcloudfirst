package org.module.provider.service;
import org.module.api.Product;
import java.util.List;

public interface IProductService {
    Product get(long id);
    boolean add(Product product);
    List<Product> list();
}