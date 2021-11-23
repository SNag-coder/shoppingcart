package com.sourav.shoppingCart.controller;

import com.sourav.shoppingCart.model.Category;
import com.sourav.shoppingCart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("create")
    public void createCategory(@RequestBody Category category){
        categoryService.setCat(category);

    }
}
