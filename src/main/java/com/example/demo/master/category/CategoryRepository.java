package com.example.demo.master.category;

import com.example.demo.master.storeType.StoreType;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query(value = "SELECT * FROM category WHERE category_name = ?1", nativeQuery = true)
    Category findByCategoryName(String categoryName);

    @Override
    List<Category> findAll();
}
