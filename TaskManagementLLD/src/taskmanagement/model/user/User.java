package taskmanagement.model.user;

import java.time.LocalDateTime;
import java.util.List;
import taskmanagement.enums.TaskPriority;
import taskmanagement.enums.TaskStatus;
import taskmanagement.model.reminder.Reminder;
import taskmanagement.model.task.Task;

public interface User {
  public String getEmail();
  public String getPassword();
  public boolean createTask(String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority);
  public boolean updateTask(Task task, String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority, TaskStatus taskStatus);
  public boolean deleteTask(Task task);
  public List<Task> getOngoingTasks();
  public List<Task> getCompletedTasks();
  public boolean canAssignTask();
  public boolean assignTask(User user, Task task);
  public void addTask(Task task);
}
