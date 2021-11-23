package com.sourav.shoppingCart.service;


import com.sourav.shoppingCart.repository.CategoryRepo;
import com.sourav.shoppingCart.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    @Override
    public void setCat(Category category) {
        categoryRepo.save(category);

    }
}
