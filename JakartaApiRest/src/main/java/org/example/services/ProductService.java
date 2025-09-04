package org.example.services;

import jakarta.inject.Named;
import org.example.abstractions.IProductService;
import org.example.models.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class ProductService implements IProductService {

    private static  List<Product> products = new ArrayList<>();

    @Override
    public List<Product> listAll() {
        return products;
    }

    @Override
    public Product save(Product product) {
        products.add(product);
         return product;
    }

    @Override
    public Product update(Product product) {
        products = products.stream().map(c -> c.getId().equals(product.getId()) ?product : c).collect(Collectors.toList());
        return product;
    }

    @Override
    public Product findById(Integer id) {
        return products.stream().filter(c->c.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public void delete(Integer id) {
           products.removeIf(c->c.getId().equals(id));
    }
}
