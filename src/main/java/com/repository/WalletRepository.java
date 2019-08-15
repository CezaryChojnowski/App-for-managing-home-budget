package com.repository;

import com.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    List<Wallet> findUserWallets(Integer id);

    Wallet findUsersWalletByID(Integer idUser, Integer idWallet);

    Wallet findWalletByID(Integer idWallet);

    void deleteById(Integer walletId);
}
