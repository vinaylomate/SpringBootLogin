package com.example.demo.master.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("category")
    public String addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("allCategory")
    public List<Category> showAllCategory() {
        return categoryService.showAllCategory();
    }

    @PutMapping("category/edit/{id}")
    public String updateCategory(@PathVariable("id") Long id,  @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
}
