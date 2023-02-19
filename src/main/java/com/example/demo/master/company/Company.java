package com.example.demo.master.company;

import com.example.demo.master.location.Location;
import com.example.demo.master.product.Product;
import com.example.demo.master.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @SequenceGenerator(
            name = "company_sequence",
            sequenceName = "company_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "company_sequence"
    )
    @Column(
            name = "company_id",
            updatable = false,
            nullable = false
    )
    private Long id;
    @Column(
            name = "company_code",
            nullable = false
    )
    private String companyCode;
    @Column(
            name = "company_name",
            nullable = false
    )
    private String companyName;

    @Column(
            name = "company_description",
            nullable = false
    )
    private String companyDescription;

    @JsonIgnore
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            mappedBy = "company"
    )
    private List<Location> locations = new ArrayList<>();
    @JsonIgnore
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            mappedBy = "company"
    )
    private List<Product> products = new ArrayList<>();

    public Company(String companyCode, String companyName, String companyDescription) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public void addLocation(Location location) {
        if(!this.locations.contains(location)) {
            this.locations.add(location);
            location.setCompany(this);
        }
    }

    public void removeLocation(Location location) {
        if(this.locations.contains(location)) {
            this.locations.add(location);
            location.setCompany(null);
        }
    }

    public void addProduct(Product product) {
        if(!this.products.contains(product)) {
            this.products.add(product);
            product.setCompany(this);
        }
    }

    public void removeProduct(Product product) {
        if(this.products.contains(product)) {
            this.products.add(product);
            product.setCompany(null);
        }
    }
}

