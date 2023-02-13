package com.example.demo.master.uom;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UOMService {

    private final UOMRepository uomRepository;

    public String addUOM(UOM uom) {

        uomRepository.save(uom);
        return "uom added";
    }

    public List<UOM> findAllUOM() {
        return uomRepository.findAll();
    }
}
