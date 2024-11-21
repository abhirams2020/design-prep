package AtmManagement;

import AtmManagement.enums.SelectionType;
import AtmManagement.model.user.NormalUser;
import AtmManagement.model.user.User;

public class AtmManagementDemo {

  public static void main(String[] args) {
    AtmService atmService = AtmService.instance;

    atmService.startMachine(); // move from idle state

    atmService.createAccount("abhiram", "account1", "password1");

    atmService.login("account1", "password1");

    atmService.selectOption(SelectionType.ENQUIRY);

    atmService.checkBalance();

    atmService.selectOption(SelectionType.DEPOSIT);

    atmService.depositMoney(1000);

    atmService.selectOption(SelectionType.ENQUIRY);

    atmService.checkBalance();

    atmService.selectOption(SelectionType.WITHDRAW);

    atmService.withdrawMoeny(500);

    atmService.selectOption(SelectionType.ENQUIRY);

    atmService.checkBalance();

    atmService.stopMachine();
  }
}
