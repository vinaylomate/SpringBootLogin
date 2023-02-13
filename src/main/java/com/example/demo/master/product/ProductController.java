package com.example.demo.master.product;

import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.uom.UOM;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "addProduct")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping(path = "allProducts")
    public List<ProductRegister> showAllProducts() {
        return productService.showAllProducts();
    }

    @GetMapping(path = "getAllProduct/{categoryName}")
    public List<Product> showAllProductByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.showAllProductByCategory(categoryName);
    }

    @GetMapping(path = "allProduct/{pageNumber}/{size}")
    public List<ProductRegister> showAllProductPagination(@PathVariable("pageNumber") Integer pageNumber,
                                                          @PathVariable("size") Integer size) {
        return productService.showAllProductPagination(pageNumber, size);
    }

    @GetMapping(path = "allProduct/{keywords}")
    public List<Product> showAllProductByKeyword(String keywords) {
        return productService.showAllProductByKeyword(keywords);
    }

    @GetMapping(path = "getProduct/{sageCode}")
    public Product showProductBySageCode(@PathVariable("sageCode") String sageCode) {
        return productService.showProductBySageCode(sageCode);
    }

    @GetMapping(path = "getProductTypeByCode/{sageCode}")
    public ProductType showProductTypeByCode(@PathVariable("sageCode") String sageCode) {
        return productService.showProductTypeByCode(sageCode);
    }

    @GetMapping(path = "getUomByProduct/{sageCode}")
    public UOM showProductByUOM(@PathVariable("sageCode") String sageCode) {
        return productService.showProductByUOM(sageCode);
    }
}
