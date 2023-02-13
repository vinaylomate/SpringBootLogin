package com.example.demo.master.producttype;

import com.example.demo.master.category.Category;
import com.example.demo.master.category.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final CategoryRepository categoryRepository;
    public String addProduct(ProductType productType) {
        if(productTypeRepository.findByProductTypeName(productType.getProductTypeName()) != null) {
            throw new IllegalStateException("Location Already Exists");
        }
        Category category = categoryRepository.findByCategoryName(productType.getCategoryName());
        category.addProductType(productType);
        productTypeRepository.save(productType);
        return "Product added";
    }

    public List<ProductTypeRegister> showAllProducts() {
        return productTypeRepository.findAllProducts();
    }

    public List<ProductType> showProductTypeByCategoryName(String categoryName) {
        return productTypeRepository.findProductTypeByCategory(categoryName);
    }
}
