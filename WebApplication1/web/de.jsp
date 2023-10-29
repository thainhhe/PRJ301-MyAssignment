<%@ page import="java.util.List" %>
<%@ page import="dal.NewClass" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
<% for (NewClass student : (List<NewClass>)request.getAttribute("des")) { %>
                <tr>
                    
                    <td><%= student.getDes() %></td>

           
                </tr>
            <% } %>
    </body>
</html>
