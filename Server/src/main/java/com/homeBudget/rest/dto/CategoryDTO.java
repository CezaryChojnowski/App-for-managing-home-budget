package com.homeBudget.rest.dto;

import com.homeBudget.domain.category.Category;
import lombok.*;


@Value
@Data
public class CategoryDTO {
    private String name;


    public CategoryDTO(Category category){
        this.name=category.getName();
    }
}
