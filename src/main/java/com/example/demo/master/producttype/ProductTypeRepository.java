package com.example.demo.master.producttype;

import com.example.demo.master.storeType.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

    @Query(value = "SELECT * FROM product_type WHERE product_type_name = ?1", nativeQuery = true)
    ProductType findByProductTypeName(String productTypeName);

    @Query("SELECT new com.example.demo.master.producttype.ProductTypeRegister(p.id,p.productTypeName,c.categoryName) FROM ProductType p JOIN p.category c")
    List<ProductTypeRegister> findAllProducts();

    @Query("SELECT p FROM ProductType p WHERE p.category.categoryName = ?1")
    List<ProductType> findProductTypeByCategory(String productCategoryName);
}
