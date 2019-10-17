package com.homeBudget.rest;

import com.homeBudget.domain.transaction.TransactionDAO;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@PropertySource("classpath:messages.properties")
public class TransactionRestController {
    public final TransactionDAO transactionDAO;
    public final UserDAO userDAO;
    @GetMapping
    public ResponseEntity getAllTransactionByUser(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam(value = "finishDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate finishDate){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        if(transactionDAO.checkIfDateIsNull(startDate, finishDate)){
            return ResponseEntity.ok(transactionDAO.findAllTransactionByUser(user));
        }
            return ResponseEntity.ok(transactionDAO.findAllTransactionsByDate(startDate, finishDate, user));
    }
}
