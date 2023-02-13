package com.example.demo.master.uom;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class UOMController {

    private final UOMService uomService;

    @PostMapping(path = "uom")
    public String addUOM(@RequestBody UOM uom) {
        return uomService.addUOM(uom);
    }

    @GetMapping(path = "allUOM")
    public List<UOM> findAllUOM() {
        return uomService.findAllUOM();
    }
}
