package com.ruskaof.lab2wildfly.controller;

import com.ruskaof.lab2wildfly.model.TableData;
import com.ruskaof.lab2wildfly.utils.Parameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletGetTableHtml", value = "/ServletGetTableHtml")
public class ServletGetTableHtml extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var tableData = (TableData) request.getSession().getAttribute(Parameter.TABLE_DATA.toString());
        if (tableData == null) tableData = new TableData();

        final var writer = response.getWriter();

        for (int i = 0; i < tableData.tableRowList().size(); i++) {
            writer.println("<tr>");
            final var row = tableData.tableRowList().get(i);
            writer.println("<td>" + i + "</td>");
            writer.println("<td>" + row.x() + "</td>");
            writer.println("<td>" + row.y() + "</td>");
            writer.println("<td>" + row.r() + "</td>");
            if (row.wasHit()) {
                writer.println("<td> <p class=\"status status-hit\">HIT</p></td>");
            } else {
                writer.println("<td> <p class=\"status status-miss\">MISS</p></td>");
            }
            writer.println("<td>" + row.attemptTime() + "</td>");
            writer.println("<td>" + row.processTimeMills() + "ms" + "</td>");
            writer.println("</tr>");
        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
