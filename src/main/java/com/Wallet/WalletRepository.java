package com.Wallet;

import com.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findWalletsByUser(User user);
}
