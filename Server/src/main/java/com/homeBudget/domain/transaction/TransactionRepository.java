package com.homeBudget.domain.transaction;

import com.homeBudget.domain.event.Event;
import com.homeBudget.rest.dto.DailyExpensesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
 List<Transaction> findTransactionsByEvent(Event event);

 @Query(value = "SELECT t FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1")
 List<Transaction> findTransactionsByUserEmail(String email);

 @Query(value = "SELECT count(t) FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1 AND t.date between ?2 and ?3")
 int qwerty(String email, LocalDate firstMonthDay, LocalDate lastMonthDay);

 @Query(value = "SELECT sum(t.amount) FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1 AND t.date between ?2 and ?3 AND t.expenditure = ?4")
 float calculateTheSumOfExpensesForCurrentMonth(String email, LocalDate firstMonthDay, LocalDate lastMonthDay, boolean expenditure);

 @Query(value = "SELECT sum(t.amount) FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1 AND t.date between ?2 and ?3 AND t.expenditure = ?4")
 float calculateTheSumOfInComeForCurrentMonth(String email, LocalDate firstMonthDay, LocalDate lastMonthDay, boolean expenditure);

 @Query(value = "SELECT new com.homeBudget.rest.dto.DailyExpensesDTO(t.date, sum(t.amount)) FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1 AND t.date between ?2 and ?3 AND t.expenditure = true group by t.date")
 List<DailyExpensesDTO> sumAndGroupDailyExpenses(String email, LocalDate firstMonthDay, LocalDate lastMonthDay);

 @Query(value = "SELECT new com.homeBudget.rest.dto.DailyExpensesDTO(t.date, sum(t.amount)) FROM User u, Category c, Subcategory s, Transaction t WHERE t.subcategory=s AND s.category=c AND c.user=u AND u.email = ?1 AND t.date between ?2 and ?3 AND t.expenditure = true group by t.date")
 List<DailyExpensesDTO> sumAndGroupDailyExpensesofTheLast7Days(String email, LocalDate firstMonthDay, LocalDate lastMonthDay);

 Transaction findFirstByOrderByDateAsc();

 Transaction findFirstByExpenditureOrderByDateAsc(boolean expenditure);

 Transaction findFirstByExpenditureOrderByDateDesc(boolean expenditure);
}


