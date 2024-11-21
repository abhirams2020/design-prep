package AtmManagement.states;

import AtmManagement.AtmService;
import AtmManagement.enums.SelectionType;

public class SelectionState extends AtmState{

  @Override
  public void selectOption(AtmService atmService, SelectionType selectionType) {
    System.out.println("selection option : " + selectionType.name());
    if(selectionType==SelectionType.ENQUIRY) {
      atmService.setAtmState(new EnquiryState());
    } else if (selectionType==SelectionType.DEPOSIT) {
      atmService.setAtmState(new CashDepositState());
    } else {
      atmService.setAtmState(new CashWithdrawalState());
    }
  }
}
