package com.example.demo.master.product;

import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.rack.Rack;
import com.example.demo.master.uom.UOM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM product WHERE sage_code = ?1", nativeQuery = true)
    Product findByProductSageCode(String sageCode);

    @Query(value = "SELECT new com.example.demo.master.product.ProductRegister(pd.id, c.companyCode, c.companyName, pc.categoryName, pt.productTypeName, pd.sageCode, pd.focusCode, pd.productName, pd.reorderLevelQuantity, pd.productLifetime) FROM Product pd JOIN pd.productType pt JOIN pd.category pc JOIN pd.company c")
    List<ProductRegister> findAllProduct();

    @Query(value = "SELECT pd FROM Product pd WHERE pd.category.categoryName = ?1")
    List<Product> findAllByCategory(String categoryName);

    @Query(value = "SELECT new com.example.demo.master.product.ProductRegister(pd.id, c.companyCode, c.companyName, pc.categoryName, pt.productTypeName, pd.sageCode, pd.focusCode, pd.productName, pd.reorderLevelQuantity, pd.productLifetime) FROM Product pd JOIN pd.productType pt JOIN pd.category pc JOIN pd.company c")
    Page<ProductRegister> findAllProductPagination(Pageable pageable);

    @Query(value = "SELECT pd FROM Product pd WHERE pd.sageCode = ?1 OR pd.focusCode = ?1 OR pd.productName = ?1")
    Page<Product> searchProductByKeyword(String keywords, Pageable pageable);

    @Query(value = "SELECT pt FROM Product pd JOIN pd.productType pt WHERE pd.sageCode = ?1")
    ProductType findProductType(String sageCode);

    @Query(value = "SELECT u FROM Product pd JOIN pd.uom u WHERE pd.sageCode = ?1")
    UOM findUOMBySageCode(String sageCode);
}
