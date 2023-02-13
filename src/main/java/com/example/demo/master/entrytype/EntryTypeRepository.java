package com.example.demo.master.entrytype;

import com.example.demo.master.rack.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryTypeRepository extends JpaRepository<EntryType, Long> {

    @Query(value = "SELECT * FROM entry_type WHERE entry_type_name = ?1", nativeQuery = true)
    EntryType findByEntryTypeName(String entryTypeName);

    @Override
    List<EntryType> findAll();
}
