package AtmManagement.states;

import AtmManagement.AtmService;

public class IdleState extends AtmState{

  @Override
  public void startMachine(AtmService atmService) {
    System.out.println("atm started");
    atmService.setAtmState(new AuthState());
  }
}
