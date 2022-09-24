package com.ruskaof.lab2wildfly.utils;

public final class Urls {
    public static final String BASE_URL = "http://127.0.0.1:80/lab2WildFly-1.0-SNAPSHOT";

    public static String vkOauthAuthorizePage(int clientId, String redirectUrl) {
        return "https://oauth.vk.com/authorize?client_id=" + clientId + "&redirect_uri=" + redirectUrl;
    }

    public static String vkOauthGetAccessToken(int clientId, String redirectUrl, String clientSecret, String code) {
        return "https://oauth.vk.com/access_token?client_id=" + clientId + "&client_secret=" + clientSecret +
                "&redirect_uri=" + redirectUrl + "&code=" + code;
    }
}
