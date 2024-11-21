package AtmManagement.states;

import AtmManagement.AtmService;
import AtmManagement.enums.SelectionType;
import AtmManagement.model.user.User;

public abstract class AtmState {
  public AtmService atmService;

  public void startMachine(AtmService atmService) {
    atmService.setAtmState(new AuthState());
  }

  public void createAccount(AtmService atmService, String name, String account, String password) {
    System.out.println("invalid action");
  }

  public void login(AtmService atmService, String account, String password) {
    System.out.println("invalid action");
  }

  public void logout(AtmService atmService) {
    atmService.user = null;
    atmService.setAtmState(new AuthState());
  }

  public void selectOption(AtmService atmService, SelectionType selectionType) {
    System.out.println("invalid action");
  }

  public void checkBalance(AtmService atmService, User user) {
    System.out.println("invalid action");
  }

  public void depositMoney(AtmService atmService, User user, int money) {
    System.out.println("invalid action");
  }

  public void withdrawMoney(AtmService atmService, User user, int money) {
    System.out.println("invalid action");
  }

  public void stopMachine(AtmService atmService) {
    atmService.setAtmState(new IdleState());
  }
}
