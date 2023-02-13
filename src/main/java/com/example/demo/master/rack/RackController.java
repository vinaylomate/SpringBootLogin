package com.example.demo.master.rack;

import com.example.demo.master.producttype.ProductType;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class RackController {

    private final RackService rackService;

    @PostMapping(path = "rack")
    private String addRack(@RequestBody Rack rack) {
        return rackService.addRack(rack);
    }

    @GetMapping(path = "getRack/{categoryName}")
    public List<Rack> showRackByCategoryName(@PathVariable("categoryName") String categoryName) {
        return rackService.showRackByCategoryName(categoryName);
    }

    @GetMapping(path = "getRackLocation/{categoryName}/{locationCode}")
    public List<Rack> showRackByCategoryName(@PathVariable("locationCode") String locationCode,
                                             @PathVariable("categoryName") String categoryName) {
        return rackService.showRackByCategoryAndLocation(locationCode, categoryName);
    }

    @GetMapping(path = "allRack")
    public List<RackRegister> showAllRack() {
        return rackService.showAllRack();
    }

    @GetMapping(path = "getRackById/{rackId}")
    public Rack showRackById(@PathVariable("rackId") Long rackId) {
        return rackService.showRackById(rackId);
    }
}
