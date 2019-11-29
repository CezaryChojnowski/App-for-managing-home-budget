package com.homeBudget.rest.controller;

import com.homeBudget.domain.subcategory.SubcategoryService;
import com.homeBudget.domain.transaction.Transaction;
import com.homeBudget.domain.transaction.TransactionService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subcategories")
public class SubcategoryRestController {

    private final UserService userService;
    private final SubcategoryService subcategoryService;
    private final TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity getAllSubcategoryByUser(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        return ResponseEntity.ok(subcategoryService.getAllSubcategoryByUser(user));
    }
    @PostMapping("{idSubcategory}/transactions")
    public ResponseEntity createNewTransaction(@PathVariable("idSubcategory") int idSubcategory,
                                               @RequestParam(value = "idWallet") int idWallet,
                                               @RequestParam(value = "idEvent", required = false) Long idEvent,
                                               @RequestParam(value = "idPerson", required = false) Long idPerson,
                                               @Valid @RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.createNewTransaction(transaction.getAmount(),transaction.getComment(),transaction.getDate(), idSubcategory, idWallet, idEvent, idPerson));
    }

}
