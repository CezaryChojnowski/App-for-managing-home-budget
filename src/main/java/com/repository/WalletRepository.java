package com.repository;

import com.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    @Query(value = "SELECT w FROM Wallet w, User u WHERE w.user=u and u.id = ?1")
    List<Wallet> findUserWallets(Integer id);

    @Query(value = "SELECT w FROM Wallet w, User u WHERE w.user=u and u.id = ?1 and w.id=?2")
    Wallet findUsersWalletByID(Integer idUser, Integer idWallet);
}
