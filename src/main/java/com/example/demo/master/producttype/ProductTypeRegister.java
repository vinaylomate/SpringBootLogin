package com.example.demo.master.producttype;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductTypeRegister {

    private Long id;
    private String productTypeName;
    private String categoryName;
}
