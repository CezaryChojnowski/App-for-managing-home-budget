package com.Category;

import com.Wallet.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Getter
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
    @JoinColumn(name="id_wallet")
    @JsonIgnore
    private Wallet wallet;

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
}
