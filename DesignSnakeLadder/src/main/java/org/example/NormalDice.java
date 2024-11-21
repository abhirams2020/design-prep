package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class NormalDice implements Dice{
  int side;

  public NormalDice(int side) {
    this.side = side;
  }
  public int rollDice() {
    return ThreadLocalRandom.current().nextInt(1, side+1);
  }
}
