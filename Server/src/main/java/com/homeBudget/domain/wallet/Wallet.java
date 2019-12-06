package com.homeBudget.domain.wallet;


import com.homeBudget.domain.transaction.Transaction;
import com.homeBudget.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @NotEmpty(message = "{wallet.name_wallet.notEmpty}")
    private String name;

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
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "wallet")
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("wallet{" +
                "id=" + id +
                ", name_wallet='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", balance=" + balance + '\'' +
                ", savings='" + savings + '\'' +
                '}');
        return result.toString();
    }
}
