package com.example.demo.master.rack;

import com.example.demo.master.category.Category;
import com.example.demo.master.category.CategoryRepository;
import com.example.demo.master.location.Location;
import com.example.demo.master.location.LocationRepository;
import com.example.demo.master.producttype.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RackService {

    private final RackRepository rackRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;

    public String addRack(Rack rack) {
        if(rackRepository.findByRackNumber(rack.getRackNumber()) != null && rackRepository.findLocationByRack(rack.getRackNumber()) != null) {
            throw new IllegalStateException("rack already exists");
        }
        Category category = categoryRepository.findByCategoryName(rack.getCategoryName());
        Location location = locationRepository.findByLocationCode(rack.getLocationCode());
        category.addRack(rack);
        location.addRack(rack);
        rackRepository.save(rack);
        return "rack added";
    }

    public List<Rack> showRackByCategoryName(String categoryName) {

        return rackRepository.findRackByCategory(categoryName);
    }

    public List<RackRegister> showAllRack() {

        return rackRepository.findAllRack();
    }

    public List<Rack> showRackByCategoryAndLocation(String locationCode, String categoryName) {
        return rackRepository.findRackByCategoryAndLocation(locationCode, categoryName);
    }

    public Rack showRackById(Long rackId) {
        return rackRepository.findByRackId(rackId);
    }
}
