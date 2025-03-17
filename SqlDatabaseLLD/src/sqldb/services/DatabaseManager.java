package sqldb.services;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sqldb.models.Column;
import sqldb.models.Database;
import sqldb.models.Row;
import sqldb.models.Table;
import sqldb.models.filters.EqualsFilter;

public class DatabaseManager {
  private static volatile DatabaseManager instance;

  public static DatabaseManager getInstance() {
    if(instance == null) {
      synchronized (DatabaseManager.class) {
        if (instance == null) {
          instance = new DatabaseManager();
        }
      }
    }
    return instance;
  }

  Map<String, Database> databaseMap = new HashMap<>();

  private DatabaseManager() {
    this.databaseMap = new HashMap<>();
  }

  // Create a new database and return its ID (name)
  public String createDatabase(String databaseName) {
    if (databaseMap.containsKey(databaseName)) {
      throw new IllegalArgumentException("Database " + databaseName + " already exists");
    }

    Database database = new Database(databaseName);
    databaseMap.put(databaseName, database);
//    System.out.println("Database " + databaseName + " created successfully");
    return databaseName;
  }

  // Delete a database by ID
  public void deleteDatabase(String databaseId) {
    validateDatabaseExists(databaseId);
    databaseMap.remove(databaseId);
  }

  // Create a table in a database and return its ID
  public String createTable(String databaseId, String tableName, List<Column> columnDefinitions) {
    validateDatabaseExists(databaseId);
    Database database = databaseMap.get(databaseId);
    database.createTable(tableName, columnDefinitions);
    return tableName;
  }

  // Delete a table from a database by IDs
  public void deleteTable(String databaseId, String tableId) {
    validateDatabaseExists(databaseId);
    Database database = databaseMap.get(databaseId);
    database.deleteTable(tableId);
  }

  // Insert a record into a table
  public void insertRecord(String databaseId, String tableId, LinkedHashMap<String, Object> values) {
    validateDatabaseExists(databaseId);
    Database database = databaseMap.get(databaseId);
    database.insert(tableId, values);
  }

  // Print all records in a table
  public void printRecords(String databaseId, String tableId) {
    validateDatabaseExists(databaseId);
    Database database = databaseMap.get(databaseId);
    database.printRecords(tableId);
  }

  // Filter records in a table and return results
  public void filterRecords(String databaseId, String tableId, String columnName, Object value) {
    validateDatabaseExists(databaseId);
    Database database = databaseMap.get(databaseId);
    List<Row> rows = database.filterRecords(tableId, List.of(new EqualsFilter(columnName, value)));
  }

  // Helper method to validate database exists
  private void validateDatabaseExists(String databaseId) {
    if (!databaseMap.containsKey(databaseId)) {
      throw new IllegalArgumentException("Database " + databaseId + " does not exist");
    }
  }
}
