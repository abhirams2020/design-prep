package model.impl;

import enums.Size;
import java.util.List;

public class MediumLocker extends AbstractLocker{
  public MediumLocker() {
    super(Size.MEDIUM, List.of(Size.MEDIUM, Size.SMALL));
  }
}
