package model.impl;

import enums.Size;
import java.util.List;

public class SmallLocker extends AbstractLocker{
  public SmallLocker() {
    super(Size.SMALL, List.of(Size.SMALL));
  }
}
