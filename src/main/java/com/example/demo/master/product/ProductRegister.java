package com.example.demo.master.product;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ProductRegister {

    private Long id;
    private String productName;

    private String categoryName;
    private Integer quantity;
}
