package com.homeBudget.rest.dto;



import lombok.ToString;

import java.time.LocalDate;

@ToString
public class DailyExpensesDTO {
      public LocalDate date;
      public double amount;

      public DailyExpensesDTO(LocalDate date, double amount){
            this.date=date;
            this.amount=amount;
      }
}
