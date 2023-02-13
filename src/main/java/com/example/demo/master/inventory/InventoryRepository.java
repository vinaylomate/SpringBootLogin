package com.example.demo.master.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT * FROM inventory WHERE product_product_id = ?1", nativeQuery = true)
    List<Inventory> findByProductId(Long id);

    @Query(value = "SELECT * FROM inventory WHERE rack_rack_id = ?1", nativeQuery = true)
    List<Inventory> findByRackId(Long id);

    @Query(value = "SELECT * FROM inventory WHERE batch_code = ?1", nativeQuery = true)
    Inventory findByBatchCode(String batchCode);

    @Query(value = "SELECT SUM(i.quantity) FROM Inventory i WHERE i.product.sageCode = ?2 AND i.location.locationCode = ?1")
    Long findQuantityByProduct(String locationCode, String sageCode);

    @Query(value = "SELECT SUM(i.quantity) FROM Inventory i WHERE i.product.sageCode = ?2 AND i.location.locationCode = ?1 AND i.rack.id = ?3")
    Long findQuantityByRack(String locationCode, String sageCode, Long rackId);

    @Query(value = "SELECT SUM(i.quantity) FROM Inventory i WHERE i.product.sageCode = ?2 AND i.location.locationCode = ?1 AND i.rack.id = ?3 AND i.batchCode = ?4")
    Long findQuantityByBatchCode(String locationCode, String sageCode, Long rackId, String batchCode);

    @Override
    List<Inventory> findAll();

    @Query(value = "SELECT SUM(i.quantity) FROM Inventory i WHERE i.product.sageCode = ?1")
    Long findQuantityBySage(String sageCode);

    @Query(value = "SELECT i FROM Inventory i WHERE i.location.locationCode = ?1 AND i.rack.id = ?2")
    List<Inventory> findBatchCode(String locationCode, Long rackId);

    @Query(value = "SELECT i FROM Inventory i WHERE i.product.sageCode = ?2 AND i.location.locationCode = ?1 AND i.rack.id = ?3")
    List<Inventory> findBatchOut(String locationCode, String sageCode, Long rackId);
}
