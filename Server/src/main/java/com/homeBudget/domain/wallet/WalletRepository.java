package com.homeBudget.domain.wallet;

import com.homeBudget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findWalletByUser(User user);

    Wallet findWalletById(int id);

    List<Wallet> findWalletByUserAndSavings(User user, boolean savings);

    Wallet findWalletByUserAndId(User user, int id);

    Wallet findWalletByName(String name);
}
