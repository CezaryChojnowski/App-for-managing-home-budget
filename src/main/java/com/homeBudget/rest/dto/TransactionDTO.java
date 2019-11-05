package com.homeBudget.rest.dto;

import com.homeBudget.domain.transaction.Transaction;
import lombok.*;

import java.time.LocalDate;


@Value
@Data
public class TransactionDTO {
    private String comment;
    private float amount;
    private LocalDate date;

    public TransactionDTO(Transaction transaction){
        this.comment=transaction.getComment();
        this.amount=transaction.getAmount();
        this.date=transaction.getDate();
    }
}
