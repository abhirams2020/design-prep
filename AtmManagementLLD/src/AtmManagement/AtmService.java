package AtmManagement;

import AtmManagement.enums.SelectionType;
import AtmManagement.model.user.User;
import AtmManagement.states.AtmState;
import AtmManagement.states.IdleState;

public class AtmService {
  public AtmState atmState;
  public AuthService authService;
  public BalanceService balanceService;

  public User user;

  public static AtmService instance = new AtmService();

  private AtmService() {
    atmState = new IdleState();
    authService = AuthService.instance;
    balanceService = balanceService.instance;
    user = null;
  }

  public void startMachine() {
    atmState.startMachine(this);
  }

  public void createAccount(String name, String account, String password) {
    atmState.createAccount(this, name, account, password);
  }

  public void login(String account, String password) {
    atmState.login(this, account, password);
  }

  public void selectOption(SelectionType selectionType) {
    atmState.selectOption(this, selectionType);
  }

  public void checkBalance() {
    atmState.checkBalance(this, user);
  }

  public void depositMoney(int money) {
    atmState.depositMoney(this, user, money);
  }

  public void stopMachine() {
    atmState.stopMachine(this);
  }

  public void withdrawMoeny(int money) {
    atmState.withdrawMoney(this, user, money);
  }

  public void setAtmState(AtmState atmState) {
    this.atmState = atmState;
  }
}
