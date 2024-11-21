package LoggingSystem;

import java.util.List;

public class Logger {
  LogLevel logLevel;

  List<Message> messageList;

  public void setLogLevel(LogLevel logLevel) {
    this.logLevel = logLevel;
  }

  public void addMessage(Message message) {
    messageList.add(message);
  }
}
