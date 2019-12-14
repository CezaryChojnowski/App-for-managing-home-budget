package com.homeBudget.rest.controller;

import com.homeBudget.domain.transaction.Transaction;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.domain.wallet.Wallet;
import com.homeBudget.domain.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
@CrossOrigin
public class WalletRestController {

    private final WalletService walletService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity addWallet(@Valid @RequestBody Wallet wallet, Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        Iterable<Wallet> walletsList = walletService.findAllUserWallets(user);
        return ResponseEntity.ok(walletService.addWallet((List<Wallet>) walletsList, wallet, user.getEmail()));
    }

    @GetMapping("/simpleWallets")
    public List<Wallet> getAllSimpleWallets(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
       return walletService.findAllUserWalletsBySavings(user, false);
    }

    @GetMapping
    public ResponseEntity getAllUserWalletsBySavings(@RequestParam(value = "savings") boolean savings){
        User user = userService.getUserByAuthentication();
        return ResponseEntity.ok(walletService.findAllUserWalletsBySavings(user, savings));
    }

    @PatchMapping(value = "/{idWallet}")
    public ResponseEntity editWallet(@PathVariable("idWallet") int idWallet,
                                        @Valid @RequestBody Wallet wallet,
                                        Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        List<Wallet> walletsList = (List<Wallet>) walletService.findAllUserWallets(user);
        return ResponseEntity.ok(walletService.editWallet(wallet.getId(), wallet.getName(), wallet.getBalance(), wallet.getComment(), wallet.getFinancialGoal(), walletsList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWalletById(@PathVariable int id, Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        Wallet wallet = walletService.findWalletByUserAndIdWallet(user, id);
        return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Wallet> getAllWallets(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        return walletService.findAllWallets(user);
    }

    @RequestMapping(value = "/{idWallet}", method = RequestMethod.DELETE)
    public void deleteWallet(@PathVariable int idWallet, Principal principal){
        walletService.deleteWallet(idWallet);
    }

    @PatchMapping
    public void TransferFunds(@RequestParam("IDsenderWallet") int IDsenderWallet,
                                     @RequestParam("IDrecipientWallet") int IDrecipientWallet,
                                        @RequestParam("amount") float amount,
                                     Principal principal){
        walletService.TransferFunds(IDsenderWallet, IDrecipientWallet, amount);
    }
}
