package wallet.model.payment;

import java.math.BigDecimal;
import wallet.model.Account;

public interface PaymentMethod {

  boolean makePayment(Account sender, Account receiver, BigDecimal amt);
}
