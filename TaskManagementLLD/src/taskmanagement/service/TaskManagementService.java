package taskmanagement.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import taskmanagement.enums.TaskPriority;
import taskmanagement.enums.TaskStatus;
import taskmanagement.model.reminder.CloseToCompleteReminder;
import taskmanagement.model.reminder.Reminder;
import taskmanagement.model.task.Task;
import taskmanagement.model.user.NormalUser;
import taskmanagement.model.user.User;

public class TaskManagementService {
  List<User> userList;

  private static TaskManagementService instance;

  private TaskManagementService() {
    userList = new ArrayList<>();
  }

  public static TaskManagementService getInstance() {
    if(instance==null) {
      synchronized (TaskManagementService.class) {
        if(instance==null) {
          instance = new TaskManagementService();
        }
      }
    }
    return instance;
  }

  public boolean registerUser(String name, String email, String password) {
    User user = checkUserExist(email);
    if(user != null) return false;
    userList.add(new NormalUser(name, email, password));
    return true;
  }

  public User loginUser(String email, String password) {
    User user = checkUserExist(email);
    if(user!=null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
      return user;
    }
    return null;
  }

  private User checkUserExist(String userId) {
    for(User user:userList) {
      if(user.getEmail() == userId) {
        return user;
      }
    }
    return null;
  }

  public boolean createTask(User user, String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority) {
    return user.createTask(title, description, dueDate, taskPriority);
  }

  public boolean updateTask(User user, Task task, String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority, TaskStatus taskStatus) {
    if(taskStatus == TaskStatus.CLOSE_TO_FINISH) {
      Reminder reminder = new CloseToCompleteReminder(user,"reminder to finish task", task);
      System.out.println(reminder.print());
    }
    return user.updateTask(task, title, description, dueDate, taskPriority, taskStatus);
  }

  public boolean deleteTask(User user, Task task) {
    return user.deleteTask(task);
  }

  public List<Task> getOngoingTask(User user) {
    return user.getOngoingTasks();
  }

  public void showPendingTask(User user) {
    List<Task> taskList = user.getOngoingTasks();
    System.out.println("Showing pending tasks of " + user.getEmail() + " : ");
    for(Task task:taskList) {
      System.out.println(task.getTitle());
    }
  }

  public void showHistory(User user) {
    List<Task> taskList = user.getCompletedTasks();
    System.out.println("Showing completed tasks : ");
    for(Task task:taskList) {
      System.out.println(task.getTitle());
    }
  }

  public boolean assignTask(User sender, User receiver, Task task) {
    return sender.assignTask(receiver, task);
  }
}
