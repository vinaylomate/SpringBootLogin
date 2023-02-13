package com.example.demo.master.location;

import com.example.demo.master.entrytype.EntryType;
import com.example.demo.master.storeType.StoreType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping(path = "location")
    public String addLocation(@RequestBody Location location) {

        return locationService.addLocation(location);
    }

    @GetMapping(path = "allLocations")
    public List<LocationRegister> showAllLocations() {
        return locationService.showAllLocations();
    }

    @GetMapping(path = "getLocation/{companyCode}")
    public List<Location> showLocationByCompany(@PathVariable("companyCode") String companyCode) {
        List<Location> locations = locationService.showLocationByCompany(companyCode);
        return locations;
    }

    @GetMapping(path = "getLocation/{companyCode}/{storeType}")
    public List<Location> showLocationByCompany(@PathVariable("companyCode") String companyCode,
                                                @PathVariable("storeType") String storeTypeName) {
        List<Location> locations = locationService.showLocationByStoreTypeAndCompany(companyCode, storeTypeName);
        return locations;
    }

    @GetMapping(path = "getStoreType/{locationCode}")
    public List<StoreType> showStoreTypeByLocation(@PathVariable("locationCode") String locationCode) {
        return locationService.showStoreTypeByLocation(locationCode);
    }

    @GetMapping(path = "getCount/{locationCode}")
    public Long showCountByLocationCode(@PathVariable("locationCode") String locationCode) {
        return locationService.showCountByLocationCode(locationCode);
    }

    @GetMapping(path = "getLocationByCode/{locationCode}")
    public Location showLocationByCode(@PathVariable("locationCode") String locationCode) {
        return locationService.showLocationByCode(locationCode);
    }

}
