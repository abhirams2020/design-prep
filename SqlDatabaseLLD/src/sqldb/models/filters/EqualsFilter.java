package sqldb.models.filters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import sqldb.models.Row;

public class EqualsFilter implements Filter{
  String columnName;
  Object value;

  public EqualsFilter(String columnName, Object value) {
    this.columnName = columnName;
    this.value = value;
  }

  @Override
  public List<Row> filter(List<Row> rows) {
    List<Row> res = new ArrayList<>();
    for(Row row:rows) {
      if(row.getContent().get(columnName).equals(value)) {
        res.add(row);
      }
    }
    return res;
  }
}
