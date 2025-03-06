// Created by Eric B. 17.05.2021 17:50
package de.ericzones.bungeedriver.collectives.database;

public enum SqlDataType {

    VARCHAR("VARCHAR(255)"),
    BOOLEAN("BOOLEAN"),
    INT("BIGINT UNSIGNED"),
    FLOAT("FLOAT(500,250)"),
    DOUBLE("DOUBLE(1500,500)"),
    TEXT("MEDIUMTEXT");

    private SqlDataType(String sqlTag){
        this.sqlTag = sqlTag;
    };

    private String sqlTag;

    public String getSqlTag() {
        return sqlTag;
    }

}
