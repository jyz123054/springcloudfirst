package org.module.provider.mapper;
import org.module.api.Product;
import java.util.List;

public interface ProductMapper {
    boolean create(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
}