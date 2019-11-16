package com.homeBudget.domain.subcategory;

import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.domain.category.Category;
import com.homeBudget.domain.category.CategoryRepository;
import com.homeBudget.domain.user.User;
import com.homeBudget.rest.dto.SubcategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

    public final SubcategoryRepository subcategoryRepository;
    public final CategoryRepository categoryRepository;
    private final Environment env;

    public Subcategory createNewSubcategory(String nameCategory, int idCategory){
        Subcategory subcategory = new Subcategory.SubcategoryBuilder()
                .name(nameCategory)
                .category(categoryRepository.findCategoryById(idCategory))
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
        return userSubCategories.stream().anyMatch(o -> o.getName().equals(newSubcategoryName));
    }

    public SubcategoryDTO addSubcategory(List<Subcategory> subcategoriesList, Subcategory subcategory, int id){
        if(checkIfUserHasSubcategoryWithTheGivenName(subcategoriesList, subcategory.getName())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + subcategory.getName());
        }
        else{
            createNewSubcategory(subcategory.getName(), id);
            return new SubcategoryDTO(subcategory);
        }
    }

}
