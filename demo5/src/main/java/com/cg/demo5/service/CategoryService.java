package com.cg.demo5.service;

import com.cg.demo5.DAO.CategoryDAO;
import com.cg.demo5.model.Category;

import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> findAll(){
        return categoryDAO.findAll();
    }

    public Category findById(int id){

        return categoryDAO.findById(id);
    }
}
