<%-- 
    Document   : search
    Created on : Oct 4, 2023, 11:56:08 AM
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
        <form id="searchForm" action="search" method="GET"> 
            
            Department: 
            <select name="did" onchange="document.getElementById('searchForm').submit();">
                <option value="0">-Select a department-</option>
                <c:forEach items="${requestScope.depts}" var="d">
                    <option
                        <c:if test="${d.id eq param.did}">
                            selected="selected"
                        </c:if>
                        value="${d.id}">${d.name}</option>
                </c:forEach>
            </select>
            <table border="1px">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Dob</td>
                <td>Department</td>
                <td></td>
            </tr>
            <c:forEach items="${requestScope.students}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td><c:if test="${s.gender}">
                            <img src="../img/1064228_619013178110687_1088476389_o.jpg" width="24" height="24"  alt=""/>
                        </c:if></td>
                    <td>${s.dob}</td>
                    <td>${s.dept.name}</td>
                    <td>
                        <a href="edit?id=${s.id}">Edit</a>
                        <input type="button" value="Delete" onclick="deleteStudent(${s.id})"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
        </form>
    </body>
</html>
