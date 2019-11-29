package com.homeBudget.domain.subcategory;

import com.homeBudget.domain.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homeBudget.domain.transaction.Transaction;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subcategory")
@Builder
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_category")
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "subcategory")
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("subcategory{" +
                "idSubcategory=" + id +
                ", nameSubcategory='" + name + '\'' +
                '}');
        return result.toString();
    }

}
