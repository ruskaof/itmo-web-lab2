package com.ruskaof.lab2wildfly.controller.utils;

public enum SessionAttribute {
    ACCESS_TOKEN("access_token");

    private final String stringName;

    SessionAttribute(String stringName) {
        this.stringName = stringName;
    }

    @Override
    public String toString() {
        return stringName;
    }
}
