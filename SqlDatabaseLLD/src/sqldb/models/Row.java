package sqldb.models;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import sqldb.enums.ColumnType;
import sqldb.models.filters.Filter;

public class Row {
  String id;
  LinkedHashMap<String, Object> content = new LinkedHashMap<>();

  public void insert(Table table, LinkedHashMap<String, Object> content) {
    List<Column> cols = table.getColumns();
    int i = 0;
    for(String key: content.keySet()) {
      if(!cols.get(i).validate(key, content.get(key))) {
        return;
      }
      if(cols.get(i).columnType.equals(ColumnType.PRIMARY_KEY)) {
        id = cols.get(i).name;
      }
      i++;
    }
    if(this.id==null) return;
    this.content = content;
  }

  public void update(Table table, LinkedHashMap<String, Object> content) {

  }

  public void print(Table table) {
    System.out.println(content.values());
  }

  public LinkedHashMap<String, Object> getContent() {
    return content;
  }
}
