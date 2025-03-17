package sqldb.models.filters;

import java.util.LinkedHashMap;
import java.util.List;
import sqldb.models.Row;

public interface Filter {
  List<Row> filter(List<Row> rows);
}
