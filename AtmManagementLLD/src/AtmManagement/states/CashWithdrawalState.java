package AtmManagement.states;

import AtmManagement.AtmService;
import AtmManagement.BalanceService;
import AtmManagement.model.user.User;

public class CashWithdrawalState extends AtmState{

  BalanceService balanceService;

  public CashWithdrawalState() {
    this.balanceService = BalanceService.instance;
  }

  @Override
  public void withdrawMoney(AtmService atmService, User user, int money) {
    balanceService.withdrawMoney(user, money);
    System.out.println(money + " has been withdrawn by user : " + user.getName());
    atmService.setAtmState(new SelectionState());
  }
}
