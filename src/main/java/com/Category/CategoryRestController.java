package com.Category;

import com.User.User;
import com.User.UserDAO;
import com.error.RecordExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryDAO categoryDAO;
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
}
