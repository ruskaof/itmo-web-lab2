package com.ruskaof.lab2wildfly.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruskaof.lab2wildfly.model.PARAM;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Main servlet that accepts all the requests (in post body with JSON) from client and
 * delegates its work to other servlets.
 *
 * If request is a POST method and contains "x", "y", "r" parameters -> ServletAreaCheck is executed
 * If request is a POST method contains "clear"  parameter -> ServletClearTable is executed
 * If request is a GET method -> ServletGetTable is executed
 *
 * These parameters should not be mixed to prevent unexpected response.
 */
@WebServlet(name = "ServletController", value = "/ServletController")
public class ServletController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getNamedDispatcher("ServletGetTable").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Convert request body to map of strings if the JSON is correct
        final Map<String, String> requestBody;
        try {
            final var mapType = new TypeToken<Map<String, String>>() {
            }.getType();
            final var requestString = getBody(request);
            requestBody = new Gson().fromJson(requestString, mapType);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not parse json");
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return;
        }

        //  Select a servlet to proceed the request
        if (requestBody.containsKey(PARAM.X.toString())
                && requestBody.containsKey(PARAM.Y.toString())
                && requestBody.containsKey(PARAM.R.toString())) {
            request.setAttribute(PARAM.X.toString(), requestBody.get(PARAM.X.toString()));
            request.setAttribute(PARAM.Y.toString(), requestBody.get(PARAM.Y.toString()));
            request.setAttribute(PARAM.R.toString(), requestBody.get(PARAM.R.toString()));


            getServletContext().getNamedDispatcher("ServletAreaCheck").forward(request, response);
        } else if (requestBody.containsKey(PARAM.CLEAR.toString())) {
            request.setAttribute(PARAM.CLEAR.toString(), requestBody.get(PARAM.CLEAR.toString()));

            getServletContext().getNamedDispatcher("ServletClearTable").forward(request, response);
        }
    }

    // from StackOverflow https://stackoverflow.com/questions/14525982/getting-request-payload-from-post-request-in-java-servlet
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
