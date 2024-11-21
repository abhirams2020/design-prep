package taskmanagement.model.task;

import java.time.LocalDateTime;
import taskmanagement.enums.TaskPriority;
import taskmanagement.enums.TaskStatus;

public interface Task {
  public String getTitle();
  public LocalDateTime getDueDate();

  public void update(String title, String description, LocalDateTime dueDate, TaskPriority taskPriority, TaskStatus taskStatus);
}
