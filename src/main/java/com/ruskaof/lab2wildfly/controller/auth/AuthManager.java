package com.ruskaof.lab2wildfly.controller.auth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface AuthManager {
    void makeAuth(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
