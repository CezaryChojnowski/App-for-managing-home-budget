package com.Wallet;

import com.User.User;
import com.User.UserDAO;
import com.error.RecordExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class WalletRestController {

    private final WalletDAO walletDAO;
    private final UserDAO userDAO;
    private final Environment env;


    @PostMapping
    public ResponseEntity createNewWallet(@Valid @RequestBody Wallet wallet){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        List<Wallet> walletsList = walletDAO.findAllUserWallets(user);
        if(walletDAO.checkIfUserHasWalletWithTheGivenName(walletsList, wallet.getNameWallet())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + wallet.getNameWallet());
        }
        else{
            return ResponseEntity.ok(walletDAO.createNewWallet(wallet.getNameWallet(), wallet.getBalance(), wallet.getFinancialGoal(), wallet.getComment(), wallet.isSavings(), email));
        }
    }

    @GetMapping
    public ResponseEntity getAllUserWalletsBySavings(@RequestParam(value = "savings") boolean savings){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        return ResponseEntity.ok(walletDAO.findAllUserWalletsBySavings(user, savings));
    }

    @PatchMapping(value = "/{idWallet}")
    public ResponseEntity changeBalance(@PathVariable("idWallet") Integer idWallet,
                                        @RequestParam("balance") float balance){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findUserByEmail(email);
        return ResponseEntity.ok(walletDAO.updateBalance(user, idWallet, balance));
    }
}
