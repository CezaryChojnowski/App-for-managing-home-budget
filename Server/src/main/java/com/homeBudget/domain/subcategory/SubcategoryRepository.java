package com.homeBudget.domain.subcategory;

import com.homeBudget.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    List<Subcategory> findAllByCategory(Category category);
    Subcategory findSubcategoryById(int idSubcategory);
}
