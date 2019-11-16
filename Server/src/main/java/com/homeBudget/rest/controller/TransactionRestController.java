package com.homeBudget.rest.controller;

import com.homeBudget.domain.transaction.TransactionService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
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
    public final TransactionService transactionService;
    public final UserService userService;
    @GetMapping
    public ResponseEntity getAllTransactionByUser(@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam(value = "finishDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate finishDate){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        if(transactionService.checkIfDateIsNull(startDate, finishDate)){
            return ResponseEntity.ok(transactionService.findAllTransactionByUser(user));
        }
            return ResponseEntity.ok(transactionService.findAllTransactionsByDate(startDate, finishDate, user));
    }
}
