<%-- 
    Document   : rect
    Created on : Sep 18, 2023, 8:17:33 AM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Rect"%>
<%@ page isELIgnored = "false"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        ${ requestScope.rects[0].x mod 2 eq 0?"Even":"Odd"}
        
        <canvas id="myCanvas" width="500" height="500" style="border:1px solid grey"></canvas>
        <script>
            const c = document.getElementById("myCanvas");
            const ctx = c.getContext("2d");
            ctx.beginPath();
            <c:forEach items="${requestScope.rects}" var="r" varStatus="loop" >
               
                    ctx.fillStyle = "rgb(${r.r},${r.g},${r.b})";
                    ctx.fillRect(${r.x}, ${r.y}, ${r.w}, ${r.h});
                
                ctx.rect(${r.x}, ${r.y}, ${r.w}, ${r.h});
            </c:forEach>
            
            ctx.stroke();
        </script> 
    </body>
</html>
