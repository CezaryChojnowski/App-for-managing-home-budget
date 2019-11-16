package com.homeBudget.domain.wallet;

import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import com.homeBudget.rest.dto.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final Environment env;

    public List<Wallet> findAllUserWallets(User user){
        return walletRepository.findWalletByUser(user);
    }

    public List<Wallet> findAllUserWalletsBySavings(User user, boolean savings){
        return walletRepository.findWalletByUserAndSavings(user, savings);
    }

    public Wallet createNewWallet(String name, float balance, float financialGoal, String comment, boolean savings, String emailUser){
        Wallet wallet = new Wallet.WalletBuilder()
                .name(name)
                .balance(balance)
                .financialGoal(financialGoal)
                .comment(comment)
                .savings(savings)
                .user(userRepository.findUserByEmail(emailUser).get())
                .build();
        return walletRepository.save(wallet);
    }
    public boolean checkIfUserHasWalletWithTheGivenName(List<Wallet> userWallets, String newWalletName){
        return userWallets.stream().anyMatch(o -> o.getName().equals(newWalletName));
    }

    public Wallet findWalletByIdWallet(int id){
        return walletRepository.findWalletById(id);
    }

    public Wallet findWalletByUserAndIdWallet(User user, int idWallet){
        return walletRepository.findWalletByUserAndId(user, idWallet);
    }

    public Wallet updateBalance(User user, int id, float balance){
        Wallet wallet = findWalletByUserAndIdWallet(user, id);
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }

    public WalletDTO addWallet(List<Wallet> walletsList, Wallet wallet, String email){
        if(checkIfUserHasWalletWithTheGivenName(walletsList, wallet.getName())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + wallet.getName());
        }
        else{
            WalletDTO walletDTO = new WalletDTO(wallet);
            createNewWallet(wallet.getName(), wallet.getBalance(), wallet.getFinancialGoal(), wallet.getComment(), wallet.isSavings(), email);
            return walletDTO;
        }
    }
}
