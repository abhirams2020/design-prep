package wallet;

import java.math.BigDecimal;
import java.util.List;
import wallet.model.Account;
import wallet.model.Transaction;
import wallet.model.User;
import wallet.model.enums.CurrencyType;
import wallet.model.payment.CardPaymentMethod;
import wallet.model.payment.PaymentMethod;
import wallet.model.payment.UpiPaymentMethod;

public class WalletDemo {

  public static void main(String[] args) {
    WalletService walletService = WalletService.getInstance();

    Account account1 = new Account("123", "456");
    Account account2 = new Account("456", "123");

    walletService.createAccount("ab@gmail.com", "12345", account1);
    walletService.createAccount("cd@gmail.com", "12345", account2);

    User user1 = walletService.login("ab@gmail.com", "12345");
    User user2 = walletService.login("cd@gmail.com", "12345");

    walletService.depositMoney(user1, new BigDecimal("1000"));

    PaymentMethod cardPaymentMethod1 = new CardPaymentMethod("12345678", "123", "abhiram");
    PaymentMethod upiPaymentMethod1 = new UpiPaymentMethod("abhiram@icici", "abhiram");

    walletService.setPaymentMethod(user1, cardPaymentMethod1);

    walletService.makePayment(user1, user2, new BigDecimal("100.00"), CurrencyType.INR);

    walletService.setPaymentMethod(user1, upiPaymentMethod1);

    walletService.makePayment(user1, user2, new BigDecimal("150.00"), CurrencyType.USD);

    List<Transaction> transactionList = walletService.viewTransactions(user1);

    for (Transaction transaction : transactionList) {
      System.out.println(
          transaction.sender + " sent " + transaction.amount + " to " + transaction.receiver);
    }

    System.out.println("user " + user1.id + " has balance " + walletService.getBalance(user1));
    System.out.println("user " + user2.id + " has balance " + walletService.getBalance(user2));
  }
}
