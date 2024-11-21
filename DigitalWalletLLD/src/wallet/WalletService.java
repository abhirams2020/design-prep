package wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import wallet.model.Account;
import wallet.model.Transaction;
import wallet.model.User;
import wallet.model.enums.CurrencyType;
import wallet.model.payment.PaymentMethod;

public class WalletService {

  public static WalletService instance;
  private final CopyOnWriteArrayList<User> userList;
  private final ConcurrentHashMap<User, List<Transaction>> transactionMap;
  private final ConcurrentHashMap<User, PaymentMethod> paymentMap;

  public WalletService() {
    userList = new CopyOnWriteArrayList<>();
    transactionMap = new ConcurrentHashMap<>();
    paymentMap = new ConcurrentHashMap<>();
  }

  public static WalletService getInstance() {
    if (instance == null) {
      synchronized (WalletService.class) {
        if (instance == null) {
          instance = new WalletService();
        }
      }
    }
    return instance;
  }

  public synchronized void createAccount(String email, String password, Account account) {
    if (checkAccountExist(email)) {
      return;
    }
    User user = new User(generateId(), email, password, account);
    userList.add(user);
  }

  public synchronized User login(String email, String password) {
    for (User user : userList) {
      if (user.emailId.equals(email) && user.password.equals(password)) {
        return user;
      }
    }
    return null;
  }

  public void depositMoney(User user, BigDecimal amt) {
    user.account.depositMoney(amt);
  }

  public boolean withdrawMoney(User user, BigDecimal amt) {
    return user.account.withdrawMoney(amt);
  }

  public void setPaymentMethod(User user, PaymentMethod paymentMethod) {
    paymentMap.put(user, paymentMethod);
  }

  public void makePayment(User sender, User receiver, BigDecimal amt, CurrencyType currencyType) {
    PaymentMethod paymentMethod = paymentMap.get(sender);

    // convert all transaction to usd
    BigDecimal newAmt = convertCurrency(amt, currencyType, CurrencyType.USD);

    paymentMethod.makePayment(sender.account, receiver.account, newAmt);

    System.out.println(
        "payment of " + newAmt.toString() + " USD made from " + sender.id + " to " + receiver.id);

    Transaction transaction = new Transaction(generateId(), sender, receiver,
        LocalDateTime.now().toString(), newAmt);

    List<Transaction> transactionList1 = transactionMap.getOrDefault(sender, new ArrayList<>());
    List<Transaction> transactionList2 = transactionMap.getOrDefault(receiver, new ArrayList<>());
    transactionList1.add(transaction);
    transactionList2.add(transaction);
    transactionMap.put(sender, transactionList1);
    transactionMap.put(receiver, transactionList2);
  }

  public List<Transaction> viewTransactions(User user) {
    return transactionMap.getOrDefault(user, new ArrayList<>());
  }

  public BigDecimal getBalance(User user) {
    return user.account.getBalance();
  }

  private synchronized boolean checkAccountExist(String email) {
    for (User user : userList) {
      if (user.emailId.equals(email)) {
        return true;
      }
    }
    return false;
  }

  // convert val from type1 to type2
  public BigDecimal convertCurrency(BigDecimal val, CurrencyType type1, CurrencyType type2) {
    if (type1.equals(type2)) {
      return val;
    }
    BigDecimal typeVal1 = BigDecimal.valueOf(type1.getValue());
    BigDecimal typeVal2 = BigDecimal.valueOf(type2.getValue());
    BigDecimal ratio = typeVal1.divide(typeVal2);
    return val.multiply(ratio);
  }

  private synchronized String generateId() {
    return UUID.randomUUID().toString();
  }
}
