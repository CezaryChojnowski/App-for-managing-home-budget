package com.dao;

import com.entity.Wallet;
import com.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletDAO {

    private WalletRepository walletRepository;

    public WalletDAO(WalletRepository theWalletRepository){
        walletRepository = theWalletRepository;
    }

    public List<Wallet> findAllWallet(){
        return walletRepository.findAll();
    }
}
