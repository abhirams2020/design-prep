import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

class CustomSchedulerService {

  private final PriorityQueue<Task> taskQueue;
  private final Lock lock = new ReentrantLock();
  private final Condition newTaskAdded = lock.newCondition();
  private final ExecutorService workerExecutor;

  public CustomSchedulerService(int workerThreadSize) {
    this.taskQueue = new PriorityQueue<>(Comparator.comparingLong(Task::getScheduledTime));
    workerExecutor = Executors.newFixedThreadPool(workerThreadSize);
  }

  public void start() {
    long timeToSleep = 0;
    while (true) {
      lock.lock();
      try {
        while (taskQueue.isEmpty()) {
          newTaskAdded.await();
        }
        while (!taskQueue.isEmpty()) {
          timeToSleep = taskQueue.peek().getScheduledTime() - System.currentTimeMillis();
          if (timeToSleep <= 0) {
            break;
          }
          newTaskAdded.await(timeToSleep, TimeUnit.MILLISECONDS);
        }
        Task task = taskQueue.poll();
        long newScheduledTime = 0;
        switch (task.getTaskType()) {
          case 1:
            //this type of task will be executed only once
            workerExecutor.submit(task.getRunnable());
            break;
          case 2:
            newScheduledTime =
                System.currentTimeMillis() + task.getUnit().toMillis(task.getPeriod());
            workerExecutor.submit(task.getRunnable());
            task.setScheduledTime(newScheduledTime);
            taskQueue.add(task);
            break;
          case 3:
            Future<?> future = workerExecutor.submit(task.getRunnable());
            future.get(); // will wait for the finish of this task
            newScheduledTime =
                System.currentTimeMillis() + task.getUnit().toMillis(task.getDelay());
            task.setScheduledTime(newScheduledTime);
            taskQueue.add(task);
            break;
        }
      } catch (Exception e) {
        System.out.println("some thing wrong in start");
        e.printStackTrace();
      } finally {
        lock.unlock();
      }
    }

  }

  /**
   * Creates and executes a one-shot action that becomes enabled after the given delay.
   */
  public void schedule(Runnable command, long delay, TimeUnit unit) {
    lock.lock();
    try {
      long scheduledTime = System.currentTimeMillis() + unit.toMillis(delay);
      Task task = new ScheduledTask(command, scheduledTime, 1, unit);
      taskQueue.add(task);
      newTaskAdded.signalAll();
    } catch (Exception e) {
      System.out.println("some thing wrong in scheduling task type 1");
    } finally {
      lock.unlock();
    }
  }

  /**
   * Creates and executes a periodic action that becomes enabled first after the given initial
   * delay, and subsequently with the given period; that is executions will commence after
   * initialDelay then initialDelay+period, then initialDelay + 2 * period, and so on.
   */
  public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
    lock.lock();
    try {
      long scheduledTime = System.currentTimeMillis() + unit.toMillis(initialDelay);
      Task task = new ScheduledFixedRateTask(command, scheduledTime, 2, period, unit);
      taskQueue.add(task);
      newTaskAdded.signalAll();
    } catch (Exception e) {
      System.out.println("some thing wrong in scheduling task type 2");
    } finally {
      lock.unlock();
    }
  }

  /*
   * Creates and executes a periodic action that becomes enabled first after the given initial delay, and
   * subsequently with the given delay between the termination of one execution and the commencement of the next.
   */
  public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay,
      TimeUnit unit) {
    lock.lock();
    try {
      long scheduledTime = System.currentTimeMillis() + unit.toMillis(initialDelay);
      Task task = new ScheduledFixedDelayTask(command, scheduledTime, 3, delay, unit);
      taskQueue.add(task);
      newTaskAdded.signalAll();
    } catch (Exception e) {
      System.out.println("some thing wrong in scheduling task type 3");
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

}

abstract class Task {
  private final Runnable runnable;
  private final int taskType;
  private final TimeUnit unit;
  private Long scheduledTime;

  public Task(Runnable runnable, Long scheduledTime, int taskType, TimeUnit unit) {
    this.runnable = runnable;
    this.scheduledTime = scheduledTime;
    this.taskType = taskType;
    this.unit = unit;
  }

  public Runnable getRunnable() {
    return runnable;
  }

  public int getTaskType() {
    return taskType;
  }

  public Long getPeriod() {
    return Long.MAX_VALUE;
  }

  public Long getDelay() {
    return Long.MAX_VALUE;
  }

  public TimeUnit getUnit() {
    return unit;
  }

  public Long getScheduledTime() {
    return scheduledTime;
  }

  public void setScheduledTime(Long scheduledTime) {
    this.scheduledTime = scheduledTime;
  }
}

class ScheduledTask extends Task{
  public ScheduledTask(Runnable runnable, Long scheduledTime, int taskType, TimeUnit unit) {
    super(runnable, scheduledTime, taskType, unit);
  }
}

class ScheduledFixedRateTask extends Task {
  private final Long period;

  public ScheduledFixedRateTask(Runnable runnable, Long scheduledTime, int taskType, Long period, TimeUnit unit) {
    super(runnable, scheduledTime, taskType, unit);
    this.period = period;
  }

  @Override
  public Long getPeriod() {
    return period;
  }
}

class ScheduledFixedDelayTask extends Task {
  private final Long delay;

  public ScheduledFixedDelayTask(Runnable runnable, Long scheduledTime, int taskType, Long delay, TimeUnit unit) {
    super(runnable, scheduledTime, taskType, unit);
    this.delay = delay;
  }

  @Override
  public Long getDelay() {
    return delay;
  }
}



public class Main {
  private static Runnable getRunnableTask(String s) {
    return () -> {
      System.out.println(s + " started at " + LocalTime.now());
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(s + " ended at " + LocalTime.now());
    };
  }

  public static void main(String args[]) {
      CustomSchedulerService schedulerService = new CustomSchedulerService(10);
      Runnable task1 = getRunnableTask("Task1");
      schedulerService.schedule(task1, 1, TimeUnit.SECONDS);
      Runnable task2 = getRunnableTask("Task2");
      schedulerService.scheduleAtFixedRate(task2, 1, 2, TimeUnit.SECONDS);
      Runnable task3 = getRunnableTask("Task3");
      schedulerService.scheduleWithFixedDelay(task3, 1, 2, TimeUnit.SECONDS);
      Runnable task4 = getRunnableTask("Task4");
      schedulerService.scheduleAtFixedRate(task4, 1, 2, TimeUnit.SECONDS);
      schedulerService.start();
  }
}