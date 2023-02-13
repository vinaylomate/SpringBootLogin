package com.example.demo.master.uom;

import com.example.demo.master.rack.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UOMRepository extends JpaRepository<UOM, Long> {

    @Query(value = "SELECT * FROM uom WHERE unit_name = ?1", nativeQuery = true)
    UOM findByUnitName(String unitName);

    @Override
    List<UOM> findAll();

    @Query(value = "SELECT u FROM UOM u WHERE u.id = ?1")
    UOM findByUom(Long uomId);
}
