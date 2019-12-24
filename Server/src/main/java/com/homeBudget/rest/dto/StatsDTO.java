package com.homeBudget.rest.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StatsDTO {
    int numberOfTransactionsInTheCurrentMonth;
    float averageDailyExpensesInCurrentMonth;
    float balanceOfAllAccounts;
    float sumOfExpensesForTheCurrentMonth;
    float sumOfInComeForTheCurrentMonth;
}
