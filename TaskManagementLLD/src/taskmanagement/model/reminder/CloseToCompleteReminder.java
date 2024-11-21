package taskmanagement.model.reminder;

import taskmanagement.model.user.User;
import taskmanagement.service.GenereateRandomIdService;
import taskmanagement.model.task.Task;

public class CloseToCompleteReminder implements Reminder{
  public User user;
  public String id, description;
  public Task task;

  public CloseToCompleteReminder(User user, String description, Task task) {
    this.user = user;
    this.id = GenereateRandomIdService.getRandomId();
    this.description = description;
    this.task = task;
  }

  @Override
  public String print() {
    return new String("REMINDER ! " + user.getEmail() + " is so close, reminder to complete task " + task.getTitle() + " by " + task.getDueDate().toString());
  }
}
