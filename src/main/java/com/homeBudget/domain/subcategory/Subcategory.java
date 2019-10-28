package com.homeBudget.domain.subcategory;

import com.homeBudget.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subcategory")
@Builder
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSubcategory;

    @Setter
    private String nameSubcategory;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_category")
    @JsonIgnore
    private Category category;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("subcategory{" +
                "idSubcategory=" + idSubcategory +
                ", nameSubcategory='" + nameSubcategory + '\'' +
                '}');
        return result.toString();
    }

}
