package sqldb.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import sqldb.models.filters.Filter;

public class Table {
  List<Column> cols;
  Map<String, Column> columnMap;
  Map<String, Row> rows;
  String name;

  public Table(String name, List<Column> cols) {
    this.name = name;
    this.cols = cols;
    columnMap = new HashMap<>();
    for(Column col:cols) {
      columnMap.put(col.name, col);
    }
    rows = new TreeMap<>();
  }

  public List<Column> getColumns() {
    return cols;
  }

  public void create(List<Column> cols) {
    this.cols = cols;
  }

  public void insert(List<LinkedHashMap<String, Object>> values) {
    for(LinkedHashMap<String,Object> value:values) {
      Row row = new Row();
      row.insert(this, value);
      rows.put(value.get(row.id).toString(), row);
    }
  }

  public void update(String id, LinkedHashMap<String, Object> values) {
    if(!rows.containsKey(id)) return;
    Row row = rows.get(id);
    row.update(this, values);
    rows.put(row.id, row);
  }

  public List<Row> filter(List<Filter> filters) {
    List<Row> res = rows.values().stream().toList();
    for(Filter filter:filters) {
      res = filter.filter(res);
    }
    List<String> columns = cols.stream().map(a->a.name).toList();
    System.out.println(columns.toString());
    for(Row row: res) {
      row.print(this);
    }
    return res;
  }

  public void delete(String id) {
    rows.remove(id);
  }

  public void printRecords() {
    List<String> columns = cols.stream().map(a->a.name).toList();
    System.out.println(columns.toString());
    for(Row row: rows.values()) {
      row.print(this);
    }
  }
}
