package com.homeBudget.domain.wallet;

import com.homeBudget.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findWalletByUser(User user);

    Wallet findWalletById(int id);

    List<Wallet> findWalletByUserAndSavings(User user, boolean savings);

    Wallet findWalletByUserAndId(User user, int id);

    Wallet findWalletByName(String name);

    @Query(value = "SELECT sum(w.balance) FROM User u, Wallet w WHERE w.user=u AND u.email = ?1 AND w.savings = ?2")
    int calculateSumOfAccountBalances(String email, boolean savings);
}
