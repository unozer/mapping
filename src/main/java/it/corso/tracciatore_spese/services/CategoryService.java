package it.corso.tracciatore_spese.services;

import it.corso.tracciatore_spese.controllers.ExpenseController;
import it.corso.tracciatore_spese.models.Category;
import it.corso.tracciatore_spese.models.Expense;
import it.corso.tracciatore_spese.repositories.CategoryRepository;
import it.corso.tracciatore_spese.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createNewCategory(String categoryType) {
        Category newCategory = new Category(null, categoryType);
        return categoryRepository.save(newCategory);
    }

    public Category updateCategory(Long id, String categoryType) {
        Category updatedCategory = new Category(id, categoryType);
        return categoryRepository.save(updatedCategory);
    }

    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }
}
