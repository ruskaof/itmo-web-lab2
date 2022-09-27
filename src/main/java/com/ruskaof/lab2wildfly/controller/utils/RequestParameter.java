package com.ruskaof.lab2wildfly.controller.utils;

public enum RequestParameter {
    X("x"),
    Y("y"),
    R("r"),
    ROUND("round_if_needed"),
    CLEAR("clear"),
    TABLE_HTML("table_html");


    private final String stringName;

    RequestParameter(String stringName) {
        this.stringName = stringName;
    }

    @Override
    public String toString() {
        return stringName;
    }
}
