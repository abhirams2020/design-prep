package AtmManagement.states;

import AtmManagement.AtmService;
import AtmManagement.AuthService;
import AtmManagement.model.user.User;

public class AuthState extends AtmState{

  AuthService authService;

  public AuthState() {
    authService = AuthService.instance;
  }

  @Override
  public void createAccount(AtmService atmService, String name, String account, String password) {
    authService.createAccount(name, account, password);
  }

  @Override
  public void login(AtmService atmService, String account, String password) {
    User user = authService.login(account, password);
    atmService.user = user;

    if(user != null) {
      System.out.println("user logged in");
      atmService.setAtmState(new SelectionState());
    }
    System.out.println("invalid credentials");
  }
}
