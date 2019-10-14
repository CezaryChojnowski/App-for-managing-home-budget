package com.homeBudget.domain.Transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
