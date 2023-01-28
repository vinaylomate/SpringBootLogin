package com.example.demo.master.storeType;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
@AllArgsConstructor
public class StoreTypeController {

    private final StoreTypeServices storeTypeServices;

    @PostMapping(path = "storetype")
    public String addStoreType(@RequestBody StoreType storeType) {

        return storeTypeServices.addStoreType(storeType);
    }

    @GetMapping("allStoretype")
    public List<StoreType> showAllStoreType() {
        return storeTypeServices.showAllStoreType();
    }
}
