package com.homeBudget.rest.dto;



import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class DailyExpensesDTO {
      public LocalDate date;
      public double amount;

      public DailyExpensesDTO(LocalDate date, double amount){
            this.date=date;
            this.amount=amount;
      }
}
