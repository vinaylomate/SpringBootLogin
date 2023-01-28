package com.example.demo.master.product;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/")
public class ProductController {

    private final ProductService productService;

    @PostMapping("product")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("allProduct")
    public List<ProductRegister> showAllProducts() {
        List<ProductRegister> productRegisters = productService.showAllProducts();
        System.out.println(productRegisters);
        return productRegisters;
    }
}
