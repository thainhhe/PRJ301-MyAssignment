<%-- 
    Document   : login
    Created on : Oct 4, 2023, 1:58:14 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action = "LogInPage" method="POST">
            
            Gmail: <input type="text" name="gmail"><br>
            Password: <input type="password" name="password"><br>
            
            <input type="submit" value="Login">
            
        </form>
    </body>
</html>
