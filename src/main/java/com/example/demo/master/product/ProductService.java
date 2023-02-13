package com.example.demo.master.product;

import com.example.demo.master.category.Category;
import com.example.demo.master.category.CategoryRepository;
import com.example.demo.master.company.Company;
import com.example.demo.master.company.CompanyRepository;
import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.producttype.ProductTypeRepository;
import com.example.demo.master.uom.UOM;
import com.example.demo.master.uom.UOMRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductTypeRepository productTypeRepository;
    private final CompanyRepository companyRepository;
    private final UOMRepository uomRepository;

    public String addProduct(Product product) {
        if(productRepository.findByProductSageCode(product.getSageCode()) != null) {
            throw new IllegalStateException("product already exists");
        }
        UOM uom = uomRepository.findByUnitName(product.getPackSize());
        Category category = categoryRepository.findByCategoryName(product.getCategoryName());
        ProductType productType = productTypeRepository.findByProductTypeName(product.getProductTypeName());
        Company company = companyRepository.findByCompanyCode(product.getCompanyCode());
        uom.addProduct(product);
        category.addProduct(product);
        productType.addProduct(product);
        company.addProduct(product);
        productRepository.save(product);
        return "product added";
    }

    public List<ProductRegister> showAllProducts() {
        return productRepository.findAllProduct();
    }

    public List<Product> showAllProductByCategory(String categoryName) {

        return productRepository.findAllByCategory(categoryName);
    }

    public List<ProductRegister> showAllProductPagination(Integer pageNumber, Integer size) {
        Pageable pageable = PageRequest.of(
                pageNumber,
                size,
                Sort.by("productName").ascending()
        );
        Page<ProductRegister> products = productRepository.findAllProductPagination(pageable);
        return products.getContent();
    }

    public List<Product> showAllProductByKeyword(String keywords) {
        Pageable pageable = PageRequest.of(
                0,
                20,
                Sort.by("productName").ascending()
        );
        Page<Product> products = productRepository.searchProductByKeyword(keywords, pageable);
        return products.getContent();
    }

    public Product showProductBySageCode(String sageCode) {

        return productRepository.findByProductSageCode(sageCode);
    }

    public ProductType showProductTypeByCode(String sageCode) {

        return productRepository.findProductType(sageCode);
    }

    public UOM showProductByUOM(String sageCode) {
        return productRepository.findUOMBySageCode(sageCode);
    }
}
