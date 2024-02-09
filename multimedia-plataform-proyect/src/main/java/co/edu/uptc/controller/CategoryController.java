package co.edu.uptc.controller;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.database.CategoryDatabase;

public class CategoryController {
    public void updateName(Category category, String name) {
        category.setName(name);
    }

    public void updateDescription(Category category, String description) {
        category.setDescription(description);
    }

    public void delete(Category category){
        CategoryDatabase.CATEGORY_DATABASE.remove(category);
    }

    public Category create(String name, String description) {
        Category category = new Category(name, description); 
        CategoryDatabase.CATEGORY_DATABASE.add(category); 
        return category; 
    }
}