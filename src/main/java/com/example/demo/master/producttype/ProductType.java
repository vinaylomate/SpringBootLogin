package com.example.demo.master.producttype;

import com.example.demo.master.category.Category;
import com.example.demo.master.product.Product;
import com.example.demo.master.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_type")
@Getter
@Setter
@NoArgsConstructor
public class ProductType {

    @Id
    @SequenceGenerator(
            name = "product_type_sequence",
            sequenceName = "product_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_type_sequence"
    )
    @Column(
            name = "product_type_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "product_type_name",
            nullable = false
    )
    private String productTypeName;
    @Transient
    private String categoryName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "category_name",
            nullable = false,
            referencedColumnName = "category_name",
            foreignKey = @ForeignKey(
                    name = "location_product_category_fk"
            )
    )
    private Category category;

    @JsonIgnore
    @OneToMany(
            mappedBy = "productType",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY    // this will not join the book table to any table
    )
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "productType"
    )
    private List<Transaction> transactions = new ArrayList<>();

    public ProductType(String productTypeName, String categoryName) {
        this.productTypeName = productTypeName;
        this.categoryName = categoryName;
    }

    public void addProduct(Product product) {
        if(!this.products.contains(product)) {
            this.products.add(product);
            product.setProductType(this);
        }
    }

    public void removeProduct(Product product) {
        if(!this.products.contains(product)) {
            this.products.add(product);
            product.setProductType(null);
        }
    }

    public void addTransaction(Transaction transaction) {
        if(!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
            transaction.setProductType(this);
        }
    }

    public void removeTransaction(Transaction transaction) {
        if(this.transactions.contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.setProductType(null);
        }
    }
}
