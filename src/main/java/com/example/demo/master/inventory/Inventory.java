package com.example.demo.master.inventory;

import com.example.demo.master.location.Location;
import com.example.demo.master.product.Product;
import com.example.demo.master.rack.Rack;
import com.example.demo.master.storeType.StoreType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "inventory")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inventory {

    @Id
    @SequenceGenerator(
            name = "inventory_sequence",
            sequenceName = "inventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "inventory_sequence"
    )
    @Column(
            name = "inventory_id",
            updatable = false
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "location_id",
            foreignKey = @ForeignKey(
                    name = "location_inventory_fk"
            )
    )
    private Location location;
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(
                    name = "product_inventory_fk"
            )
    )
    private Product product;
    @ManyToOne
    @JoinColumn(
            referencedColumnName = "rack_id",
            foreignKey = @ForeignKey(
                    name = "rack_inventory_fk"
            )
    )
    private Rack rack;
    @Column(
            name = "batch_code"
    )
    private String batchCode;

    @Column(
            name = "quantity"
    )
    private Double quantity;

    public Inventory(Location location, Product product, Rack rack, String batchCode, Double quantity) {
        this.location = location;
        this.product = product;
        this.rack = rack;
        this.batchCode = batchCode;
        this.quantity = quantity;
    }
}
