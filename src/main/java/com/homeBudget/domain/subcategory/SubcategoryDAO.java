package com.homeBudget.domain.subcategory;

import com.homeBudget.domain.category.Category;
import com.homeBudget.domain.category.CategoryRepository;
import com.homeBudget.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryDAO {

    public final SubcategoryRepository subcategoryRepository;
    public final CategoryRepository categoryRepository;

    public Subcategory createNewSubcategory(String nameCategory, int idCategory){
        Subcategory subcategory = new Subcategory.Builder()
                .nameSubcategory(nameCategory)
                .category(categoryRepository.findCategoryByIdCategory(idCategory))
                .build();
        return subcategoryRepository.save(subcategory);
    }

    public List<Subcategory> getAllSubcategoryByUser(User user){
        List<Category> categoryList = categoryRepository.findCategoriesByUser(user);
        List<Subcategory> subcategoryList = new ArrayList<>();
        for(Category c: categoryList){
            subcategoryList.addAll(subcategoryRepository.findAllByCategory(c));
        }
        return subcategoryList;
    }

    public boolean checkIfUserHasSubcategoryWithTheGivenName(List<Subcategory> userSubCategories, String newSubcategoryName){
        return userSubCategories.stream().anyMatch(o -> o.getNameSubcategory().equals(newSubcategoryName));
    }

}
