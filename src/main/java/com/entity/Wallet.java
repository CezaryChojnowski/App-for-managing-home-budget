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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "{wallet.name_wallet.notEmpty}")
    @Column(name="name_wallet")
    //rename name_wallet -> nameWallet, do it in all cases
    //after that you can remove (name="name_wallet") because by default if variable name will be nameWallet it will be mapped on name_wallet in db
    private String name_wallet;

    @Min(value = 0, message = "{wallet.balance.min}")
    @Column(name="balance")
    private float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usufructuary")
    @JsonIgnore
    private User user;

    @Override
    public String toString() {
        //Use string builder
        return "Wallet{" +
                "id=" + id +
                ", name_wallet='" + name_wallet + '\'' +
                ", balance=" + balance +
                '}';
    }
}
