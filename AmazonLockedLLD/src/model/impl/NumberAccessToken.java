package model.impl;

import enums.TokenType;
import java.time.Duration;
import java.time.LocalDateTime;
import model.AccessToken;
import model.Locker;

public class NumberAccessToken implements AccessToken {
  Locker locker;
  LocalDateTime createdAt;
  LocalDateTime expiredAt;
  Duration expiry;
  String code;
  TokenType type;

  public NumberAccessToken(Locker locker) {
    this.locker = locker;
    this.expiry = Duration.ofDays(7);
    this.createdAt = LocalDateTime.now();
    this.expiredAt = createdAt.plus(expiry);
    this.type = TokenType.NUMBER;
  }

  @Override
  public boolean isExpired() {
    return LocalDateTime.now().isAfter(expiredAt);
  }

  @Override
  public Locker getLocker() {
    return locker;
  }

  @Override
  public TokenType getType() {
    return type;
  }

  @Override
  public String getCode() {
    return code;
  }
}
