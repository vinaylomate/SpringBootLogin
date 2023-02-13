package com.example.demo.master.location;

import com.example.demo.master.company.Company;
import com.example.demo.master.entrytype.EntryType;
import com.example.demo.master.storeType.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM location WHERE location_code = ?1", nativeQuery = true)
    Location findByLocationCode(String locationCode);

    @Query(value = "SELECT new com.example.demo.master.location.LocationRegister(l.id,c.companyCode,c.companyName,s.storeTypeName,l.locationCode,l.locationName,l.locationDescription) FROM Location l JOIN l.company c JOIN l.storeType s")
    List<LocationRegister> findAllLocations();

    @Query(value = "SELECT l FROM Location l WHERE l.company.companyCode = ?1")
    List<Location> findLocationByCompany(String companyCode);

    @Query(value = "SELECT e FROM Location l JOIN l.storeType e WHERE l.locationCode = ?1")
    List<StoreType> findStoreTypeByLocationCode(String locationCode);

    @Query(value = "SELECT l FROM Location l WHERE l.company.companyCode = ?1 AND l.storeType.storeTypeName = ?2")
    List<Location> findLocationByStoreTypeAndCompany(String companyCode, String storeTypeName);

    @Query(value = "SELECT COUNT(l) FROM Location l WHERE l.locationCode = ?1")
    Long findCountByLocationCodeAndStoreType(String locationCode);
}
