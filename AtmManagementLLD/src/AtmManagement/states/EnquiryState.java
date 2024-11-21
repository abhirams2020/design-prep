package AtmManagement.states;

import AtmManagement.AtmService;
import AtmManagement.BalanceService;
import AtmManagement.model.user.User;

public class EnquiryState extends AtmState{
  BalanceService balanceService;

  public EnquiryState() {
    this.balanceService = BalanceService.instance;
  }

  @Override
  public void checkBalance(AtmService atmService, User user) {
    int balance = balanceService.checkBalance(user);
    System.out.println("user : " + user.getName() + " has balance : " + balance);
    atmService.setAtmState(new SelectionState());
  }
}
