package sqldb.models;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sqldb.models.filters.Filter;

public class Database {
  String name;

  public Database(String name) {
    this.name = name;
    tableMap = new HashMap<>();
  }

  Map<String, Table> tableMap;

  public Table createTable(String name, List<Column> columns) {
    if(tableMap.containsKey(name)) return null;
    Table table = new Table(name, columns);
    tableMap.put(name, table);
    return table;
  }

  public void deleteTable(String name) {
    tableMap.remove(name);
  }

  public void insert(String name, LinkedHashMap<String, Object> values) {
    Table table = tableMap.get(name);
    table.insert(List.of(values));
  }

  public Table getTable(String name) {
    return tableMap.getOrDefault(name, null);
  }

  public void printRecords(String name) {
    Table table = getTable(name);
    table.printRecords();
  }

  public List<Row> filterRecords(String name, List<Filter> filters) {
    Table table = getTable(name);
    return table.filter(filters);
  }
}
