package taskmanagement.service;

import java.util.UUID;

public class GenereateRandomIdService {
  public static String getRandomId() {
    return UUID.randomUUID().toString();
  }
}
