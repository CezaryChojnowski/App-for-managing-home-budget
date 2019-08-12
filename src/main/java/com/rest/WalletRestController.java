package com.rest;

import com.dao.UserDAO;
import com.dao.WalletDAO;
import com.entity.User;
import com.entity.Wallet;
import com.error.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletRestController {

    private WalletDAO walletDAO;

    private UserDAO userDAO;

    @Autowired
    public WalletRestController(WalletDAO theWalletDAO, UserDAO theUserDAO){
        walletDAO = theWalletDAO;
        userDAO = theUserDAO;
    }

    @GetMapping("/getAllWallets")
    public List<Wallet> getAllWallets(){
        return walletDAO.findAllWallet();
    }

    @GetMapping("/myWallets")
    public List<Wallet> getMyWallets(@RequestParam Integer userID){
        return walletDAO.findUserWallets(userID);
    }

    @PostMapping("/createNewWallet")
    public ResponseEntity createNewWallet(@RequestParam Integer userID,
                                  @Valid @RequestBody Wallet wallet){
        User user = userDAO.findUserByID(userID);
        if(walletDAO.checkIfUserHasWalletWithTheGivenName(user.getWallets(), wallet.getName_wallet())){
            throw new Exception("User has a wallet with this name " + wallet.getName_wallet());
        }
        else{
            return ResponseEntity.ok(walletDAO.createNewWallet(wallet, userID));
        }
    }


}
