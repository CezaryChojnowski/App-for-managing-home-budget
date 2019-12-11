package com.homeBudget.domain.transaction;

import com.homeBudget.domain.event.Event;
import com.homeBudget.domain.subcategory.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
 List<Transaction> findAllBySubcategory(Subcategory subcategory);
 List<Transaction> findTransactionsByDateBetween(LocalDate startDate, LocalDate finishDate);
 List<Transaction> findTransactionsByEvent(Event event);
 @Query(value = "SELECT t FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1")
 List<Transaction> findTransactionsByUserEmail(String email);
 }
