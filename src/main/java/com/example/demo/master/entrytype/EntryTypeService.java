package com.example.demo.master.entrytype;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EntryTypeService {

    private final EntryTypeRepository entryTypeRepository;

    public String addEntryType(EntryType entryType) {
        entryTypeRepository.save(entryType);
        return "entry type added";
    }

    public List<EntryType> findAllEntryType() {
        return entryTypeRepository.findAll();
    }
}
