package com.ruskaof.lab2wildfly.controller.utils;

public enum SessionAttribute {
    CLICKS_REPOSITORY("clicks_repository");
//    TABLE_DATA("table_data");

    private final String stringName;

    SessionAttribute(String stringName) {
        this.stringName = stringName;
    }

    @Override
    public String toString() {
        return stringName;
    }
}
