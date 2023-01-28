package com.example.demo.master.storeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreTypeRepository extends JpaRepository<StoreType,Long> {

    @Query(value = "SELECT * FROM store_type WHERE store_type_name = ?1", nativeQuery = true)
    StoreType findByStoreTypeName(String storeTypeName);

    @Override
    List<StoreType> findAll();
}
