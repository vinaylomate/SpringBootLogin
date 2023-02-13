package com.example.demo.master.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRegister {

    private Long id;
    private String companyCode;
    private String companyName;
    private String categoryName;
    private String productTypeName;
    private String sageCode;
    private String focusCode;
    private String productName;
    private Long reorderLevelQuantity;
    private Integer productLifetime;

    public ProductRegister(Long id,
                           String companyCode,
                           String companyName,
                           String categoryName,
                           String productTypeName,
                           String sageCode,
                           String focusCode,
                           String productName,
                           Long reorderLevelQuantity,
                           Integer productLifetime) {
        this.id = id;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.categoryName = categoryName;
        this.productTypeName = productTypeName;
        this.sageCode = sageCode;
        this.focusCode = focusCode;
        this.productName = productName;
        this.reorderLevelQuantity = reorderLevelQuantity;
        this.productLifetime = productLifetime;
    }
}
