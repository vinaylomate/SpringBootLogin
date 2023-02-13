package com.example.demo.master.inventory;

import com.example.demo.master.location.Location;
import com.example.demo.master.product.Product;
import com.example.demo.master.rack.Rack;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class InventoryId implements Serializable {

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

    public InventoryId(Location location, Product product, Rack rack) {
        this.location = location;
        this.product = product;
        this.rack = rack;
    }
}
