package com.example.demo.master.storeType;

import com.example.demo.master.location.LocationRepository;
import com.example.demo.master.location.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StoreTypeServices {

    private final StoreTypeRepository storeTypeRepository;

    public String addStoreType(StoreType storeType) {
        if(storeTypeRepository.findByStoreTypeName(storeType.getStoreTypeName()) != null) {
            throw new IllegalStateException("Store type already exists");
        }
        storeTypeRepository.save(storeType);
        return "Store Type Added";
    }

    public List<StoreType> showAllStoreType() {
        return storeTypeRepository.findAll();
    }
}
