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
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;
    private final SubcategoryService subcategoryService;
    private final UserService userService;
    private final Environment env;

    @PostMapping
    public ResponseEntity createNewCategory(@Valid @RequestBody Category category){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        List<Category> categoriesList = categoryService.getAllCategoriesByUser(user);
        if(categoryService.checkIfUserHasCategoryWithTheGivenName(categoriesList, category.getNameCategory())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + category.getNameCategory());
        }
        else{
            return ResponseEntity.ok(categoryService.createNewCategory(category.getNameCategory(), category.isTypeCategory(), email));
        }
    }

    @RequestMapping("{idCategory}/subcategories")
    @PostMapping
    public ResponseEntity createNewSubcategory(@PathVariable("idCategory") int idCategory,
                                               @Valid @RequestBody Subcategory subcategory){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        List<Subcategory> subcategoriesList = subcategoryService.getAllSubcategoryByUser(user);
        if(subcategoryService.checkIfUserHasSubcategoryWithTheGivenName(subcategoriesList, subcategory.getNameSubcategory())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + subcategory.getNameSubcategory());
        }
        else{
            return ResponseEntity.ok(subcategoryService.createNewSubcategory(subcategory.getNameSubcategory(), idCategory));
        }
    }
}
