package com.homeBudget.domain.Transaction;

import com.homeBudget.domain.Subcategory.Subcategory;
import com.homeBudget.domain.Subcategory.SubcategoryDAO;
import com.homeBudget.domain.Subcategory.SubcategoryRepository;
import com.homeBudget.domain.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionDAO {
    public final SubcategoryRepository subcategoryRepository;
    public final TransactionRepository transactionRepository;
    public final SubcategoryDAO subcategoryDAO;

    public List<Transaction> findAllTransactionByUser(User user){
        List<Subcategory> subcategories = subcategoryDAO.getAllSubcategoryByUser(user);
        List<Transaction> transactions = new ArrayList<>();
        for (Subcategory s: subcategories){
            transactions.addAll(transactionRepository.findAllBySubcategory(s));
        }
        return transactions;

    }
}
