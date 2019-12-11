package com.homeBudget.rest.controller;

import com.homeBudget.domain.category.Category;
import com.homeBudget.domain.category.CategoryService;
import com.homeBudget.domain.subcategory.Subcategory;
import com.homeBudget.domain.subcategory.SubcategoryService;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.configuration.error.RecordExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryRestController {

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity createNewCategory(@Valid @RequestBody Category category,
                                            Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        List<Category> categoriesList = categoryService.getAllCategoriesByUser(user);
        return ResponseEntity.ok(categoryService.addCategory(categoriesList, category, userService.getEmailByAuthentication()));
    }

    @RequestMapping("{idCategory}/subcategories")
    @PostMapping
    public ResponseEntity createNewSubcategory(@PathVariable("idCategory") int idCategory,
                                               @Valid @RequestBody Subcategory subcategory, Principal principal){
        String email = userService.findUserByEmail(principal.getName()).getEmail();
        User user = userService.findUserByEmail(email);
        List<Subcategory> subcategoriesList = subcategoryService.getAllSubcategoriesByUserEmail(user.getEmail());
        return ResponseEntity.ok(subcategoryService.addSubcategory(subcategoriesList, subcategory, idCategory));
    }

    @GetMapping
    @RequestMapping("/all")
    public List<Category> getUserCategories(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        System.out.println(categoryService.getAllCategoriesByUser(user));
        return categoryService.getAllCategoriesByUser(user);
    }
}
