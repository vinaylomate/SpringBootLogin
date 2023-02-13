package com.example.demo.master.rack;

import com.example.demo.master.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RackRepository extends JpaRepository<Rack, Long> {

    @Query(value = "SELECT * FROM rack WHERE rack_number = ?1", nativeQuery = true)
    Rack findByRackNumber(String rackNumber);

    @Query(value = "SELECT r FROM Rack r WHERE r.category.categoryName = ?1")
    List<Rack> findRackByCategory(String productCategoryName);

    @Query(value = "SELECT r FROM Rack r WHERE r.location.locationCode = ?1 AND r.category.categoryName = ?2")
    List<Rack> findRackByCategoryAndLocation(String locationCode, String productCategoryName);

    @Query(value = "SELECT new com.example.demo.master.rack.RackRegister(r.id, l.locationCode, l.locationName, c.categoryName, r.rackNumber) FROM Rack r JOIN r.location l JOIN r.category c")
    List<RackRegister> findAllRack();

    @Query(value = "SELECT l FROM Rack rk JOIN rk.location l WHERE rk.location.locationCode = ?1")
    Location findLocationByRack(String rackNumber);

    @Query(value = "SELECT rk FROM Rack rk WHERE rk.id = ?1")
    Rack findByRackId(Long rackId);
}
