package com.homeBudget.domain.transaction;

import com.homeBudget.domain.event.Event;
import com.homeBudget.domain.person.Person;
import com.homeBudget.domain.subcategory.Subcategory;
import com.homeBudget.domain.wallet.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String comment;

    @Setter
    private float amount;

    @Setter
    @Column(name = "date_transaction", columnDefinition = "DATE")
    private LocalDate date;

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
        result.append("transaction{" +
                "idTransaction=" + id +
                ", comment='" + comment + '\'' +
                ", amount='" + amount + '\'' +
                ", dateTransaction='" + date + '\'' +
                '}');
        return result.toString();
    }

}
