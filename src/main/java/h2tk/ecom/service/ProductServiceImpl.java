package h2tk.ecom.service;


import h2tk.ecom.model.Products;
import h2tk.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository repo;
    private Products product;

    @Override
    public List<Products> listAll() {
        return repo.findAll();
    }
    @Override
    public void save(Products product) {
        this.product = product;
        repo.save(product);
    }
    @Override
    public Products get(int id) {

        Optional< Products > optional = repo.findById(id);
        Products product = null;
        if (optional.isPresent()) {
            product = optional.get();
        } else {
            throw new RuntimeException(" Product not found for id :: " + id);
        }
        return product;
    }
    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
