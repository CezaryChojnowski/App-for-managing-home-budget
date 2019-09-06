package com.entity;


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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_wallet")
    private int idWallet;

    @NotEmpty(message = "{wallet.name_wallet.notEmpty}")
    @Column(name = "name_wallet")
    private String nameWallet;

    @Min(value = 0, message = "{wallet.balance.min}")
    @Column(name = "balance")
    private float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Wallet{" +
                "id=" + idWallet +
                ", name_wallet='" + nameWallet + '\'' +
                ", balance=" + balance +
                '}');
        return result.toString();
    }

    public static final class Builder {
        private int idWallet;
        private String nameWallet;
        private float balance;
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

        public Builder user(User user) {
            this.user = user;
            return this;
        }

            public Wallet build() {
                Wallet goal = new Wallet();
                goal.idWallet = this.idWallet;
                goal.nameWallet = this.nameWallet;
                goal.balance = this.balance;
                goal.user = this.user;
                return goal;
            }
        }
    }
