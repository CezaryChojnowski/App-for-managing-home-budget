package com.homeBudget.domain.category;

import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import com.homeBudget.rest.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final Environment env;

    public Category createNewCategory(String nameCategory, String emailUser){
        Category category = new Category.CategoryBuilder()
                .name(nameCategory)
                .user(userRepository.findUserByEmail(emailUser).get())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategoriesByUser(User user){
        return categoryRepository.findCategoriesByUser(user);
    }

    public boolean checkIfUserHasCategoryWithTheGivenName(List<Category> userCategories, String newCategoryName){
        return userCategories.stream().anyMatch(o -> o.getName().equals(newCategoryName));
    }

    public CategoryDTO addCategory(List<Category> categoriesList, Category category, String email){
        if(checkIfUserHasCategoryWithTheGivenName(categoriesList, category.getName())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + category.getName());
        }
        else{
            createNewCategory(category.getName(), email);
            return new CategoryDTO(category);
        }
    }

}
