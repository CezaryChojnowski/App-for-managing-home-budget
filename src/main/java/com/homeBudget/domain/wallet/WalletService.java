package com.homeBudget.domain.wallet;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public List<Wallet> findAllUserWallets(User user){
        return walletRepository.findWalletByUser(user);
    }

    public List<Wallet> findAllUserWalletsBySavings(User user, boolean savings){
        return walletRepository.findWalletByUserAndSavings(user, savings);
    }

    public Wallet createNewWallet(String nameWallet, float balance, float financialGoal, String comment, boolean savings, String emailUser){
        Wallet wallet = new Wallet.Builder()
                .nameWallet(nameWallet)
                .balance(balance)
                .financialGoal(financialGoal)
                .comment(comment)
                .savings(savings)
                .user(userRepository.findUserByEmail(emailUser).get())
                .build();
        return walletRepository.save(wallet);
    }
    public boolean checkIfUserHasWalletWithTheGivenName(List<Wallet> userWallets, String newWalletName){
        return userWallets.stream().anyMatch(o -> o.getNameWallet().equals(newWalletName));
    }

    public Wallet findWalletByIdWallet(int idWallet){
        return walletRepository.findWalletByIdWallet(idWallet);
    }

    public Wallet findWalletByUserAndIdWallet(User user, int idWallet){
        return walletRepository.findWalletByUserAndIdWallet(user, idWallet);
    }

    public Wallet updateBalance(User user, int idWallet, float balance){
        Wallet wallet = findWalletByUserAndIdWallet(user, idWallet);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }
}
