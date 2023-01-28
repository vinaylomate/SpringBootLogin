package com.example.demo.master.storeType;

import com.example.demo.master.company.Company;
import com.example.demo.master.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "store_type")
@Getter
@Setter
@NoArgsConstructor
public class StoreType {

    @Id
    @SequenceGenerator(
            name = "store_type_sequence",
            sequenceName = "store_type_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "store_type_sequence"
    )
    @Column(
            name = "store_type_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "store_type_name",
            nullable = false
    )
    private String storeTypeName;

    @JsonIgnore
    @OneToMany(
            mappedBy = "storeType",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY    // this will not join the book table to any table
    )
    private List<Location> locations = new ArrayList<>();
    public StoreType(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public void addLocation(Location location) {
        if(!this.locations.contains(location)) {
            this.locations.add(location);
            location.setStoreType(this);
        }
    }

    public void removeLocation(Location location) {
        if(!this.locations.contains(location)) {
            this.locations.add(location);
            location.setStoreType(this);
        }
    }
}
