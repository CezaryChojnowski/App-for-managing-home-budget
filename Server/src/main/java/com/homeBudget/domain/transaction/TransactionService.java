package com.homeBudget.domain.transaction;

import com.homeBudget.domain.event.EventRepository;
import com.homeBudget.domain.person.PersonRepository;
import com.homeBudget.domain.subcategory.SubcategoryService;
import com.homeBudget.domain.subcategory.SubcategoryRepository;
import com.homeBudget.domain.user.UserRepository;
import com.homeBudget.domain.wallet.Wallet;
import com.homeBudget.domain.wallet.WalletRepository;
import com.homeBudget.rest.dto.DailyExpensesDTO;
import com.homeBudget.rest.dto.StatsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
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

    public Transaction createNewTransaction(float amount, String comment, LocalDate dateTransaction, int id_subcategory, int id_wallet, Long id_event, Long id_person, boolean expenditure){
        Transaction transaction = new Transaction.TransactionBuilder()
                .amount(amount)
                .comment(comment)
                .date(dateTransaction)
                .expenditure(expenditure)
                .subcategory(subcategoryRepository.findSubcategoryById(id_subcategory))
                .wallet(walletRepository.findWalletById(id_wallet))
                .event(eventRepository.findEventById(id_event))
                .person(personRepository.findPersonById(id_person))
                .build();
        Wallet wallet = walletRepository.findWalletById(id_wallet);
        if(expenditure==true){
            wallet.setBalance(wallet.getBalance()-amount);
        }
        else{
            wallet.setBalance(wallet.getBalance()+amount);
        }
        walletRepository.save(wallet);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findTransactionByEvent(Long eventID){
        return transactionRepository.findTransactionsByEvent(eventRepository.findEventById(eventID));
    }

    public void deleteTransactions(int idTransactions){
        transactionRepository.deleteById(idTransactions);
    }

    public List<Transaction> getAllTransactionsByUserEmail(String email){
        return transactionRepository.findTransactionsByUserEmail(email);
    }

    public StatsDTO getStatsToDashboard(Principal principal){
        String email = principal.getName();
        StatsDTO stats = new StatsDTO();
        LocalDate localDate = LocalDate.now();
        LocalDate dateStartingCurrentMonthOfTheCurrentYear = LocalDate.parse(localDate.getYear() + "-" + localDate.getMonth().getValue() + "-" + "01");
        LocalDate dateEndingCurrentMonthOfTheCurrentYear = LocalDate.parse(localDate.getYear() + "-" + localDate.getMonth().getValue() + "-" + localDate.getMonth().maxLength());
        stats.setBalanceOfAllAccounts(walletRepository.calculateSumOfAccountBalances(email,false));
        stats.setNumberOfTransactionsInTheCurrentMonth(transactionRepository.qwerty(email,dateStartingCurrentMonthOfTheCurrentYear,dateEndingCurrentMonthOfTheCurrentYear));
        stats.setAverageDailyExpensesInCurrentMonth((transactionRepository.calculateTheSumOfExpensesForCurrentMonth(email, dateStartingCurrentMonthOfTheCurrentYear, dateEndingCurrentMonthOfTheCurrentYear, true))/localDate.getDayOfMonth());
        stats.setSumOfExpensesForTheCurrentMonth(transactionRepository.calculateTheSumOfExpensesForCurrentMonth(email, dateStartingCurrentMonthOfTheCurrentYear, dateEndingCurrentMonthOfTheCurrentYear, true));
        stats.setSumOfInComeForTheCurrentMonth(transactionRepository.calculateTheSumOfInComeForCurrentMonth(email,dateStartingCurrentMonthOfTheCurrentYear,dateEndingCurrentMonthOfTheCurrentYear,false));
        return stats;
    }

    public List<DailyExpensesDTO> getDailyExpenses(Principal principal){
        String email = principal.getName();
        StatsDTO stats = new StatsDTO();
        LocalDate localDate = LocalDate.now();
        LocalDate dateStartingCurrentMonthOfTheCurrentYear = LocalDate.parse(localDate.getYear() + "-" + localDate.getMonth().getValue() + "-" + "01");
        LocalDate dateEndingCurrentMonthOfTheCurrentYear = LocalDate.parse(localDate.getYear() + "-" + localDate.getMonth().getValue() + "-" + localDate.getMonth().maxLength());
        return transactionRepository.sumAndGroupDailyExpenses(email,dateStartingCurrentMonthOfTheCurrentYear,dateEndingCurrentMonthOfTheCurrentYear);
    }

}
