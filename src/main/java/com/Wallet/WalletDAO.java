package com.Wallet;

import com.User.User;
import com.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletDAO {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public List<Wallet> findWalletsByUser(User user){
        return walletRepository.findWalletsByUser(user);
    }

    public Wallet createNewWallet(String nameWallet, float balance, String emailUser){
        Wallet wallet = new Wallet.Builder()
                .nameWallet(nameWallet)
                .balance(balance)
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
}
