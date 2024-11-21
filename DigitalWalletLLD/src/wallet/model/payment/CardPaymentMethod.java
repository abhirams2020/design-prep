package wallet.model.payment;

import java.math.BigDecimal;
import wallet.model.Account;

public class CardPaymentMethod implements PaymentMethod {

  private final String cardNumber;
  private final String cvv;
  private final String name;

  public CardPaymentMethod(String cardNumber, String cvv, String name) {
    this.cardNumber = cardNumber;
    this.cvv = cvv;
    this.name = name;
  }

  @Override
  public boolean makePayment(Account sender, Account receiver, BigDecimal amt) {
    return sender.withdrawMoney(amt) && receiver.depositMoney(amt);
  }
}
