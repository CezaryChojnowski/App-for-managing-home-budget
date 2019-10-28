package com.homeBudget.rest.controller;

import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserService;
import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.domain.wallet.Wallet;
import com.homeBudget.domain.wallet.WalletService;
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

    private final WalletService walletService;
    private final UserService userService;
    
    @PostMapping
    public ResponseEntity addWallet(@Valid @RequestBody Wallet wallet){
        User user = userService.getUserByAuthentication();
        List<Wallet> walletsList = walletService.findAllUserWallets(user);
        return ResponseEntity.ok(walletService.addWallet(walletsList, wallet, user.getEmail()));
    }

    @GetMapping
    public ResponseEntity getAllUserWalletsBySavings(@RequestParam(value = "savings") boolean savings){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(walletService.findAllUserWalletsBySavings(user, savings));
    }

    @PatchMapping(value = "/{idWallet}")
    public ResponseEntity changeBalance(@PathVariable("idWallet") Integer idWallet,
                                        @RequestParam("balance") float balance){
        User user = userService.getUserByAuthentication();
        return ResponseEntity.ok(walletService.updateBalance(user, idWallet, balance));
    }
}
