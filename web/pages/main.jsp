<%-- 
    Document   : main
    Created on : 04.05.2018, 19:31:00
    Author     : Вадим
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% request.setCharacterEncoding("UTF-8"); %>
        <%= "Привет " + request.getParameter("username")%>
    </body>
</html>
