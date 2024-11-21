package LoggingSystem;

import java.util.List;

public class SaveLogFile implements SaveLog{

  @Override
  public void save(List<Message> messageList) {
    System.out.println("messages saved to file");
  }
}
