package taskmanagement.model.task;

import java.time.LocalDateTime;
import taskmanagement.service.GenereateRandomIdService;
import taskmanagement.enums.TaskPriority;
import taskmanagement.enums.TaskStatus;

public class NormalTask implements Task{
  public String id;
  public String title, description;
  public LocalDateTime dueDate;
  public TaskPriority taskPriority;
  public TaskStatus taskStatus;

  public NormalTask(String title, String description, LocalDateTime dueDate, TaskPriority taskPriority) {
    this.id = GenereateRandomIdService.getRandomId();
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.taskPriority = taskPriority;
    this.taskStatus = TaskStatus.NOT_STARTED;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public LocalDateTime getDueDate() {
    return dueDate;
  }

  @Override
  public void update(String title, String description, LocalDateTime dueDate,
      TaskPriority taskPriority, TaskStatus taskStatus) {
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.taskPriority = taskPriority;
    this.taskStatus = taskStatus;
  }
}
