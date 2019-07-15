package com.rest;

import com.dao.WalletDAO;
import com.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletRestController {

    private WalletDAO walletDAO;

    @Autowired
    public WalletRestController(WalletDAO theWalletDAO){
        walletDAO = theWalletDAO;
    }

    @GetMapping("/getAllWallets")
    public List<Wallet> getAllWallets(){
        return walletDAO.findAllWallet();
    }
}
