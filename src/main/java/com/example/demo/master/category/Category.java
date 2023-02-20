package com.example.demo.master.category;

import com.example.demo.master.product.Product;
import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.rack.Rack;
import com.example.demo.master.transaction.Transaction;
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

    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY    // this will not join the book table to any table
    )
    private List<ProductType> productTypes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "category"
    )
    private List<Transaction> transactions = new ArrayList<>();
    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY    // this will not join the book table to any table
    )
    private List<Rack> racks = new ArrayList<>();

    @JsonIgnore
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

    public void addProductType(ProductType productType) {
        if(!this.productTypes.contains(productType)) {
            this.productTypes.add(productType);
            productType.setCategory(this);
        }
    }

    public void removeProductType(ProductType productType) {
        if(!this.productTypes.contains(productType)) {
            this.productTypes.add(productType);
            productType.setCategory(null);
        }
    }

    public void addRack(Rack rack) {
        if(!this.racks.contains(rack)) {
            this.racks.add(rack);
            rack.setCategory(this);
        }
    }

    public void removeRack(Rack rack) {
        if(!this.racks.contains(rack)) {
            this.racks.add(rack);
            rack.setCategory(null);
        }
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
            product.setCategory(null);
        }
    }

    public void addTransaction(Transaction transaction) {
        if(!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
            transaction.setCategory(this);
        }
    }

    public void removeTransaction(Transaction transaction) {
        if(this.transactions.contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.setCategory(null);
        }
    }
}
