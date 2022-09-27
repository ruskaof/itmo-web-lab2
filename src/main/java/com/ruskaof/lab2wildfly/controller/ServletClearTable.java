package com.ruskaof.lab2wildfly.controller;

import com.ruskaof.lab2wildfly.model.ClicksRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletClearTable", value = "/ServletClearTable")
public class ServletClearTable extends HttpServlet {

    @EJB
    ClicksRepository clicksRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        clicksRepository.clear(request.getSession());
    }
}
