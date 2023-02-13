package com.example.demo.master.rack;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RackRegister {

    private Long id;
    private String locationCode;
    private String locationName;
    private String categoryName;
    private String rackNumber;

    public RackRegister(Long id, String locationCode, String locationName, String categoryName, String rackNumber) {
        this.id = id;
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.categoryName = categoryName;
        this.rackNumber = rackNumber;
    }
}
