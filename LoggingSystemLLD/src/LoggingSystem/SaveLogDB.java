package LoggingSystem;

import java.util.List;

public class SaveLogDB implements SaveLog{

  @Override
  public void save(List<Message> messageList) {
    System.out.println("message saved to db");
  }
}
