package com.rest;

import com.dao.WalletDAO;
import com.entity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/myWallets")
    public List<Wallet> getMyWallets(@RequestParam Integer userID){
        return walletDAO.findUserWallets(userID);
    }
}
