package com.example.demo.master.location;

import com.example.demo.master.company.Company;
import com.example.demo.master.company.CompanyRepository;
import com.example.demo.master.storeType.StoreType;
import com.example.demo.master.storeType.StoreTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;
    private final StoreTypeRepository storeTypeRepository;

    public String addLocation(Location location) {
        if(locationRepository.findByLocationCode(location.getLocationCode()) != null) {
            throw new IllegalStateException("Location Already Exists");
        }
        Company company = companyRepository.findByCompanyCode(location.getCompanyCode());
        StoreType storeType = storeTypeRepository.findByStoreTypeName(location.getStoreTypeName());
        storeType.addLocation(location);
        company.addLocation(location);
        locationRepository.save(location);
        return "location added";
    }

    public List<Location> showAllLocations() {
        return locationRepository.findAll();
    }
}
