package com.homeBudget.rest;

import com.homeBudget.domain.Subcategory.SubcategoryDAO;
import com.homeBudget.domain.User.User;
import com.homeBudget.domain.User.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/subcategories")
public class SubcategoryRestController {

    private final UserDAO userDAO;
    private final SubcategoryDAO subcategoryDAO;

    @GetMapping
    public ResponseEntity getAllSubcategoryByUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        return ResponseEntity.ok(subcategoryDAO.getAllSubcategoryByUser(user));
    }

}
