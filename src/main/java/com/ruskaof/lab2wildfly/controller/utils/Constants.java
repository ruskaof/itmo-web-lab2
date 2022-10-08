package com.ruskaof.lab2wildfly.controller.utils;

import java.util.Map;

public final class Constants {
    public static final String CLIENT_ID;
    public static final String CLIENT_SECRET;

    static {
        final Map<String, String> env = System.getenv();
        CLIENT_ID = env.get("LAB2_VK_CLIENT_ID");
        CLIENT_SECRET = env.get("LAB2_VK_CLIENT_SECRET");

        System.out.println(CLIENT_ID + " " + CLIENT_SECRET);
    }

    private Constants() {
        throw new UnsupportedOperationException("This is a util class that must not be initialised");
    }
}
