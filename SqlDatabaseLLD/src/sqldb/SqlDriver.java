package sqldb;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import sqldb.enums.ColumnType;
import sqldb.models.Column;
import sqldb.models.DataType.DataType;
import sqldb.models.DataType.IntType;
import sqldb.models.DataType.StringType;
import sqldb.models.constraints.IntConstraint;
import sqldb.models.constraints.StringConstraint;
import sqldb.services.DatabaseManager;

public class SqlDriver {

  public static void main(String[] args) {
    DatabaseManager databaseMgr = DatabaseManager.getInstance();
    String databaseName = databaseMgr.createDatabase("database");
    // Create column definitions
    List<Column> employeeColumns = new ArrayList<>();
    employeeColumns.add(new Column("id", new IntType(new IntConstraint()), ColumnType.PRIMARY_KEY));
    employeeColumns.add(new Column("name", new StringType(new StringConstraint()), ColumnType.REQUIRED));
    employeeColumns.add(new Column("age", new IntType(new IntConstraint(18,65)), ColumnType.NOT_REQUIRED));
    employeeColumns.add(new Column("department", new StringType(new StringConstraint()), ColumnType.NOT_REQUIRED));

    // Create a table
    String tableName = databaseMgr.createTable(databaseName, "employees", employeeColumns);

    LinkedHashMap<String, Object> emp1 = new LinkedHashMap<>();
    emp1.put("id", 1);
    emp1.put("name", "John Doe");
    emp1.put("age", 30);
    emp1.put("department", "IT");
    databaseMgr.insertRecord(databaseName, tableName, emp1);

    LinkedHashMap<String, Object> emp2 = new LinkedHashMap<>();
    emp2.put("id", 2);
    emp2.put("name", "Jane Smith");
    emp2.put("age", 25);
    emp2.put("department", "HR");
    databaseMgr.insertRecord(databaseName, tableName, emp2);

    LinkedHashMap<String, Object> emp3 = new LinkedHashMap<>();
    emp3.put("id", 3);
    emp3.put("name", "Bob Johnson");
    emp3.put("age", 40);
    emp3.put("department", "IT");
    databaseMgr.insertRecord(databaseName, tableName, emp3);

    // Print all records
    System.out.println("All records in employees table:");
    databaseMgr.printRecords(databaseName, tableName);

    // Filter records
    System.out.println("\nRecords with department = IT:");
    databaseMgr.filterRecords(databaseName, tableName, "department", "IT");

    // Delete table
    databaseMgr.deleteTable(databaseName, tableName);
  }
}
