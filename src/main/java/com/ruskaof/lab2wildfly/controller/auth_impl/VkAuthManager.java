package com.ruskaof.lab2wildfly.controller.auth_impl;

import com.ruskaof.lab2wildfly.controller.auth.AuthManager;
import com.ruskaof.lab2wildfly.controller.utils.Constants;
import com.ruskaof.lab2wildfly.controller.utils.SessionAttribute;
import com.ruskaof.lab2wildfly.controller.utils.Urls;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class VkAuthManager implements AuthManager {
    @Override
    public void makeAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute(SessionAttribute.ACCESS_TOKEN.toString()) == null) {
            if (request.getParameter("code") == null) {
                String redirectURL = Urls.vkOauthAuthorizePage(Constants.CLIENT_ID, Urls.BASE_URL);
                response.sendRedirect(redirectURL);
            } else {
                try {
                    String redirectURL = Urls.vkOauthGetAccessToken(Constants.CLIENT_ID, Urls.BASE_URL, Constants.CLIENT_SECRET, request.getParameter("code"));

                    String recv;
                    StringBuilder recvbuff = new StringBuilder();
                    URL jsonpage = new URL(redirectURL);
                    URLConnection urlcon = jsonpage.openConnection();
                    BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));

                    while ((recv = buffread.readLine()) != null)
                        recvbuff.append(recv);
                    buffread.close();

                    System.out.println(recvbuff);
                    request.getSession().setAttribute(SessionAttribute.ACCESS_TOKEN.toString(), recvbuff);
                } catch (IOException e) {
                    response.sendError(401);
                }
            }
        }
    }
}
