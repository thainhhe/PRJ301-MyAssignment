<%-- 
    Document   : insert
    Created on : Oct 2, 2023, 8:34:47 AM
    Author     : sonnt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../authentication/welcomeuser.jsp" />
        
        <form action="insert" method="POST"> 
            Name: <input type="text" name="name" /> <br/>
            Gender: <input type="radio" value="male" name="gender"/> Male
            <input type="radio" value="female" name="gender"/> Female <br/>
            Dob: <input type="date" name="dob" /> <br/>
            Department: 
            <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <Br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
