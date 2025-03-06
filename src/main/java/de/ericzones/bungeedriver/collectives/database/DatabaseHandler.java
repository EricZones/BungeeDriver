// Created by Eric B. 17.05.2021 17:51
package de.ericzones.bungeedriver.collectives.database;

public class DatabaseHandler {

    private ISqlAdapter sqlAdapter;
    private SqlAPI sqlAPI;

    public DatabaseHandler(String database) {
        this.sqlAPI = new SqlAPI("localhost", 3306, database, "username", "password");
        sqlAPI.connect();
        this.sqlAdapter = new SqlAdapter(sqlAPI);
    }

    public void disconnectDatabase() {
        this.sqlAPI.disconnect();
    }

    public ISqlAdapter getSqlAdapter() {
        return sqlAdapter;
    }

}
