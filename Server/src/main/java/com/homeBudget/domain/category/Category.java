package com.homeBudget.domain.category;

import com.homeBudget.domain.subcategory.Subcategory;
import com.homeBudget.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String name;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
    private List<Subcategory> subcategories = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("category{" +
                "idCategory=" + id +
                ", nameCategory='" + name + '\'' +
                '}');
        return result.toString();
    }
}
