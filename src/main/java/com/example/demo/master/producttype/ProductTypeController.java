package com.example.demo.master.producttype;

import com.example.demo.master.storeType.StoreType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @PostMapping("addProductType")
    public String addProduct(@RequestBody ProductType productType) {
        return productTypeService.addProduct(productType);
    }

    @GetMapping("allProductType")
    public List<ProductTypeRegister> showAllProducts() {
        List<ProductTypeRegister> productTypeRegisters = productTypeService.showAllProducts();
        return productTypeRegisters;
    }

    @GetMapping(path = "getProductType/{categoryName}")
    public List<ProductType> showProductTypeByCategoryName(@PathVariable("categoryName") String categoryName) {
        return productTypeService.showProductTypeByCategoryName(categoryName);
    }
}
