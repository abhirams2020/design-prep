package model;

import enums.TokenType;

public interface AccessToken {
  boolean isExpired();
  Locker getLocker();
  TokenType getType();
  String getCode();
}
