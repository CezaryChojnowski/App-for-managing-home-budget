package com.homeBudget.rest.dto;

import com.homeBudget.domain.category.Category;
import lombok.*;


@Value
@Data
public class CategoryDTO {
    private String name;
    private boolean type;


    public CategoryDTO(Category category){
        this.name=category.getName();
        this.type=category.isCredits();
    }
}
