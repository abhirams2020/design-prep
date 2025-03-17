package sqldb.models;

import sqldb.enums.ColumnType;
import sqldb.models.DataType.DataType;

public class Column {
  String name;
  DataType datatype;
  ColumnType columnType;

  public Column(String name, DataType datatype) {
    this.name = name;
    this.datatype = datatype;
    this.columnType = ColumnType.NOT_REQUIRED;
  }

  public Column(String name, DataType datatype, ColumnType columnType) {
    this.name = name;
    this.datatype = datatype;
    this.columnType = columnType;
  }

  public boolean validate(String name, Object data) {
    if(!name.equals(this.name)) return false;
    if(columnType != ColumnType.NOT_REQUIRED && data==null) return false;
    return datatype.validate(data);
  }
}
