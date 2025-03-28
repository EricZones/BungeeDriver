// Created by Eric B. 17.05.2021 17:12
package de.ericzones.bungeedriver.global.language;

public enum Language {

    GERMAN("de", 0),
    ENGLISH("en", 1);

    private Language(String synonym, int id) {
        this.synonym = synonym;
        this.id = id;
    }

    private String synonym;
    private int id;

    public String getSynonym() {
        return synonym;
    }

    public int getId() {
        return id;
    }

    public static Language getLanguageFromId(int id) {
        for(Language current : Language.values()) {
            if (current.getId() == id) return current;
        }
        return null;
    }

}
