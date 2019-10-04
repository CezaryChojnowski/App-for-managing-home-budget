package com.Category;

import com.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idCategory;

    @Setter
    private String nameCategory;

    @Setter
    private boolean typeCategory;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Category{" +
                "idCategory=" + idCategory +
                ", nameCategory='" + nameCategory + '\'' +
                ", typeCategory=" + typeCategory +
                '}');
        return result.toString();
    }

    public static final class Builder {
        private int idCategory;
        private String nameCategory;
        private boolean typeCategory;
        private User user;

        public Builder idCategory(int idCategory) {
            this.idCategory = idCategory;
            return this;
        }

        public Builder nameCategory(String nameCategory) {
            this.nameCategory = nameCategory;
            return this;
        }
        public Builder typeCategory(boolean typeCategory) {
            this.typeCategory = typeCategory;
            return this;
        }
        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Category build() {
            Category category = new Category();
            category.idCategory = this.idCategory;
            category.nameCategory = this.nameCategory;
            category.typeCategory = this.typeCategory;
            category.user=this.user;
            return category;
        }
    }
}
