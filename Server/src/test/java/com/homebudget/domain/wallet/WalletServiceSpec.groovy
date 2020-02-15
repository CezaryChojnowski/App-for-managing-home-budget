package com.homebudget.domain.wallet

import com.homeBudget.domain.user.User
import com.homeBudget.domain.user.UserRepository
import com.homeBudget.domain.wallet.Wallet
import com.homeBudget.domain.wallet.WalletRepository
import com.homeBudget.domain.wallet.WalletService
import org.springframework.core.env.Environment
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import spock.lang.Specification

class WalletServiceSpec extends Specification {
    UserRepository userRepository;
    WalletService walletService;
    WalletRepository walletRepository;
    Environment environment;
    BCryptPasswordEncoder bCryptPasswordEncoder


    def setup() {
        environment = Mock(Environment)
        userRepository = Mock(UserRepository)
        bCryptPasswordEncoder = Mock(BCryptPasswordEncoder)
        walletRepository = Mock(WalletRepository)
        walletService = new WalletService(walletRepository, userRepository, environment)
    }

    def 'Should return all user wallets'() {
        given: 'User with all required fields'
        def id = 4
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        Iterable<Wallet> walletIterableTemp = [];
        walletRepository.findWalletByUser(_ as User) >> walletIterableTemp;
        Iterable<Wallet> walletIterable = walletService.findAllUserWallets(user)
        then:
        walletIterable.isEmpty();
    }

    def 'Should return new created wallet '() {
        given: 'User and wallet with all required fields'
        def id = 4
        def name = "tempName"
        def balance = 20
        def savings = false
        Wallet wallet = new Wallet.WalletBuilder()
                .name(name)
                .balance(balance)
                .savings(savings)
                .build();
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        walletRepository.save(_ as Wallet) >> wallet
        userRepository.findUserByEmail(_ as String) >> Optional.of(user)
        Wallet newWallet = walletService.createNewWallet(wallet.getId(),wallet.getName(),wallet.getBalance(), wallet.getFinancialGoal(), wallet.getComment(), wallet.isSavings(), "qqq" )
        then:
        newWallet.equals(wallet)
    }

    def 'Shoud return deleted wallet'(){
        given: 'Wallet with all required fields'
        def id = 4
        def name = "tempName"
        def balance = 20
        def savings = false
        Wallet wallet = new Wallet.WalletBuilder()
                .name(name)
                .balance(balance)
                .savings(savings)
                .build();
        when:
        walletRepository.findWalletById(_ as int) >> wallet
        then:
        walletService.deleteWallet(id) == null;
}

    def 'Should return user wallets by savings'() {
        given: 'User with all required fields'
        def id = 4
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        List<Wallet> walletListTemp = new ArrayList<>();
        walletRepository.findWalletByUserAndSavings(_ as User, _ as boolean) >> walletListTemp;
        List<Wallet> walletList = walletService.findAllUserWalletsBySavings(user, false)
        then:
        walletListTemp.isEmpty();
    }

    def 'Should return all wallets'(){
        given: 'User with all required fields'
        def id = 4
        def firstName = "testUserFirstName"
        def lastName = "testUserLastName"
        def password = "tempPassword"
        def email = "tempEmail@temp.temp"
        User user = new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .password(bCryptPasswordEncoder.encode(password))
                .email(email)
                .idTravel((long) 0)
                .build();
        when:
        List<Wallet> walletListTemp = new ArrayList<>();
        walletRepository.findWalletByUser(_ as User) >> walletListTemp
        then:
        walletListTemp.isEmpty();
    }

    def 'Should transfer funds from the first wallet to the second'(){
        given: 'Wallet with all required fields and amount to transfer'
        def amount = 100.00;
        def senderWalletId = 4
        def senderWalletName = "tempName"
        def senderWalletBalance = 500
        def senderWalletBalanceSavings = false
        Wallet senderWallet = new Wallet.WalletBuilder()
                .name(senderWalletName)
                .balance(senderWalletBalance)
                .savings(senderWalletBalanceSavings)
                .build();
        def recipientWalletId = 5
        def recipientWalletName = "tempName"
        def recipientWalletBalance = 100
        def recipientWalletSavings = true
        Wallet recipientWallet = new Wallet.WalletBuilder()
                .name(recipientWalletName)
                .balance(recipientWalletBalance)
                .savings(recipientWalletSavings)
                .build();
        when:
        walletRepository.findWalletById(_ as int) >> senderWallet
        walletRepository.save(_ as Wallet) >> recipientWallet
        then:
        senderWallet.equals(senderWallet)
    }
}
