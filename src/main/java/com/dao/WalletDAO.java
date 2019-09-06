package com.dao;

import com.entity.Wallet;
import com.repository.AbstractDao;
import com.repository.WalletRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class WalletDAO extends AbstractDao implements WalletRepository {

    private final UserDAO userDAO;

    @Override
    public void saveWallet(String nameWallet, float balance, String emailUser) {
        Wallet wallet = new Wallet.Builder()
                .nameWallet(nameWallet)
                .balance(balance)
                .user(userDAO.findUserByEmail(emailUser).get())
                .build();
        save(wallet);
        commit();
        getSession().close();
    }
}
