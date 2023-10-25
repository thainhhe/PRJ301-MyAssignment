<%-- 
    Document   : login
    Created on : Oct 24, 2023, 11:56:05 PM
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
        <form action="MainController">
            UserID: <input type="text" name="txtUserID" value=""/> <br/>
            Password: <input type="password" name="txtPassword" value=""/> <br/>
            <input type="submit" name="action" value="LOGIN"/>
                   
        </form>
    </body>
</html>
