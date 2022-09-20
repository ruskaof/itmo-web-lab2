package com.ruskaof.lab2wildfly.controller;

import com.google.gson.Gson;
import com.ruskaof.lab2wildfly.model.PARAM;
import com.ruskaof.lab2wildfly.model.TableData;
import com.ruskaof.lab2wildfly.model.TableRow;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Returns whole table data in JSON format
 */
@WebServlet(name = "ServletGetTable", value = "/ServletGetTable")
public class ServletGetTable extends HttpServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var tableData = (TableData) request.getSession().getAttribute(PARAM.TABLE_DATA.toString());
        if (tableData == null) tableData = new TableData();

        final var writer = response.getWriter();
        writer.println(gson.toJson(tableData));
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
