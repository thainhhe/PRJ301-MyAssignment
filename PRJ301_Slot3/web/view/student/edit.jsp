<%-- 
    Document   : edit
    Created on : Oct 2, 2023, 9:15:21 AM
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
        <form action="edit" method="POST"> 
            Id: <input name="id" style="background-color: yellow;" type="text" readonly="readonly" value="${requestScope.student.id}"/> <br/>
            Name: <input type="text" name="name" value="${requestScope.student.name}" /> <br/>
            Gender: <input 
                <c:if test="${requestScope.student.gender}">
                checked="checked"
                </c:if>
                type="radio" value="male" name="gender"/> Male
            <input
                <c:if test="${!requestScope.student.gender}">
                checked="checked"
                </c:if>
                type="radio" value="female" name="gender"/> Female <br/>
            Dob: <input type="date" name="dob" value="${requestScope.student.dob}" /> <br/>
            Department: 
            <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option 
                        <c:if test="${d.id eq requestScope.student.dept.id}">
                        selected="selected"
                        </c:if>
                        value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <Br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
