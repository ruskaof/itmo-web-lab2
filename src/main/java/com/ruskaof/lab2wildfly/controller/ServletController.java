package com.ruskaof.lab2wildfly.controller;

import com.ruskaof.lab2wildfly.controller.utils.RequestParameter;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main servlet that accepts all the requests from client and
 * delegates its work to other servlets.
 * <p>
 * If request contains "x", "y", "r", "round_if_needed" parameters -> ServletAreaCheck is executed
 * If request contains "clear"  parameter -> ServletClearTable is executed
 * Else -> ServletGetTable is executed
 * <p>
 * These parameters should not be mixed to prevent unexpected response.
 */
@WebServlet(name = "ServletController", value = "/servlet-controller")
public class ServletController extends HttpServlet {
    private static final TransportClient transportClient = new HttpTransportClient();
    private static final VkApiClient vk = new VkApiClient(transportClient);

    private static final int APP_ID = 51433610;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  Select a servlet to proceed the request
        if (request.getParameter(RequestParameter.X.toString()) != null
                && request.getParameter(RequestParameter.Y.toString()) != null
                && request.getParameter(RequestParameter.R.toString()) != null
                && request.getParameter(RequestParameter.ROUND.toString()) != null) {

            System.out.println("redirect to area_check");
            getServletContext().getNamedDispatcher("ServletAreaCheck").forward(request, response);
        } else if (request.getParameter(RequestParameter.CLEAR.toString()) != null) {
            System.out.println("redirect to clear table");
            getServletContext().getNamedDispatcher("ServletClearTable").forward(request, response);
        } else if (request.getParameter(RequestParameter.TABLE_HTML.toString()) != null) {
            System.out.println("redirect to get table");
            getServletContext().getNamedDispatcher("ServletGetTableHtml").forward(request, response);
        } else {
            System.out.println("redirect to get table clicks");
            getServletContext().getNamedDispatcher("ServletGetTableClicks").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("wtf");
    }


}
