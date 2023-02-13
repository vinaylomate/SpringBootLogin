package com.example.demo.master.uom;

import com.example.demo.master.company.Company;
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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "uom")
public class UOM implements Serializable {

    @Id
    @SequenceGenerator(
            name = "uom_sequence",
            sequenceName = "uom_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "uom_sequence"
    )
    @Column(
            name = "uom_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "unit_name",
            nullable = false
    )
    private String unitName;
    @Column(
            name = "unit_description"
    )
    private String unitDescription;
    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "uom",
            fetch = FetchType.EAGER
    )
    private List<Transaction> transactions = new ArrayList<>();
    @JsonIgnore
    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "uom"
    )
    private List<Product> products = new ArrayList<>();

    public UOM(String unitName, String unitDescription) {
        this.unitName = unitName;
        this.unitDescription = unitDescription;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setUom(this);
    }

    public void removeTransaction(Transaction transaction) {
        if(this.transactions.contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.setUom(null);
        }
    }

    public void addProduct(Product product) {
        if(!this.products.contains(product)) {
            this.products.add(product);
            product.setUom(this);
        }
    }

    public void removeProduct(Product product) {
        if(this.products.contains(product)) {
            this.products.remove(product);
            product.setUom(null);
        }
    }
}
