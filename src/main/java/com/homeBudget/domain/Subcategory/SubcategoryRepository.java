package com.homeBudget.domain.Subcategory;

import com.homeBudget.domain.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    List<Subcategory> findAllByCategory(Category category);
}
