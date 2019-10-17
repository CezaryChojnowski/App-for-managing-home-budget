package com.homeBudget.domain.category;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryDAO {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Category createNewCategory(String nameCategory, boolean typeCategory, String emailUser){
        Category category = new Category.Builder()
                .nameCategory(nameCategory)
                .typeCategory(typeCategory)
                .user(userRepository.findUserByEmail(emailUser).get())
                .build();
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategoriesByUser(User user){
        return categoryRepository.findCategoriesByUser(user);
    }

    public boolean checkIfUserHasCategoryWithTheGivenName(List<Category> userCategories, String newCategoryName){
        return userCategories.stream().anyMatch(o -> o.getNameCategory().equals(newCategoryName));
    }
}
