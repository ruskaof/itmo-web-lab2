package com.ruskaof.lab2wildfly.controller;

import com.google.gson.Gson;
import com.ruskaof.lab2wildfly.model.repository.ClicksRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns whole table data in JSON format
 */
@WebServlet(name = "ServletGetTableClicks")
public class ServletGetTableClicks extends HttpServlet {
    private static final Gson gson = new Gson();

    @EJB
    ClicksRepository clicksRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var tableData = clicksRepository.getClicks(request.getSession());

        final var writer = response.getWriter();
        writer.println(gson.toJson(tableData));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
