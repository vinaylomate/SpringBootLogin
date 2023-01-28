package com.example.demo.master.location;

import com.example.demo.master.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM location WHERE location_code = ?1", nativeQuery = true)
    Location findByLocationCode(String locationCode);

    @Query(value = "SELECT new com.example.demo.master.location.LocationRegister(l.id,c.companyName,s.storeTypeName,l.locationCode,l.locationName,l.locationDescription) FROM Location l JOIN l.companies c JOIN l.storeType s")
    List<Location> findAllLocations();
}
