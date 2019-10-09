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
        result.append("Subcategory{" +
                "idSubcategory=" + idSubcategory +
                ", nameSubcategory='" + nameSubcategory + '\'' +
                '}');
        return result.toString();
    }

    public static final class Builder {
        private int idSubcategory;
        private String nameSubcategory;
        private Category category;

        public Builder idSubcategory(int idSubcategory) {
            this.idSubcategory = idSubcategory;
            return this;
        }

        public Builder nameSubcategory(String nameSubcategory) {
            this.nameSubcategory = nameSubcategory;
            return this;
        }
        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Subcategory build() {
            Subcategory Subcategory = new Subcategory();
            Subcategory.idSubcategory = this.idSubcategory;
            Subcategory.nameSubcategory = this.nameSubcategory;
            Subcategory.category=this.category;
            return Subcategory;
        }
    }
}
