package com.example.demo.master.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT t FROM Transaction t WHERE t.location.locationCode = ?1 AND t.product.sageCode = ?2 AND t.createdAt >= ?3 AND t.createdAt <= ?4 ORDER BY t.createdAt")
    List<Transaction> findTransaction(String locationCode, String sageCode, String from, String to);

    @Query(value = "SELECT t FROM Transaction t WHERE t.category.categoryName = ?1 AND t.createdAt >= ?2 AND t.createdAt <= ?3 ORDER BY t.createdAt")
    List<Transaction> findTransactionType(String categoryName, String from, String to);

    @Query(value = "SELECT t FROM Transaction t WHERE t.location.locationCode = ?1 AND t.createdAt >= ?2 AND t.createdAt <= ?3 ORDER BY t.createdAt")
    List<Transaction> findTransactionLocation(String locationCode, String from, String to);

    @Override
    List<Transaction> findAll();

    @Query(value = "SELECT * FROM transaction WHERE created_at = ?2 AND sage_code = ?1 ORDER BY created_at DESC", nativeQuery = true)
    List<Transaction> findSingleTransaction(String sageCode, String date);
}
