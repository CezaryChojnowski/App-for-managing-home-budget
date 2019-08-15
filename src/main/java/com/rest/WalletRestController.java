package com.rest;

import com.dao.UserDAO;
import com.dao.WalletDAO;
import com.entity.User;
import com.entity.Wallet;
import com.error.Exception;
import com.error.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
//read about REST naming conventions - it is always should be like /collection/idOfSingleObject...
@RequestMapping("/wallets")
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class WalletRestController {

    private final WalletDAO walletDAO;
    private final UserDAO userDAO;
    private final Environment env;


    @GetMapping
    public List<Wallet> getAllWallets(){
        return walletDAO.findAllWallet();
    }

    @GetMapping
    public List<Wallet> getWalletByUserId(@RequestParam Integer userID){
        return walletDAO.findUserWallets(userID);
    }

    //create WalletForm
    //consider retrieving logged user id from sesion or from request security token to not have to got userId in http request
    @PostMapping
    public ResponseEntity createNewWallet(@RequestParam Integer userID,
                                  @Valid @RequestBody Wallet walletForm){
        //it would be better to move that logic to some factory
        User user = userDAO.findUserByID(userID);
        if(walletDAO.checkIfUserHasWalletWithTheGivenName(user.getWallets(), walletForm.getName_wallet())){
            throw new Exception(env.getProperty("recordExist") + " " + walletForm.getName_wallet());
        }
        else{
            return ResponseEntity.ok(walletDAO.createNewWallet(walletForm, userID));
        }
    }

    @DeleteMapping("/{walletId}")
    public void removeWallet(@PathVariable("walletId") Integer walletId){
        //walletId should be enough to remove wallet
        //you should not thrown an exception when there is no resource you want to delete
        //read about what means that method is idempotent - when you sent the same delete request it should give you the same result
            walletDAO.removeWallet(walletId);
    }

    //Use PATCH method to edit resource,
    //PUT is for replacing whole resource - has to be idempotent
    @PatchMapping("/{userId}:{walletId}")
    public Wallet editWallet(@PathVariable("userId") Integer userID, @PathVariable("walletId") Integer walletId,
                           @Valid @RequestBody Wallet wallet){
        User user = userDAO.findUserByID(userID);
        Wallet tempWallet = walletDAO.findWalletByID(walletId);
        if(user == null|| tempWallet == null){
            throw new RecordNotFoundException(env.getProperty("notFoundRecord"));
        }
        else{
            if(walletDAO.checkIfUserHasWalletWithTheGivenName(user.getWallets(), wallet.getName_wallet())){
                throw new Exception(env.getProperty("recordExist") + " " + wallet.getName_wallet());
            }
            else{
                return walletDAO.updateWallet(userID, walletId, wallet);
            }
        }
    }
}
