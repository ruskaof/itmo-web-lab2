<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.IOException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- Redirecting to VK to get the code for authorization -->
<%
    if (request.getParameter("code") == null) {
        String redirectURL = "https://oauth.vk.com/authorize?client_id=51433610&redirect_uri=http://127.0.0.1:80/lab2WildFly-1.0-SNAPSHOT/";
        response.sendRedirect(redirectURL);
    } else {
        try {
            String redirectURL = "https://oauth.vk.com/access_token?client_id=51433610&client_secret=ka2ybPtS0YoEU6WBMEFX&" +
                    "redirect_uri=http://127.0.0.1:80/lab2WildFly-1.0-SNAPSHOT/&code=" + request.getParameter("code");


            String recv;
            StringBuilder recvbuff = new StringBuilder();
            URL jsonpage = new URL(redirectURL);
            URLConnection urlcon = jsonpage.openConnection();
            BufferedReader buffread = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));

            while ((recv = buffread.readLine()) != null)
                recvbuff.append(recv);
            buffread.close();

            System.out.println(recvbuff);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>lab1</title>
    <link rel="stylesheet" href="./src/style/main.css">
    <script src="https://vk.com/js/api/openapi.js?169" type="text/javascript"></script>
</head>
<body>
<header>
    <h1>Dmitrii Rusinov</h1>
    <h2>P32302, var: 3216</h2>
</header>

<div class="main-site-body">
    <div class="list-item">
        <div class="card">
            <div class="gradient-animation-box">
                    <span>
                        <canvas id="graph" width="390" height="390"></canvas>
                    </span>
            </div>
        </div>
    </div>

    <div class="list-item">
        <div class="value-picker-field">
            <form id="form" method="post">
                <div class="value-picker-field">
                    <span class="label">X</span>
                    <input
                            class="value-picker-element"
                            type="text"
                            name="x"
                            id="input_x"
                            placeholder="0"
                            required
                    />
                    <span id="input_x_warning"></span>
                </div>
                <div class="value-picker-field">
                    <span class="label">Y</span>
                    <select
                            class="value-picker-element"
                            name="y"
                            id="select-y"
                    >
                        <option value="-3">-3</option>
                        <option value="-2">-2</option>
                        <option value="-1">-1</option>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="value-picker-field">
                    <span class="label">R</span>
                    <select
                            class="value-picker-element"
                            name="r"
                            id="select_r"
                    >
                        <option value="1">1</option>
                        <option value="1.5">1.5</option>
                        <option value="2">2</option>
                        <option value="2.5">2.5</option>
                        <option value="3">3</option>
                    </select>
                </div>
                <div class="buttons">
                    <div>
                        <button
                                class="mybutton"
                                id="submit_button"
                                type="button"
                        >
                            Send
                        </button>
                    </div>
                    <div>
                        <button
                                class="mybutton"
                                id="reset_button"
                                type="button"
                        >
                            Reset
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <div class="list-item">
            <div class="card">
                <table id="my_table">
                    <thead>
                    <tr>
                        <th>№</th>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Result</th>
                        <th>Attempt time</th>
                        <th>Process time</th>
                    </tr>
                    </thead>

                    <tbody id="tbody">
                    <jsp:useBean id="table_data" class="com.ruskaof.lab2wildfly.model.TableData" scope="session"/>
                    <c:forEach var="row" items="${table_data.tableRowList()}" varStatus="i">
                        <tr>
                            <td>${i.index}</td>
                            <td>${row.x()}</td>
                            <td>${row.y()}</td>
                            <td>${row.r()}</td>
                            <td>${row.wasHit()}</td>
                            <td>${row.attemptTime()}</td>
                            <td>${row.processTimeMills()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="list-item">
        <div id="vk_auth">

        </div>
        <script>

        </script>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script type="module" src="src/index.js"></script>
<script src="./src/scripts/vk_auth.js"></script>
</body>

</html>

