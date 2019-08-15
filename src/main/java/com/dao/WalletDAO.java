package com.dao;

import com.entity.Wallet;
import com.repository.UserRepository;
import com.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletDAO {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;


    //rethink if all this public method really should be public
    public List<Wallet> findAllWallet(){
        return walletRepository.findAll();
    }

    public List<Wallet> findUserWallets(Integer id){
        return walletRepository.findUserWallets(id);
    }

    public Wallet findWalletByID(Integer walletID){
        return walletRepository.findWalletByID(walletID);
    }

    //create wallet should not have wallet as an argument
    public Wallet createNewWallet(Wallet wallet, Integer userID){
        wallet.setName_wallet(wallet.getName_wallet());
        wallet.setBalance(wallet.getBalance());
        wallet.setUser(userRepository.findUserById(userID));
        return walletRepository.save(wallet);
    }

    public boolean checkIfUserHasWalletWithTheGivenName(List<Wallet> userWallets, String newWalletName){
        return userWallets.stream().anyMatch(o -> o.getName_wallet().equals(newWalletName));
    }

    public void removeWallet(Integer walletId){
        walletRepository.deleteById(walletId);
    }

    public Wallet updateWallet(Integer userID, Integer walletID, Wallet editWallet){
        Wallet wallet = walletRepository.findUsersWalletByID(userID, walletID);
        wallet.setBalance(editWallet.getBalance());
        wallet.setName_wallet(editWallet.getName_wallet());
        return walletRepository.save(wallet);
    }
}
