package com.ruskaof.lab2wildfly.model.auth;

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

/**
 * https://dev.vk.com/api/access-token/authcode-flow-user
 */
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

                    final var jsonAuthResponse = recvbuff.toString();
                    if (!jsonAuthResponse.contains("access_token")) {
                        System.out.println(jsonAuthResponse + " no access token(");
                        response.sendError(401);
                        return;
                    }
                    request.getSession().setAttribute(SessionAttribute.ACCESS_TOKEN.toString(), recvbuff);
                } catch (IOException e) {
                    System.out.println("io ex");
                    response.sendError(401);
                }
            }
        }
    }
}
