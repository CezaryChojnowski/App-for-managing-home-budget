package com.dao;

import com.entity.User;
import com.entity.Wallet;
import com.repository.AbstractDao;
import com.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Transactional
public class UserDAO extends AbstractDao implements UserRepository{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(String firstName, String lastName, String password, String email) {
        try{
            User user = new User.Builder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .wallets(new ArrayList<Wallet>())
                .build();
        persist(user);
        commit();
    }catch (Exception sqlException){
            rollBack();
        }finally {
            getSession().close();
        }
    }

    @Override
    public void deleteUser(String email) {

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Query query = getSession().createQuery("from User where email = :email");
        query.setParameter("email", email);
        List<User> resultList = query.list();
        return resultList.stream().findFirst();
    }

    public List<User> findAllUsers(){
        Query query = getSession().createQuery("from User");
        List<User> resultList = query.list();
        return resultList;
    }

    public boolean checkIfUserWithGivenEmailExists(String email){
        return findUserByEmail(email).isPresent();
    }


    public void updateUser(User employee) {

    }

}
