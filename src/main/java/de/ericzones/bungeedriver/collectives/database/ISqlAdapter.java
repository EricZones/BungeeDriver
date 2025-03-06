// Created by Eric B. 17.05.2021 17:34
package de.ericzones.bungeedriver.collectives.database;

import de.ericzones.bungeedriver.collectives.object.Pair;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface ISqlAdapter {

    void createTable(String tableName, Pair<String, SqlDataType>[] content, String primaryKey);
    void createTable(String tableName, Pair<String, SqlDataType>[] content);

    void updateInTable(String tableName, String column, Object value, String setColumn, Object setValue);
    void updateAllInTable(String tableName, String column, Object setValue);
    void updateMultipleInTable(String tableName, String column, Object value, String column2, Object value2, String column3, Object value3, String setColumn, Object setValue);

    void addToTable(String tableName, List<String> columns, List<Object> values);

    void removeFromTable(String tableName, String column, Object value, String column2, Object value2);
    void removeFromTable(String tableName, String column, Object value);
    void removeAllFromTable(String tableName);

    boolean existsInTable(String tableName, String column, Object value);
    boolean existsInTable(String tableName, String column, Object value, String column2, Object value2);
    boolean multipleExistsInTable(String tableName, String column, Object value, String column2, Object value2, String column3, Object value3);

    ResultSet getResultsFromTable(String tableName, String column, Object value);
    ResultSet getResultsFromTable(String tableName, String column, String column2, Object value);
    ResultSet getResultsFromTable(String tableName);
    ResultSet getMultipleResultsFromTable(String tableName, String column, Object value, String column2, Object value2, String column3, Object value3);

    Object getObjectFromTable(String tableName, String column, Object value, String neededColumn);
    List<Object> getObjectListFromTable(String tableName, String column, Object value, String[] columnNames);

    Map<String, List<Object>> getAllObjectsFromTablePrimaryKey(String tableName, String[] columnNames);
    List<List<Object>> getAllObjectsFromTable(String tableName, String[] columnNames);
    List<List<Object>> getAllObjectsFromTable(String tableName, String column, Object value, String[] columnNames);
    List<List<Object>> getAllObjectsFromTable(String tableName, String column, String column2, Object value, String[] columnNames);

    ResultSet getDescAmountResultsFromTable(String tableName, String orderColumn, int amount);

}
