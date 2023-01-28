package com.example.demo.master.category;

import com.example.demo.master.location.Location;
import com.example.demo.master.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )
    @Column(
            name = "category_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "category_name",
            nullable = false
    )
    private String categoryName;

    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY    // this will not join the book table to any table
    )
    private List<Product> products = new ArrayList<>();

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addProduct(Product product) {
        if(!this.products.contains(product)) {
            this.products.add(product);
            product.setCategory(this);
        }
    }

    public void removeProduct(Product product) {
        if(!this.products.contains(product)) {
            this.products.add(product);
            product.setCategory(this);
        }
    }
}
