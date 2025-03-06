// Created by Eric B. 17.05.2021 17:34
package de.ericzones.bungeedriver.collectives.database;

import de.ericzones.bungeedriver.collectives.object.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlAdapter implements ISqlAdapter {

    private SqlAPI sqlAPI;

    public SqlAdapter(SqlAPI sqlAPI) {
        this.sqlAPI = sqlAPI;
    }

    @Override
    public void createTable(String tableName, Pair<String, SqlDataType>[] content, String primaryKey) {
        StringBuilder update = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        for(int i = 0; i < content.length; i++)
            update.append(content[i].getFirstObject()).append(" ").append(content[i].getSecondObject().getSqlTag()).append(", ");
        //update.append(content[i].getFirstObject()).append(" ").append(content[i].getSecondObject().getSqlTag()).append(i + 1 >= content.length ? ")" : ", ");
        update.append("PRIMARY KEY(").append(primaryKey).append("))");

        sqlAPI.update(update.toString());
    }

    @Override
    public void createTable(String tableName, Pair<String, SqlDataType>[] content) {
        StringBuilder update = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        for(int i = 0; i < content.length; i++)
            update.append(content[i].getFirstObject()).append(" ").append(content[i].getSecondObject().getSqlTag()).append(i + 1 >= content.length ? ")" : ", ");

        sqlAPI.update(update.toString());
    }

    @Override
    public void updateInTable(String tableName, String column, Object value, String setColumn, Object setValue) {
        String update = "UPDATE "+tableName+" SET "+setColumn+"='"+setValue+"' WHERE "+column+"='"+value+"'";
        sqlAPI.update(update);
    }

    @Override
    public void updateAllInTable(String tableName, String column, Object setValue) {
        String update = "UPDATE "+tableName+" SET "+column+"='"+setValue+"'";
        sqlAPI.update(update);
    }

    @Override
    public void updateMultipleInTable(String tableName, String column, Object value, String column2, Object value2, String column3, Object value3, String setColumn, Object setValue) {
        String update = "UPDATE "+tableName+" SET "+setColumn+"='"+setValue+"' WHERE "+column+"='"+value+"' AND "+column2+"='"+value2+"' AND "+column3+"='"+value3+"'";
        sqlAPI.update(update);
    }

    @Override
    public void addToTable(String tableName, List<String> columns, List<Object> values) {
        StringBuilder update = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        for(int i = 0; i < columns.size(); i++)
            update.append(columns.get(i)).append(i + 1 >= columns.size() ? ")" : ", ");
        update.append(" VALUES ('");
        for(int i = 0; i < values.size(); i++)
            update.append(values.get(i)).append(i + 1 >= values.size() ? "')" : "', '");

        sqlAPI.update(update.toString());
    }

    @Override
    public void removeFromTable(String tableName, String column, Object value, String column2, Object value2) {
        String update = "DELETE FROM "+tableName+" WHERE "+column+"='"+value+"' AND "+column2+"='"+value2+"'";
        sqlAPI.update(update);
    }

    @Override
    public void removeFromTable(String tableName, String column, Object value) {
        String update = "DELETE FROM "+tableName+" WHERE "+column+"='"+value+"'";
        sqlAPI.update(update);
    }

    @Override
    public void removeAllFromTable(String tableName) {
        String update = "DELETE FROM "+tableName;
        sqlAPI.update(update);
    }

    @Override
    public boolean existsInTable(String tableName, String column, Object value) {
        ResultSet resultSet = getResultsFromTable(tableName, column, value);
        try {
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsInTable(String tableName, String column, Object value, String column2, Object value2) {
        ResultSet resultSet = getResultsFromTable(tableName, column, value);
        ResultSet resultSet2 = getResultsFromTable(tableName, column2, value2);
        try {
            if(resultSet.next() && resultSet2.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean multipleExistsInTable(String tableName, String column, Object value, String column2, Object value2, String column3, Object value3) {
        ResultSet resultSet = getMultipleResultsFromTable(tableName, column, value, column2, value2, column3, value3);
        try {
            if (resultSet.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet getResultsFromTable(String tableName, String column, Object value) {
        return sqlAPI.getResult("SELECT * FROM "+tableName+" WHERE "+column+"='"+value+"'");
    }

    @Override
    public ResultSet getResultsFromTable(String tableName, String column, String column2, Object value) {
        return sqlAPI.getResult("SELECT * FROM "+tableName+" WHERE "+column+"='"+value+"' OR "+column2+"='"+value+"'");
    }

    @Override
    public ResultSet getResultsFromTable(String tableName) {
        return sqlAPI.getResult("SELECT * FROM "+tableName);
    }

    @Override
    public ResultSet getMultipleResultsFromTable(String tableName, String column, Object value, String column2, Object value2, String column3, Object value3) {
        return sqlAPI.getResult("SELECT * FROM "+tableName+" WHERE "+column+"='"+value+"' AND "+column2+"='"+value2+"' AND "+column3+"='"+value3+"'");
    }

    @Override
    public Object getObjectFromTable(String tableName, String column, Object value, String neededColumn) {
        ResultSet resultSet = getResultsFromTable(tableName, column, value);
        Object object = null;
        try {
            if(resultSet.next())
                object = resultSet.getObject(neededColumn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public List<Object> getObjectListFromTable(String tableName, String column, Object value, String[] columnNames) {
        ResultSet resultSet = getResultsFromTable(tableName, column, value);
        List<Object> objectList = new ArrayList<>();
        try {
            for(int i = 1; i < columnNames.length+1; i++)
                objectList.add( resultSet.getObject(columnNames[i]));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    @Override
    public Map<String, List<Object>> getAllObjectsFromTablePrimaryKey(String tableName, String[] columnNames) {
        ResultSet resultSet = getResultsFromTable(tableName);
        Map<String, List<Object>> objectList = new HashMap<>();
        try {
            while (resultSet.next()) {
                for(int i = 1; i < columnNames.length+1; i++) {
                    if(i == 1) objectList.put(resultSet.getString(1), new ArrayList<>());
                    else objectList.get(resultSet.getString(1)).add(resultSet.getObject(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    @Override
    public List<List<Object>> getAllObjectsFromTable(String tableName, String[] columnNames) {
        ResultSet resultSet = getResultsFromTable(tableName);
        List<List<Object>> objectList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                List<Object> currentList = new ArrayList<>();
                for(int i = 1; i < columnNames.length+1; i++)
                    currentList.add(resultSet.getObject(i));
                objectList.add(currentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    @Override
    public List<List<Object>> getAllObjectsFromTable(String tableName, String column, Object value, String[] columnNames) {
        ResultSet resultSet = getResultsFromTable(tableName, column, value);
        List<List<Object>> objectList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                List<Object> currentList = new ArrayList<>();
                for(int i = 1; i < columnNames.length+1; i++)
                    currentList.add(resultSet.getObject(i));
                objectList.add(currentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    @Override
    public List<List<Object>> getAllObjectsFromTable(String tableName, String column, String column2, Object value, String[] columnNames) {
        ResultSet resultSet = getResultsFromTable(tableName, column, column2, value);
        List<List<Object>> objectList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                List<Object> currentList = new ArrayList<>();
                for(int i = 1; i < columnNames.length+1; i++)
                    currentList.add(resultSet.getObject(i));
                objectList.add(currentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objectList;
    }

    @Override
    public ResultSet getDescAmountResultsFromTable(String tableName, String orderColumn, int amount) {
        return sqlAPI.getResult("SELECT * FROM "+tableName+" ORDER BY "+orderColumn+" DESC LIMIT "+amount);
    }

}
