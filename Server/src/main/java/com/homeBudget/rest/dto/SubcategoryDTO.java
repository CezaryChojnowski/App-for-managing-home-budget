package com.homeBudget.rest.dto;

import com.homeBudget.domain.subcategory.Subcategory;
import lombok.*;


@Value
@Data
public class SubcategoryDTO {
    private String name;

    public SubcategoryDTO(Subcategory subcategory){
        this.name=subcategory.getName();
    }
}
