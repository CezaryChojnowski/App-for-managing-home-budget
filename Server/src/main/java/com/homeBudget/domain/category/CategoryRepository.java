package com.homeBudget.domain.category;

import com.homeBudget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoriesByUser(User user);
    Category findCategoryById(int id);
}
