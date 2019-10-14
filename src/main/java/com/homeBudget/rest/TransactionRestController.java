package com.homeBudget.rest;

import com.homeBudget.domain.Transaction.TransactionDAO;
import com.homeBudget.domain.User.User;
import com.homeBudget.domain.User.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@PropertySource("classpath:messages.properties")
public class TransactionRestController {
    public final TransactionDAO transactionDAO;
    public final UserDAO userDAO;

    @GetMapping
    public ResponseEntity getAllTransactionByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        return ResponseEntity.ok(transactionDAO.findAllTransactionByUser(user));
    }
}
