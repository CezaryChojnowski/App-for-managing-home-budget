package com.dao;

import com.entity.Wallet;
import com.repository.UserRepository;
import com.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletDAO {

    private WalletRepository walletRepository;

    private UserRepository userRepository;

    @Autowired
    public WalletDAO(WalletRepository theWalletRepository, UserRepository theUserRepository){
        walletRepository = theWalletRepository;
        userRepository = theUserRepository;
    }

    public List<Wallet> findAllWallet(){
        return walletRepository.findAll();
    }

    public List<Wallet> findUserWallets(Integer id){
        return walletRepository.findUserWallets(id);
    }

    public Wallet createNewWallet(Wallet wallet, Integer userID){
        wallet.setName_wallet(wallet.getName_wallet());
        wallet.setBalance(wallet.getBalance());
        wallet.setUser(userRepository.findUserByID(userID));
        return walletRepository.save(wallet);
    }

    public boolean checkIfUserHasWalletWithTheGivenName(List<Wallet> useraWallets, Wallet wallet){
        return useraWallets.contains(wallet);
    }

}
