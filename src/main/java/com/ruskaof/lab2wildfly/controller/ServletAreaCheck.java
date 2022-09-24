package com.ruskaof.lab2wildfly.controller;


import com.ruskaof.lab2wildfly.model.TableData;
import com.ruskaof.lab2wildfly.model.TableRow;
import com.ruskaof.lab2wildfly.utils.Parameter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Gets x, y, r in request attributes. Parses them. Returns 400 if it can't.
 * Returns the whole new table body after adding the new click to it.
 */
@WebServlet(name = "ServletAreaCheck")
public class ServletAreaCheck extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("ServletAreaCheck");
        long startTime = System.nanoTime();

        final var xString = request.getParameter(Parameter.X.toString());
        final var yString = request.getParameter(Parameter.Y.toString());
        final var rString = request.getParameter(Parameter.R.toString());

        final float x;
        final float y;
        final float r;
        try {
            x = Float.parseFloat(xString);
            y = Float.parseFloat(yString);
            r = Float.parseFloat(rString);
        } catch (NumberFormatException | NullPointerException ignored) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // TODO: add "rounding" handling

        final var isHit = isHit(x, y, r);

        var tableData = (TableData) request.getSession().getAttribute(Parameter.TABLE_DATA.toString());
        if (tableData == null) tableData = new TableData();


        tableData.tableRowList().add(new TableRow(x, y, r, isHit, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM, dd, yyyy HH:mm:ss", Locale.US)), (System.nanoTime() - startTime) / 1000000.0F));
        request.getSession().setAttribute(Parameter.TABLE_DATA.toString(), tableData);

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

    private static boolean isHit(float x, float y, float r) {

        final var triangleHit = (x <= 0 && x >= -r && y >= 0 && y <= r / 2 && r / 2 + 0.5 * x >= y);
        final var sectorHit = (x >= 0 && x <= r / 2 && y >= 0 && y <= r / 2 && x * x + y * y <= r * r / 4);
        final var rectangleHit = (x >= 0 && x <= r / 2 && y <= 0 && y >= -r);

        return triangleHit || sectorHit || rectangleHit;
    }
}
