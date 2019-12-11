package com.homeBudget.domain.subcategory;

import com.homeBudget.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Integer> {
    List<Subcategory> findAllByCategory(Category category);
    Subcategory findSubcategoryById(int idSubcategory);
    @Query(value = "SELECT s FROM User u, Category c, Subcategory s WHERE s.category=c AND c.user=u AND u.email = ?1")
    List<Subcategory> findSubcategoriesByUserEmail(String email);
}
