package com.example.demo.master.product;

import com.example.demo.master.storeType.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM product WHERE product_name = ?1", nativeQuery = true)
    StoreType findByProductName(String productName);

    @Query("SELECT new com.example.demo.master.product.ProductRegister(p.id,p.productName,c.categoryName,p.quantity) FROM Product p JOIN p.category c")
    List<ProductRegister> findAllProducts();
}
