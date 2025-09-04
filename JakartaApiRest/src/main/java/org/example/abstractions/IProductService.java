package org.example.abstractions;

import org.example.models.Product;

import java.util.List;

public interface IProductService {

    List<Product> listAll();

    Product save(Product product);

    Product update(Product product);

    Product findById(Integer id);

    void delete (Integer id);

}
