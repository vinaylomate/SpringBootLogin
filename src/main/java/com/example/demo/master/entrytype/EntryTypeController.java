package com.example.demo.master.entrytype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/")
@AllArgsConstructor
public class EntryTypeController {

    private final EntryTypeService entryTypeService;

    @PostMapping(path = "addEntryType")
    public String addEntryType(@RequestBody EntryType entryType) {
        return entryTypeService.addEntryType(entryType);
    }

    @GetMapping(path = "allEntryType")
    public List<EntryType> findAllEntryType() {
        return entryTypeService.findAllEntryType();
    }
}
