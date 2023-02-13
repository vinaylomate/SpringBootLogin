package com.example.demo.master.location;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocationRegister {

    private Long id;
    private String companyCode;
    private String companyName;
    private String storeTypeName;
    private String locationCode;
    private String locationName;
    private String locationDescription;

    public LocationRegister(Long id, String companyCode, String companyName, String storeTypeName, String locationCode, String locationName, String locationDescription) {
        this.id = id;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.storeTypeName = storeTypeName;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
    }
}
