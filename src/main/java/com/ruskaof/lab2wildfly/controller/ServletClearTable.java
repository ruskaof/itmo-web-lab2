package com.ruskaof.lab2wildfly.controller;

import com.ruskaof.lab2wildfly.model.PARAM;
import com.ruskaof.lab2wildfly.model.TableData;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletClearTable", value = "/ServletClearTable")
public class ServletClearTable extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute(PARAM.TABLE_DATA.toString(), new TableData());
    }
}
