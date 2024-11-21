package taskmanagement.model.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import taskmanagement.service.GenereateRandomIdService;
import taskmanagement.enums.TaskPriority;
import taskmanagement.enums.TaskStatus;
import taskmanagement.model.task.NormalTask;
import taskmanagement.model.task.Task;

public class NormalUser implements User{
  public String id, name, email, password;

  List<Task> ongoingTaskList;
  List<Task> completedTaskList;

  public NormalUser(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.id = GenereateRandomIdService.getRandomId();
    ongoingTaskList = new ArrayList<>();
    completedTaskList = new ArrayList<>();
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public boolean createTask(String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority) {
    Task task = new NormalTask(title, description, dueDate, taskPriority);
    ongoingTaskList.add(task);
    return true;
  }

  @Override
  public boolean updateTask(Task task, String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority, TaskStatus taskStatus) {
    if(!checkTaskExist(task)) {
      return false;
    }
    task.update(title, description, dueDate, taskPriority, taskStatus);
    if(taskStatus == TaskStatus.COMPLETED) {
      ongoingTaskList.remove(task);
      completedTaskList.add(task);
    }
    return true;
  }

  @Override
  public boolean deleteTask(Task task) {
    if(checkTaskExist(task) == false) {
      return false;
    }
    ongoingTaskList.remove(task);
    return false;
  }

  @Override
  public List<Task> getOngoingTasks() {
    return ongoingTaskList;
  }

  @Override
  public List<Task> getCompletedTasks() {
    return completedTaskList;
  }

  @Override
  public boolean canAssignTask() {
    return true;
  }

  @Override
  public boolean assignTask(User user, Task task) {
    if(canAssignTask()==false) return false;
    user.addTask(task);
    return true;
  }

  @Override
  public void addTask(Task task) {
    if(checkTaskExist(task)){
      return;
    }
    ongoingTaskList.add(task);
  }

  private boolean checkTaskExist(Task task) {
    if(ongoingTaskList.contains(task)) {
      return true;
    }
    return false;
  }
}
