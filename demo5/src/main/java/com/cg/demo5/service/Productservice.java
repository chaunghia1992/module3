package com.cg.demo5.service;

import com.cg.demo5.DAO.ProductDAO;
import com.cg.demo5.model.Product;

import java.util.List;

public class Productservice {
    private ProductDAO productDAO = new ProductDAO();

    public void detele(int id) {
        productDAO.deteleUse(id);
    }

    public List<Product> findAll(){
        return productDAO.findAll();
    }
    public void createProduct(Product product){
    productDAO.insertProduct(product);
    }
    public void editProduct(Product product){
        productDAO.editProduct(product);
    }

    public Product findById(int id) {
        return productDAO.findById(id);
    }

}
