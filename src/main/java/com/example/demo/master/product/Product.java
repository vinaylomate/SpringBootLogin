package com.example.demo.master.product;

import com.example.demo.master.category.Category;
import com.example.demo.master.storeType.StoreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(
            name = "product_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "product_name",
            nullable = false
    )
    private String productName;

    @Transient
    private String categoryName;

    @Column(
            name = "product_quantity",
            nullable = false
    )
    private Integer quantity;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "category_id",
            foreignKey = @ForeignKey(
                    name = "location_store_type_fk"
            )
    )
    private Category category;

    public Product(String productName, String categoryName, Integer quantity) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.quantity = quantity;
    }
}
