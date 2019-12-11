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
@CrossOrigin
public class TransactionRestController {
    public final TransactionService transactionService;
    public final UserService userService;

    @GetMapping("/all")
    public List<Transaction> getAllTransactions(Principal principal){
        String email = userService.findUserByEmail(principal.getName()).getEmail();
        return transactionService.getAllTransactionsByUserEmail(email);
    }

    @PostMapping()
    public ResponseEntity addTransaction(@RequestParam(value = "event", required = false) Long eventID ,
                                      @RequestParam(value = "person", required = false) Long personID,
                                      @RequestParam(value = "wallet", required = true) int walletID,
                                      @RequestParam(value="subcategory", required = true) int subcategoryID,
                                      @RequestBody Transaction transaction,
                                              Principal principal){
        String email = userService.findUserByEmail(principal.getName()).getEmail();
        return ResponseEntity.ok(transactionService.createNewTransaction(transaction.getAmount(), transaction.getComment(), transaction.getDate(), subcategoryID, walletID, eventID, personID, transaction.isExpenditure()));
    }

    @GetMapping("/{idEvent}")
    public ResponseEntity getAllTransactionsByEvent(Principal principal,
                                                    @PathVariable(value = "idEvent") long idEvent){
        return ResponseEntity.ok(transactionService.findTransactionByEvent(idEvent));
    }

    @RequestMapping(value = "/{idTransactions}", method = RequestMethod.DELETE)
    public void deleteWallet(@PathVariable int idTransactions, Principal principal){
        transactionService.deleteTransactions(idTransactions);
    }
}
