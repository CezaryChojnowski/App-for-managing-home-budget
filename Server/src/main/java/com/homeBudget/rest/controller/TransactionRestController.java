package com.homeBudget.rest.controller;

import com.homeBudget.domain.transaction.Transaction;
import com.homeBudget.domain.transaction.TransactionService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@PropertySource("classpath:messages.properties")
public class TransactionRestController {
    public final TransactionService transactionService;
    public final UserService userService;

    @GetMapping("/all")
    public List<Transaction> getAllTransactions(Principal principal){
        String email = userService.findUserByEmail(principal.getName()).getEmail();
        User user = userService.findUserByEmail(email);
        return transactionService.findAllTransactionByUser(user);

    }
}
