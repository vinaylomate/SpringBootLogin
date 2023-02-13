package com.example.demo.master.transaction;

import com.example.demo.master.category.Category;
import com.example.demo.master.entrytype.EntryType;
import com.example.demo.master.location.Location;
import com.example.demo.master.product.Product;
import com.example.demo.master.producttype.ProductType;
import com.example.demo.master.uom.UOM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
public class Transaction implements Serializable {

    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    @Column(
            name = "transaction_id",
            updatable = false
    )
    private Long id;
    @Transient
    private String companyCode;
    @Transient
    private String categoryName;
    @Transient
    private String locationCode;
    @Transient
    private String productTypeName;
    @Column(
            name = "created_at"
    )
    private String createdAt;
    @Transient
    private String entryTypeName;
    @Column(
            name = "doc_no"
    )
    private String docNo;
    @Transient
    private String sageCode;
    @Column(
            name = "sage_reference"
    )
    private String sageReference;
    @Column(
            name = "notes"
    )
    private String notes;
    @Transient
    private Long rackId;
    @Column(
            name = "batch_code"
    )
    private String batchCode;
    @Transient
    private Long unitId;
    @Column(
            name = "quantity"
    )
    private Double quantity;
    @Column(
            name = "in"
    )
    private Double in;
    @Column(
            name = "out"
    )
    private Double out;
    @Column(
            name = "total_quantity"
    )
    private Double totalQuantity;
    @Column(
            name = "expiry_date"
    )
    private String expiryDate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "entry_type_id",
            referencedColumnName = "entry_type_id",
            foreignKey = @ForeignKey(
                    name = "transaction_entry_type_fk"
            )
    )
    private EntryType entryType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "uom_id",
            referencedColumnName = "uom_id",
            foreignKey = @ForeignKey(
                    name = "transaction_unit_id_fk"
            )
    )
    private UOM uom;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "product_type_id",
            referencedColumnName = "product_type_id",
            foreignKey = @ForeignKey(
                    name = "transaction_product_type_fk"
            )
    )
    private ProductType productType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "sage_code",
            referencedColumnName = "sage_code",
            foreignKey = @ForeignKey(
                    name = "transaction_product_fk"
            )
    )
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "location_code",
            referencedColumnName = "location_code",
            foreignKey = @ForeignKey(
                    name = "transaction_location_fk"
            )
    )
    private Location location;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "category_name",
            referencedColumnName = "category_name",
            foreignKey = @ForeignKey(
                    name = "transaction_category_fk"
            )
    )
    private Category category;

    public Transaction(String companyCode,
                       String categoryName,
                       String locationCode,
                       String createdAt,
                       String productTypeName,
                       String entryTypeName,
                       Long unitId,
                       String docNo,
                       String sageCode,
                       String sageReference,
                       String notes,
                       Long rackId,
                       String batchCode,
                       Double quantity,
                       Double in,
                       Double out,
                       Double totalQuantity,
                       String expiryDate) {
        this.companyCode = companyCode;
        this.categoryName = categoryName;
        this.locationCode = locationCode;
        this.createdAt = createdAt;
        this.productTypeName = productTypeName;
        this.entryTypeName = entryTypeName;
        this.unitId = unitId;
        this.docNo = docNo;
        this.sageCode = sageCode;
        this.sageReference = sageReference;
        this.notes = notes;
        this.rackId = rackId;
        this.batchCode = batchCode;
        this.quantity = quantity;
        this.in = in;
        this.out = out;
        this.totalQuantity = totalQuantity;
        this.expiryDate = expiryDate;
    }
}
