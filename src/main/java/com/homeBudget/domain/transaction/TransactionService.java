package com.homeBudget.domain.transaction;

import com.homeBudget.domain.event.EventRepository;
import com.homeBudget.domain.person.PersonRepository;
import com.homeBudget.domain.subcategory.Subcategory;
import com.homeBudget.domain.subcategory.SubcategoryService;
import com.homeBudget.domain.subcategory.SubcategoryRepository;
import com.homeBudget.domain.user.User;
import com.homeBudget.domain.user.UserRepository;
import com.homeBudget.domain.wallet.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {
    public final SubcategoryRepository subcategoryRepository;
    public final TransactionRepository transactionRepository;
    public final WalletRepository walletRepository;
    public final EventRepository eventRepository;
    public final PersonRepository personRepository;
    public final SubcategoryService subcategoryService;
    public final UserRepository userRepository;

    public List<Transaction> findAllTransactionByUser(User user){
        List<Subcategory> subcategories = subcategoryService.getAllSubcategoryByUser(user);
        List<Transaction> transactions = new ArrayList<>();
        for (Subcategory s: subcategories){
            transactions.addAll(transactionRepository.findAllBySubcategory(s));
        }
        return transactions;
    }

    public Transaction createNewTransaction(float amount, String comment, LocalDate dateTransaction, int id_subcategory, int id_wallet, int id_event, Long id_person){
        Transaction transaction = new Transaction.TransactionBuilder()
                .amount(amount)
                .comment(comment)
                .dateTransaction(dateTransaction)
                .subcategory(subcategoryRepository.findSubcategoryByIdSubcategory(id_subcategory))
                .wallet(walletRepository.findWalletByIdWallet(id_wallet))
                .event(eventRepository.findEventByIdEvent(id_event))
                .person(personRepository.findPersonById(id_person))
                .build();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAllTransactionsByDate(LocalDate startDate, LocalDate finishDate, User user){
        List<Transaction> transactions = findAllTransactionByUser(user);
        List<Transaction> transactionsByDate = transactionRepository.findTransactionsByDateTransactionBetween(startDate, finishDate);
        transactions.retainAll(transactionsByDate);
        return transactions;
    }

    public boolean checkIfDateIsNull(LocalDate startDate, LocalDate finishDate){
        return (startDate == null && finishDate == null);
    }

}
