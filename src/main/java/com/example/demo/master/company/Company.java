package com.example.demo.master.company;

import com.example.demo.master.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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
            name = "companyId",
            updatable = false,
            nullable = false
    )
    private Long id;
    @Column(
            name = "companyCode",
            nullable = false
    )
    private String companyCode;
    @Column(
            name = "companyName",
            nullable = false
    )
    private String companyName;

    @Column(
            name = "companyDescription",
            nullable = false
    )
    private String companyDescription;

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "store",
            joinColumns = @JoinColumn(
                    name = "company_id",
                    foreignKey = @ForeignKey(name = "store_company_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "location_id",
                    foreignKey = @ForeignKey(name = "store_location_id_fk")
            )
    )
    public List<Location> locationList;

    public Company(String companyCode, String companyName, String companyDescription) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public void addLocation(Location location) {
        locationList.add(location);
        location.getCompanies().add(this);
    }

    public void removeLocation(Location location) {
        locationList.remove(location);
        location.getCompanies().remove(this);
    }
}

