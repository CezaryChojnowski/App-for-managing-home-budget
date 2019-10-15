package com.homeBudget.rest;

import com.homeBudget.domain.subcategory.SubcategoryDAO;
import com.homeBudget.domain.transaction.Transaction;
import com.homeBudget.domain.transaction.TransactionDAO;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subcategories")
public class SubcategoryRestController {

    private final UserDAO userDAO;
    private final SubcategoryDAO subcategoryDAO;
    private final TransactionDAO transactionDAO;

    @GetMapping
    public ResponseEntity getAllSubcategoryByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        return ResponseEntity.ok(subcategoryDAO.getAllSubcategoryByUser(user));
    }
    @PostMapping("{idSubcategory}/transactions")
    public ResponseEntity createNewTransaction(@PathVariable("idSubcategory") int idSubcategory,
                                               @RequestParam(value = "idWallet") int idWallet,
                                               @RequestParam(value = "idEvent", required = false) Long idEvent,
                                               @RequestParam(value = "idPerson", required = false) Long idPerson,
                                               @Valid @RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionDAO.createNewTransaction(transaction.getAmount(),transaction.getComment(),transaction.getDateTransaction(), idSubcategory, idWallet, idEvent, idPerson));
    }

}
