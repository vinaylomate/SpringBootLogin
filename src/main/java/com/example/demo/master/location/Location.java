package com.example.demo.master.location;

import com.example.demo.master.company.Company;
import com.example.demo.master.storeType.StoreType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
public class Location {

    @Id
    @SequenceGenerator(
            name = "location_sequence",
            sequenceName = "location_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "location_sequence"
    )
    @Column(
            name = "location_id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "location_company_code",
            nullable = false
    )
    private String companyCode;
    @Transient
    private String storeTypeName;
    @Column(
            name = "location_code"
    )
    private String locationCode;
    @Column(
            name = "location_name"
    )
    private String locationName;
    @Column(
            name = "location_description"
    )
    private String locationDescription;
    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "locationList"
    )
    private List<Company> companies = new ArrayList<>();
    @ManyToOne
    @JoinColumn(
            name = "store_type_id",
            nullable = false,
            referencedColumnName = "store_type_id",
            foreignKey = @ForeignKey(
                    name = "location_store_type_fk"
            )
    )
    private StoreType storeType;

    public Location(String companyCode, String storeTypeName, String locationCode, String locationName, String locationDescription) {
        this.companyCode = companyCode;
        this.storeTypeName = storeTypeName;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }
}
