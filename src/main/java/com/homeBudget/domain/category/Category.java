package com.homeBudget.domain.category;

import com.homeBudget.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    private boolean credits;

    @Setter
    @ManyToOne(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("category{" +
                "idCategory=" + id +
                ", nameCategory='" + name + '\'' +
                ", typeCategory=" + credits +
                '}');
        return result.toString();
    }
}
