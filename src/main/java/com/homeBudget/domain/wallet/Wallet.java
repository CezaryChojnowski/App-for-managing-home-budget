package com.homeBudget.domain.wallet;


import com.homeBudget.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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
    private String comment;

    @Setter
    @Min(value = 0, message = "{wallet.balance.min}")
    private float balance;

    @Setter
    private float financialGoal;

    @Setter
    private boolean savings;

    @Setter
    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("wallet{" +
                "id=" + idWallet +
                ", name_wallet='" + nameWallet + '\'' +
                ", comment='" + comment + '\'' +
                ", balance=" + balance + '\'' +
                ", savings='" + savings + '\'' +
                '}');
        return result.toString();
    }

    public static final class Builder {
        private int idWallet;
        private String nameWallet;
        private String comment;
        private float balance;
        private float financialGoal;
        private boolean savings;
        private User user;

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
        public Builder financialGoal(float financialGoal) {
            this.financialGoal = financialGoal;
            return this;
        }
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }
        public Builder savings(boolean savings) {
            this.savings = savings;
            return this;
        }
        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Wallet build() {
            Wallet wallet = new Wallet();
            wallet.idWallet = this.idWallet;
            wallet.nameWallet = this.nameWallet;
            wallet.balance = this.balance;
            wallet.user = this.user;
            wallet.comment=this.comment;
            wallet.savings=this.savings;
            wallet.financialGoal=this.financialGoal;
            return wallet;
        }
    }
}
