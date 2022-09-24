package com.ruskaof.lab2wildfly.controller;

import com.ruskaof.lab2wildfly.model.PARAM;
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
 * If request contains "x", "y", "r" parameters -> ServletAreaCheck is executed
 * If request contains "clear"  parameter -> ServletClearTable is executed
 * Else -> ServletGetTable is executed
 * <p>
 * These parameters should not be mixed to prevent unexpected response.
 */
@WebServlet(name = "ServletController", value = "/ServletController")
public class ServletController extends HttpServlet {
    private static final TransportClient transportClient = new HttpTransportClient();
    private static final VkApiClient vk = new VkApiClient(transportClient);

    private static final int APP_ID = 51433610;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        UserAuthResponse authResponse = vk.oAuth()
//                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
//                .execute();
//
//        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());


        //  Select a servlet to proceed the request
        if (request.getParameter(PARAM.X.toString()) != null
                && request.getParameter(PARAM.Y.toString()) != null
                && request.getParameter(PARAM.R.toString()) != null) {


            getServletContext().getNamedDispatcher("ServletAreaCheck").forward(request, response);
        } else if (request.getParameter(PARAM.CLEAR.toString()) != null) {
            getServletContext().getNamedDispatcher("ServletClearTable").forward(request, response);
        } else {
            getServletContext().getNamedDispatcher("ServletGetTable").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("wtf");
    }


}
