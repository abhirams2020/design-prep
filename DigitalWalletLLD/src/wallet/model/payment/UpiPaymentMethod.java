package wallet.model.payment;

import java.math.BigDecimal;
import wallet.model.Account;

public class UpiPaymentMethod implements PaymentMethod {

  private final String upiId;
  private final String name;

  public UpiPaymentMethod(String upiId, String name) {
    this.upiId = upiId;
    this.name = name;
  }

  @Override
  public boolean makePayment(Account sender, Account receiver, BigDecimal amt) {
    System.out.println(
        amt.toString() + " payment made using upi id : " + upiId + " to account num : "
            + receiver.getAccountId());
    return sender.withdrawMoney(amt) && receiver.depositMoney(amt);
  }
}
