package com.homeBudget.rest.dto;

import com.homeBudget.domain.wallet.Wallet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@Data
@RequiredArgsConstructor
public class WalletDTO {
    private String name;
    private boolean savings;
    private String comment;
    private float financialGoal;
    private float balance;

    public WalletDTO(Wallet wallet){
        this.name=wallet.getName();
        this.comment=wallet.getComment();
        this.financialGoal=wallet.getFinancialGoal();
        this.balance=wallet.getBalance();
        this.savings=wallet.isSavings();
    }
}
