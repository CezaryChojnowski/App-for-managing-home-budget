package com.homeBudget.rest;

import com.homeBudget.domain.Category.Category;
import com.homeBudget.domain.Category.CategoryDAO;
import com.homeBudget.domain.User.User;
import com.homeBudget.domain.User.UserDAO;
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

    private final CategoryDAO categoryDAO;
    private final SubcategoryDAO subcategoryDAO;
    private final UserDAO userDAO;
    private final Environment env;

    @PostMapping
    public ResponseEntity createNewCategory(@Valid @RequestBody Category category){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        List<Category> categoriesList = categoryDAO.getAllCategoriesByUser(user);
        if(categoryDAO.checkIfUserHasCategoryWithTheGivenName(categoriesList, category.getNameCategory())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + category.getNameCategory());
        }
        else{
            return ResponseEntity.ok(categoryDAO.createNewCategory(category.getNameCategory(), category.isTypeCategory(), email));
        }
    }

    @RequestMapping("{idCategory}/subcategories")
    @PostMapping
    public ResponseEntity createNewSubcategory(@PathVariable("idCategory") int idCategory,
                                               @Valid @RequestBody Subcategory subcategory){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        List<Subcategory> subcategoriesList = subcategoryDAO.getAllSubcategoryByUser(user);
        if(subcategoryDAO.checkIfUserHasSubcategoryWithTheGivenName(subcategoriesList, subcategory.getNameSubcategory())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + subcategory.getNameSubcategory());
        }
        else{
            return ResponseEntity.ok(subcategoryDAO.createNewSubcategory(subcategory.getNameSubcategory(), idCategory));
        }
    }
}
