package com.ruskaof.lab2wildfly.model;

public enum PARAM {
    X("x"),
    Y("y"),
    R("r"),
    ROUND("round_if_needed"),
    TABLE_DATA("table_data"),
    CLEAR("clear"),
    GET_TABLE("");


    private final String stringName;
    PARAM(String stringName) {
        this.stringName = stringName;
    }

    @Override
    public String toString() {
        return stringName;
    }
}
