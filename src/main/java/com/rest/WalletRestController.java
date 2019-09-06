package com.rest;

import com.dao.UserDAO;
import com.dao.WalletDAO;
import com.entity.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wallets")
public class WalletRestController {
    private final UserDAO userDAO;
    private final Environment env;
    private final WalletDAO walletDAO;

    @PostMapping
    public void createNewWallet(@Valid @RequestBody Wallet wallet){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        walletDAO.saveWallet(wallet.getNameWallet(), wallet.getBalance(), email);
    }
}
