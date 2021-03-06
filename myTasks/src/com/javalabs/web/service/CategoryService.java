package com.javalabs.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalabs.web.dao.Category;
import com.javalabs.web.dao.CategoryDao;

@Service("categoryService")
public class CategoryService {
    
    private CategoryDao categoryDao;

    @Autowired
    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category getCategory(long idCategory){
        return categoryDao.getCategory(idCategory);
    }
    
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }
}
