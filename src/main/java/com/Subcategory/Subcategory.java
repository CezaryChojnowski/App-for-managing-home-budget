package com.Subcategory;

import com.Category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subcategory")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSubcategory;

    @Setter
    private String nameSubcategory;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_category")
    @JsonIgnore
    private Category category;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Subcategory{" +
                "idSubcategory=" + idSubcategory +
                ", nameSubcategory='" + nameSubcategory + '\'' +
                '}');
        return result.toString();
    }
}
