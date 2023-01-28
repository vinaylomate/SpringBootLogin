package com.example.demo.master.location;

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
    public List<Location> showAllLocations() {
        return locationService.showAllLocations();
    }
}
