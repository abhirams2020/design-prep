package model.impl;

import enums.Size;
import java.util.List;

public class LargeLocker extends AbstractLocker{

  public LargeLocker() {
    super(Size.LARGE, List.of(Size.SMALL, Size.MEDIUM, Size.LARGE));
  }
}
