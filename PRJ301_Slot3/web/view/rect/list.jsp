<%-- 
    Document   : rect
    Created on : Sep 18, 2023, 8:17:33 AM
    Author     : sonnt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Rect"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Rect> rects = (ArrayList<Rect>)request.getAttribute("rects");
        %>
        <canvas id="myCanvas" width="500" height="500" style="border:1px solid grey"></canvas>
        <script>
            const c = document.getElementById("myCanvas");
            const ctx = c.getContext("2d");
            ctx.beginPath();
            <% for(Rect rect : rects){ %>
                ctx.fillStyle = "rgb(<%=rect.getR()%>,<%=rect.getG()%>,<%=rect.getB()%>)";
                ctx.fillRect(<%=rect.getX()%>, <%=rect.getY()%>, <%=rect.getW()%>, <%=rect.getH()%>);
                ctx.rect(<%=rect.getX()%>, <%=rect.getY()%>, <%=rect.getW()%>, <%=rect.getH()%>);
            <%}%>
            
            ctx.stroke();
        </script> 
    </body>
</html>
