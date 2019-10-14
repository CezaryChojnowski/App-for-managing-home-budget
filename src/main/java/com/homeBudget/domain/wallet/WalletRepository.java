package com.homeBudget.domain.wallet;

import com.homeBudget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findWalletByUser(User user);

    Wallet findWalletByIdWallet(int idWallet);

    List<Wallet> findWalletByUserAndSavings(User user, boolean savings);

    Wallet findWalletByUserAndIdWallet(User user, int idWallet);
}
