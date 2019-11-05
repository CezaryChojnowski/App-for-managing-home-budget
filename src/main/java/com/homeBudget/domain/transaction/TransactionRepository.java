package com.homeBudget.domain.transaction;

import com.homeBudget.domain.subcategory.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
 List<Transaction> findAllBySubcategory(Subcategory subcategory);
 List<Transaction> findTransactionsByDateBetween(LocalDate startDate, LocalDate finishDate);
 }
