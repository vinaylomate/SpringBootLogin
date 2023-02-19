package com.example.demo.master.location;

import com.example.demo.master.company.Company;
import com.example.demo.master.entrytype.EntryType;
import com.example.demo.master.inventory.Inventory;
import com.example.demo.master.product.Product;
import com.example.demo.master.rack.Rack;
import com.example.demo.master.storeType.StoreType;
import com.example.demo.master.transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    @Transient
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "company_id",
            referencedColumnName = "company_id",
            foreignKey = @ForeignKey(
                    name = "company_location_fk"
            )
    )
    private Company company;
    @JsonIgnore
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

    @JsonIgnore
    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            mappedBy = "location"
    )
    private List<Rack> racks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "location"
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Location(String companyCode, String storeTypeName, String locationCode, String locationName, String locationDescription) {
        this.companyCode = companyCode;
        this.storeTypeName = storeTypeName;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }

    public void addRack(Rack rack) {
        if(!this.racks.contains(rack)) {
            this.racks.add(rack);
            rack.setLocation(this);
        }
    }

    public void removeRack(Rack rack) {
        if(this.racks.contains(rack)) {
            this.racks.add(rack);
            rack.setLocation(null);
        }
    }

    public void addTransaction(Transaction transaction) {
        if(!this.transactions.contains(transaction)) {
            this.transactions.add(transaction);
            transaction.setLocation(this);
        }
    }

    public void removeTransaction(Transaction transaction) {
        if(this.transactions.contains(transaction)) {
            this.transactions.remove(transaction);
            transaction.setLocation(null);
        }
    }
}
