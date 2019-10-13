package com.homeBudget.domain.Transaction;

import com.homeBudget.domain.Event.Event;
import com.homeBudget.domain.Person.Person;
import com.homeBudget.domain.Subcategory.Subcategory;
import com.homeBudget.domain.Wallet.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTransaction;

    @Setter
    private String comment;

    @Setter
    private float amount;

    @Setter
    @Column(name = "date_transaction", columnDefinition = "DATE")
    private LocalDate dateTransaction;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_subcategory")
    @JsonIgnore
    private Subcategory subcategory;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_wallet")
    @JsonIgnore
    private Wallet wallet;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_person")
    @JsonIgnore
    private Person person;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_event")
    @JsonIgnore
    private Event event;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Transaction{" +
                "idTransaction=" + idTransaction +
                ", comment='" + comment + '\'' +
                ", amount='" + amount + '\'' +
                ", dateTransaction='" + dateTransaction + '\'' +
                '}');
        return result.toString();
    }
}
