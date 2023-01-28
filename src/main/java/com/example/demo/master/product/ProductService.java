package com.example.demo.master.product;

import com.example.demo.master.category.Category;
import com.example.demo.master.category.CategoryRepository;
import com.example.demo.master.storeType.StoreType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public String addProduct(Product product) {
        if(productRepository.findByProductName(product.getProductName()) != null) {
            throw new IllegalStateException("Location Already Exists");
        }
        Category category = categoryRepository.findByCategoryName(product.getCategoryName());
        category.addProduct(product);
        productRepository.save(product);
        return "Product added";
    }

    public List<ProductRegister> showAllProducts() {
        return productRepository.findAllProducts();
    }
}
