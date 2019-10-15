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
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        result.append("transaction{" +
                "idTransaction=" + idTransaction +
                ", comment='" + comment + '\'' +
                ", amount='" + amount + '\'' +
                ", dateTransaction='" + dateTransaction + '\'' +
                '}');
        return result.toString();
    }

    public static final class Builder {
        private int idSubcategory;
        private String comment;
        private float amount;
        private LocalDate dateTransaction;
        private Subcategory subcategory;
        private Wallet wallet;
        private Event event;
        private Person person;

        public Builder idSubcategory(int idSubcategory) {
            this.idSubcategory = idSubcategory;
            return this;
        }
        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }
        public Builder amount(float amout) {
            this.amount = amout;
            return this;
        }
        public Builder dateTransaction(LocalDate dateTransaction) {
            this.dateTransaction=dateTransaction;
            return this;
        }
        public Builder subcategory(Subcategory subcategory) {
            this.subcategory = subcategory;
            return this;
        }
        public Builder event(Event event) {
            this.event = event;
            return this;
        }
        public Builder person(Person person) {
            this.person = person;
            return this;
        }
        public Builder wallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }


        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.idTransaction=this.idSubcategory;
            transaction.amount=this.amount;
            transaction.comment=this.comment;
            transaction.dateTransaction=this.dateTransaction;
            transaction.subcategory=this.subcategory;
            transaction.wallet=this.wallet;
            transaction.event=this.event;
            transaction.person=this.person;
            return transaction;
        }
    }

}
