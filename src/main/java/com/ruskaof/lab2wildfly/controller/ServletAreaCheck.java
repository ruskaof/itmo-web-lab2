package com.ruskaof.lab2wildfly.controller;


import com.ruskaof.lab2wildfly.controller.utils.RequestParameter;
import com.ruskaof.lab2wildfly.model.repository.ClicksRepository;

import javax.ejb.EJB;
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
    @EJB
    ClicksRepository clicksRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("ServletAreaCheck");
        long startTime = System.nanoTime();

        final var xString = request.getParameter(RequestParameter.X.toString());
        final var yString = request.getParameter(RequestParameter.Y.toString());
        final var rString = request.getParameter(RequestParameter.R.toString());
        final var roundString = request.getParameter(RequestParameter.ROUND.toString());

        final float x;
        final float y;
        final float r;
        final int round;
        try {
            x = Float.parseFloat(xString);
            y = Float.parseFloat(yString);
            r = Float.parseFloat(rString);
            round = Integer.parseInt(roundString);
        } catch (NumberFormatException | NullPointerException ignored) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (round != 0) {
            if (xString.length() > 14 || yString.length() > 14 || rString.length() > 14) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }


        final var isHit = isHit(x, y, r);


        clicksRepository.addNote(request.getSession(), new TableRow(x, y, r, isHit, LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM, dd, yyyy HH:mm:ss", Locale.US)), (System.nanoTime() - startTime) / 1000000.0F));
    }

    private static boolean isHit(float x, float y, float r) {

        final var triangleHit = (x <= 0 && x >= -r && y >= 0 && y <= r / 2 && r / 2 + 0.5 * x >= y);
        final var sectorHit = (x >= 0 && x <= r / 2 && y >= 0 && y <= r / 2 && x * x + y * y <= r * r / 4);
        final var rectangleHit = (x >= 0 && x <= r / 2 && y <= 0 && y >= -r);

        return triangleHit || sectorHit || rectangleHit;
    }
}
