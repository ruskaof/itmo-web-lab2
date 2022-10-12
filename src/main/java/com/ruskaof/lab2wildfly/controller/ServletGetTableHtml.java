package com.ruskaof.lab2wildfly.controller;

import com.ruskaof.lab2wildfly.model.repository.ClicksRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletGetTableHtml")
public class ServletGetTableHtml extends HttpServlet {
    @EJB
    ClicksRepository clicksRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TableData tableData = clicksRepository.getClicks(request.getSession());

        final PrintWriter writer = response.getWriter();

        printHtmlTable(writer, tableData);

        writer.close();
    }

    private void printHtmlTable(PrintWriter writer, TableData tableData) {
        for (int i = 0; i < tableData.tableRowList().size(); i++) {
            writer.println("<tr>");
            final TableRow row = tableData.tableRowList().get(i);
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
    }
}
