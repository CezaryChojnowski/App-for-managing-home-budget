package com.homeBudget.rest;

import com.homeBudget.domain.Subcategory.Subcategory;
import com.homeBudget.domain.Subcategory.SubcategoryDAO;
import com.homeBudget.domain.User.User;
import com.homeBudget.domain.User.UserDAO;
import com.homeBudget.configuration.error.RecordExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubcategoryRestController {

    private final UserDAO userDAO;
    private final SubcategoryDAO subcategoryDAO;
    private final Environment env;

    @RequestMapping("categories/{idCategory}/subcategories")
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
