package com.homeBudget.domain.Transaction;

import com.homeBudget.domain.Subcategory.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
 List<Transaction> findAllBySubcategory(Subcategory subcategory);
 }
