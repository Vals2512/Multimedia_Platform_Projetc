package co.edu.uptc.model.database;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Category;

public class CategoryDatabase {

    public static final CategoryDatabase CATEGORY_DATABASE = new CategoryDatabase();

    private List<Category> categories = new ArrayList<>();

    public CategoryDatabase() {
        // Predefinir las categorias
    }

    // las categorias deben ser unicas, una categoria es igual a otra si tiene el
    // mismo nombre, solo agrega una categoria si no existe.
    public boolean add(Category category) {
        if (categories.contains(category)) {
            return false;
        }

        categories.add(category);
        return true;
    }

    public boolean remove(Category category) {
        return categories.remove(category);
    }

    public Category search(String name) {
        for (Category category : categories) {
            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }
}