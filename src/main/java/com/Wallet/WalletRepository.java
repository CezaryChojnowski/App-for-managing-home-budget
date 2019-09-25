package com.Wallet;

import com.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findWalletByUser(User user);

    Wallet findWalletByIdWallet(int idWallet);

    List<Wallet> findWalletByUserAndSavings(User user, boolean savings);
}
