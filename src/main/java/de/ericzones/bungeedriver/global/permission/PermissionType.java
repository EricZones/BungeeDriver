// Created by Eric B. 17.05.2021 17:29
package de.ericzones.bungeedriver.global.permission;

public enum PermissionType {

    ;

    private PermissionType(String standardPermission, int id) {
        this.standardPermission = standardPermission;
        this.id = id;
    }

    private static int currentId = 0;
    private String standardPermission;
    private int id;

    public String getStandardPermission() {
        return standardPermission;
    }

    public int getId() {
        return id;
    }

    public static PermissionType getPermissionTypeFromId(int id) {
        for(PermissionType current : PermissionType.values()) {
            if (current.getId() == id) return current;
        }
        return null;
    }

    private static int getCurrentId() {
        currentId++;
        return currentId-1;
    }

}
