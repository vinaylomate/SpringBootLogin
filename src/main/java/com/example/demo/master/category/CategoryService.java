package com.example.demo.master.category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public String addCategory(Category category) {
        if(categoryRepository.findByCategoryName(category.getCategoryName()) != null) {
            throw new IllegalStateException("Store type already exists");
        }
        categoryRepository.save(category);
        return "Category added";
    }

    public List<Category> showAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
