package com.homeBudget.rest.controller;

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
public class WalletRestController {

    private final WalletService walletService;
    private final UserService userService;

    @CrossOrigin("http://localhost:3000")
    @PostMapping
    public ResponseEntity addWallet(@Valid @RequestBody Wallet wallet, Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        Iterable<Wallet> walletsList = walletService.findAllUserWallets(user);
        return ResponseEntity.ok(walletService.addWallet((List<Wallet>) walletsList, wallet, user.getEmail()));
    }

    @GetMapping
    public ResponseEntity getAllUserWalletsBySavings(@RequestParam(value = "savings") boolean savings){
        User user = userService.getUserByAuthentication();
        return ResponseEntity.ok(walletService.findAllUserWalletsBySavings(user, savings));
    }

    @PatchMapping(value = "/{idWallet}")
    public ResponseEntity changeBalance(@PathVariable("idWallet") Integer idWallet,
                                        @RequestParam("balance") float balance,
                                        Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        return ResponseEntity.ok(walletService.updateBalance(user, idWallet, balance));
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> getWalletById(@PathVariable int id, Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        Wallet wallet = walletService.findWalletByUserAndIdWallet(user, id);
        return new ResponseEntity<Wallet>(wallet, HttpStatus.OK);
    }

    @GetMapping("/all")
    @CrossOrigin("http://localhost:3000")
    public List<Wallet> getAllWallets(Principal principal){
        User user = userService.findUserByEmail(principal.getName());
        return walletService.findAllWallets(user);
    }

    @RequestMapping(value = "/{idWallet}", method = RequestMethod.DELETE)
    @CrossOrigin("http://localhost:3000")
    public void deleteWallet(@PathVariable int idWallet, Principal principal){
        walletService.deleteWallet(idWallet);
    }
}
