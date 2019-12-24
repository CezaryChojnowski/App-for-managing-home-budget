package com.homeBudget.domain.wallet;

import com.homeBudget.configuration.error.RecordExistsException;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import com.homeBudget.rest.dto.WalletDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final Environment env;

    public Iterable<Wallet> findAllUserWallets(User user){
        return walletRepository.findWalletByUser(user);
    }

    public List<Wallet> findAllUserWalletsBySavings(User user, boolean savings){
        return walletRepository.findWalletByUserAndSavings(user, savings);
    }

    public Wallet createNewWallet(int id, String name, float balance, float financialGoal, String comment, boolean savings, String emailUser){
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

    public void deleteWallet(int idWallet){
        walletRepository.deleteById(idWallet);
    }

    public Wallet findWalletByUserAndIdWallet(User user, int idWallet){
        return walletRepository.findWalletByUserAndId(user, idWallet);
    }

    public Wallet editWallet(int id, String name, float balance, String comment, float financialGoal, List<Wallet> walletsList){
        Wallet wallet = findWalletByIdWallet(id);
        walletsList.remove(wallet);
        if(checkIfUserHasWalletWithTheGivenName(walletsList, name)){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + name);
        }
            wallet.setName(name);
            wallet.setBalance(balance);
            wallet.setComment(comment);
            wallet.setFinancialGoal(financialGoal);
            return walletRepository.save(wallet);
    }

    public WalletDTO addWallet(List<Wallet> walletsList, Wallet wallet, String email){
        if(checkIfUserHasWalletWithTheGivenName(walletsList, wallet.getName())){
            throw new RecordExistsException(env.getProperty("recordExists") + " " + wallet.getName());
        }
        else{
            WalletDTO walletDTO = new WalletDTO(wallet);
            createNewWallet(wallet.getId() ,wallet.getName(), wallet.getBalance(), wallet.getFinancialGoal(), wallet.getComment(), wallet.isSavings(), email);
            return walletDTO;
        }
    }

    public List<Wallet> findAllWallets(User user){
        return walletRepository.findWalletByUser(user);
    }

    public void TransferFunds(int senderWallet, int recipientWallet, float amount){
        Wallet sender = walletRepository.findWalletById(senderWallet);
        Wallet recipient = walletRepository.findWalletById(recipientWallet);
        sender.setBalance(sender.getBalance()-amount);
        recipient.setBalance(recipient.getBalance()+amount);
        walletRepository.save(sender);
        walletRepository.save(recipient);
    }

    public int calculateSumOfAccountBalances(String email, boolean savings){
        return walletRepository.calculateSumOfAccountBalances(email, savings);
    }
}
