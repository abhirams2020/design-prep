package splitwiselld;

import splitwiselld.model.Group;
import splitwiselld.model.User;
import splitwiselld.model.expense.ExactExpense;
import splitwiselld.model.expense.Expense;
import splitwiselld.model.expense.PercentExpense;
import splitwiselld.service.SplitwiseService;
import java.util.*;

public class SplitwiseDemo {
  public static void main(String[] args) {
    SplitwiseService splitwiseService = SplitwiseService.getInstance();

    // Creating Users
    User alice = new User("Alice");
    User bob = new User("Bob");
    User charlie = new User("Charlie");

    splitwiseService.createAccount(alice);
    splitwiseService.createAccount(bob);
    splitwiseService.createAccount(charlie);

    // Creating Group
    Group tripGroup = new Group("Trip", Arrays.asList(alice, bob, charlie));
    splitwiseService.createGroup(tripGroup);

    // Adding Expense: Alice paid $120 for a shared meal
    Map<User, Double> splitMap1 = new HashMap<>();
    splitMap1.put(alice, 40.0);
    splitMap1.put(bob, 40.0);
    splitMap1.put(charlie, 40.0);
    Expense expense1 = new ExactExpense(alice, splitMap1, 120.0);

    splitwiseService.addUserExpense(alice, expense1);
//    splitwiseService.addGroupExpense(alice, tripGroup, expense1);

    // Adding Expense: Bob paid $60 for transport, split equally
    Map<User, Double> splitMap2 = new HashMap<>();
    splitMap2.put(alice, 20.0);
    splitMap2.put(bob, 20.0);
    splitMap2.put(charlie, 60.0);
    Expense expense2 = new PercentExpense(bob, splitMap2, 100.0);

    splitwiseService.addGroupExpense(bob, tripGroup, expense2);

    // Viewing Balances
    System.out.println(splitwiseService.viewUserBalance(alice));
    System.out.println(splitwiseService.viewUserBalance(bob));
    System.out.println(splitwiseService.viewUserBalance(charlie));
    System.out.println(splitwiseService.viewGroupBalance(tripGroup));

    // Settlement: Charlie pays Alice to settle dues
    splitwiseService.settleUserExpense(charlie, alice);

    // Viewing Updated Balances
    System.out.println("After charlie to alice Settlement:");
    System.out.println(splitwiseService.viewUserBalance(alice));
    System.out.println(splitwiseService.viewUserBalance(bob));
    System.out.println(splitwiseService.viewUserBalance(charlie));
    System.out.println(splitwiseService.viewGroupBalance(tripGroup));

    splitwiseService.settleGroupExpense(alice, tripGroup);

    System.out.println("After Group Settlement by Alice:");
    System.out.println(splitwiseService.viewUserBalance(alice));
    System.out.println(splitwiseService.viewUserBalance(bob));
    System.out.println(splitwiseService.viewUserBalance(charlie));
    System.out.println(splitwiseService.viewGroupBalance(tripGroup));

  }
}
