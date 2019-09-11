package com.Wallet;


import com.Category.Category;
import com.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idWallet;

    @Setter
    @NotEmpty(message = "{wallet.name_wallet.notEmpty}")
    private String nameWallet;

    @Setter
    @Min(value = 0, message = "{wallet.balance.min}")
    private float balance;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wallet")
    private List<Category> categories;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Wallet{" +
                "id=" + idWallet +
                ", name_wallet='" + nameWallet + '\'' +
                ", balance=" + balance + '\'' +
                ", categories="+ categories +
                '}');
        return result.toString();
    }

    public static final class Builder {
        private int idWallet;
        private String nameWallet;
        private float balance;
        private User user;
        private List<Category> categories;

        public Builder idWallet(int idWallet) {
            this.idWallet = idWallet;
            return this;
        }

        public Builder nameWallet(String nameWallet) {
            this.nameWallet = nameWallet;
            return this;
        }

        public Builder balance(float balance) {
            this.balance = balance;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder categories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public Wallet build() {
            Wallet goal = new Wallet();
            goal.idWallet = this.idWallet;
            goal.nameWallet = this.nameWallet;
            goal.balance = this.balance;
            goal.user = this.user;
            goal.categories = this.categories;
            return goal;
        }
    }
}
