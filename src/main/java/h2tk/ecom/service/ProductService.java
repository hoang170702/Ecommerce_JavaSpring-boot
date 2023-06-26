package h2tk.ecom.service;


import h2tk.ecom.model.Products;

import java.util.List;



public interface ProductService {


    public List<Products> listAll();

    public void save(Products product);

    public Products get(int id);

    public void delete(int id);
}

