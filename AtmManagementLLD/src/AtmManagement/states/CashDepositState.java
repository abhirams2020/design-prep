package AtmManagement.states;

import AtmManagement.AtmService;
import AtmManagement.BalanceService;
import AtmManagement.model.user.User;

public class CashDepositState extends AtmState{

  BalanceService balanceService;

  public CashDepositState() {
    this.balanceService = BalanceService.instance;
  }

  public void depositMoney(AtmService atmService, User user, int money) {
    balanceService.depositMoney(user, money);
    System.out.println(money + " has been deposited to user : " + user.getName());
    atmService.setAtmState(new SelectionState());
  }
}
