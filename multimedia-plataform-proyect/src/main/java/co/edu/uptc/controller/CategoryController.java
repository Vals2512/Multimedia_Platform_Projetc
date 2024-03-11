package co.edu.uptc.controller;

import co.edu.uptc.model.Category;
import co.edu.uptc.util.FileManager;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CategoryController {
    ArrayList<Category> categories;
    private FileManager fm;
    private final static String FILE="categories";
    public CategoryController() {
        categories=new ArrayList<>();
        fm=new FileManager();
        fm.createFile(FILE);
    }

    public void updateName(Category category, String name) {
        category.setName(name);
    }

    public void updateDescription(Category category, String description) {
        category.setDescription(description);
    }

    public void delete(Category category){

    }

    public Category create(String name, String description) {
        Category category = new Category(name, description);
        categories.add(category);
        Type type =new TypeToken<ArrayList<Category>>(){}.getType();
        fm.saveObjectToFile(FILE,categories,type);
        return category; 
    }
}