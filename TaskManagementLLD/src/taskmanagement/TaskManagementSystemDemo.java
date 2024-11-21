package taskmanagement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import taskmanagement.enums.TaskPriority;
import taskmanagement.enums.TaskStatus;
import taskmanagement.model.task.NormalTask;
import taskmanagement.model.task.Task;
import taskmanagement.model.user.User;
import taskmanagement.service.TaskManagementService;

public class TaskManagementSystemDemo {

  public static void main(String[] args) {
    System.out.println("hello world");

    TaskManagementService taskManager = TaskManagementService.getInstance();

    taskManager.registerUser("Abhiram", "ab@gmail.com", "1234");
    taskManager.registerUser("Rocky", "rk@gmail.com", "5432");

    User abhiram = taskManager.loginUser("ab@gmail.com", "1234");
    User rocky = taskManager.loginUser("rk@gmail.com", "5432");

    LocalDateTime dueDate = LocalDateTime.of(2024, 12, 12, 0,0);
    taskManager.createTask(abhiram, "task1", "task num 1", dueDate, TaskPriority.LOW);
    taskManager.createTask(abhiram, "task2", "task num 2", dueDate, TaskPriority.MEDIUM);
    taskManager.createTask(abhiram, "task3", "task num 3", dueDate, TaskPriority.HIGH);

    taskManager.createTask(rocky, "task1", "task num 1", dueDate, TaskPriority.LOW);
    taskManager.createTask(rocky, "task2", "task num 2", dueDate, TaskPriority.MEDIUM);
    taskManager.createTask(rocky, "task3", "task num 3", dueDate, TaskPriority.HIGH);

    taskManager.assignTask(rocky, abhiram, new NormalTask("task4", "task num 4", LocalDateTime.now(), TaskPriority.HIGH));

    taskManager.showPendingTask(abhiram);
    taskManager.showPendingTask(rocky);

    List<Task> abhiramTasks = taskManager.getOngoingTask(abhiram);

    Task randomTask = abhiramTasks.get(ThreadLocalRandom.current().nextInt(0, abhiramTasks.size()));

    taskManager.updateTask(abhiram, randomTask, "title5", "task num 5", dueDate, TaskPriority.LOW,
        TaskStatus.CLOSE_TO_FINISH);

    taskManager.updateTask(abhiram, randomTask, "title5", "task num 5", dueDate, TaskPriority.LOW,
        TaskStatus.COMPLETED);

    taskManager.showHistory(abhiram);
  }
}
