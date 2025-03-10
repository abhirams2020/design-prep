package model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class User {
  String id;
  String name;
  String licenseId;

  public User(String name, String licenseId) {
    this.name = name;
    this.licenseId = licenseId;
    this.id = UUID.randomUUID().toString();
  }
}
