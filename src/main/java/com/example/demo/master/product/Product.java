package com.example.demo.master.product;

import com.example.demo.master.category.Category;
import com.example.demo.master.company.Company;
import com.example.demo.master.inventory.Inventory;
import com.example.demo.master.location.Location;
import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.transaction.Transaction;
import com.example.demo.master.uom.UOM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @Transient
    private String companyCode;
    @Transient
    private String categoryName;
    @Transient
    private String productTypeName;
    @Column(
            name = "sage_code"
    )
    private String sageCode;
    @Column(
            name = "focus_code",
            nullable = false
    )
    private String focusCode;
    @Column(
            name = "brand"
    )
    private String brand;
    @Column(
            name = "product_name"
    )
    private String productName;
    @Column(
            name = "reorder_level_quantity"
    )
    private Long reorderLevelQuantity;
    @Transient
    private String packSize;
    @Column(
            name = "date"
    )
    private Integer productLifetime;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "category_id",
            foreignKey = @ForeignKey(
                    name = "category_product_fk"
            )
    )
    private Category category;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "product_type_id",
            nullable = false,
            referencedColumnName = "product_type_id",
            foreignKey = @ForeignKey(
                    name = "category_product_type_fk"
            )
    )
    private ProductType productType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "company_id",
            nullable = false,
            referencedColumnName = "company_id",
            foreignKey = @ForeignKey(
                    name = "company_product_fk"
            )
    )
    private Company company;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "uom_id",
            referencedColumnName = "uom_id",
            foreignKey = @ForeignKey(
                    name = "uom_product_fk"
            )
    )
    private UOM uom;
    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "product"
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Product(String companyCode,
                   String categoryName,
                   String productTypeName,
                   String sageCode,
                   String focusCode,
                   String brand,
                   String productName,
                   Long reorderLevelQuantity,
                   String packSize,
                   Integer productLifetime) {
        this.companyCode = companyCode;
        this.categoryName = categoryName;
        this.productTypeName = productTypeName;
        this.sageCode = sageCode;
        this.focusCode = focusCode;
        this.brand = brand;
        this.productName = productName;
        this.reorderLevelQuantity = reorderLevelQuantity;
        this.packSize = packSize;
        this.productLifetime = productLifetime;
    }

    public void addTransaction(Transaction transaction) {
        if(!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
            transaction.setProduct(this);
        }
    }

    public void removeTransaction(Transaction transaction) {
        if(this.transactions.contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.setProduct(null);
        }
    }
}
