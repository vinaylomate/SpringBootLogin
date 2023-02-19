package com.example.demo.master.rack;

import com.example.demo.master.category.Category;
import com.example.demo.master.inventory.Inventory;
import com.example.demo.master.location.Location;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rack")
@Getter
@Setter
@NoArgsConstructor
public class Rack {

    @Id
    @SequenceGenerator(
            name = "rack_sequence",
            sequenceName = "rack_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rack_sequence"
    )
    @Column(
            name = "rack_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "rack_number"
    )
    private String rackNumber;
    @Transient
    private String categoryName;
    @Transient
    private String locationCode;
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false,
            referencedColumnName = "category_id",
            foreignKey = @ForeignKey(
                    name = "category_rack_fk"
            )
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
            name = "location_id",
            nullable = false,
            referencedColumnName = "location_id",
            foreignKey = @ForeignKey(
                    name = "location_rack_fk"
            )
    )
    private Location location;

    public Rack(String rackNumber, String categoryName, String locationCode) {
        this.rackNumber = rackNumber;
        this.categoryName = categoryName;
        this.locationCode = locationCode;
    }
}
