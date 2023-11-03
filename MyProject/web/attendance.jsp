<%-- 
    Document   : attendance
    Created on : Oct 4, 2023, 2:22:56 PM
    Author     : Admin
--%>
<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${requestScope.ses.group.name}-${requestScope.ses.subject.name}-${requestScope.ses.room.rid}
        -${requestScope.ses.time.description}
        <br/>
        <form action="attendance" method="POST">
            <table border="1px"> 
                <tr>
                    <td>Student</td>
                    <td>Status</td>
                    <td>Description</td>
                    <td>Time</td>
                </tr>
                <c:forEach items="${requestScope.atts}" var="a">
                 <tr>
                    <td>${a.student.name}
                        <input type="hidden" name="stuid" value="${a.student.id}"/>
                    </td>
                    <td>
                        <input type="radio"
                               <c:if test="${!a.status}">
                                   checked="checked"
                               </c:if>
                               name="status${a.student.id}" value="absent"/>absent
                        <input type="radio"
                               <c:if test="${a.status}">
                                   checked="checked"
                               </c:if>
                               name="status${a.student.id}" value="present"/>present
                        </td>
                    <td>
                        <input type="text" value="${a.description}"
                               name="description${a.student.id}"/>
                    </td>
                    <td>${a.datetime}</td>
                </tr>   
                    
                </c:forEach>
            </table>
            <input type="hidden" value="${requestScope.ses.id}" name="sesid"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Session" %>
<%@page import="java.util.ArrayList" %>
<%@page import="controller.AttendanceController" %>
<%@ page import="entity.Group" %>
<%@ page import="entity.Subject" %>
<%@ page import="entity.Room" %>
<%@ page import="entity.TimeSlot" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%= request.getAttribute("ses.group.name") + "-" + request.getAttribute("ses.subject.name") + "-" + request.getAttribute("ses.room.rid") + "-" + request.getAttribute("ses.time.description") %>
        <br/>
        <form action="attendance" method="POST">
            <table border="1px"> 
                <tr>
                    <td>Student</td>
                    <td>Status</td>
                    <td>Description</td>
                    <td>Time</td>
                </tr>
                <% 
                    ArrayList<Attendance> atts = (ArrayList<Attendance>) request.getAttribute("atts");
                    if (atts != null && !atts.isEmpty()) {
                        for (Attendance a : atts) {
                %>
                <tr>
                    <td><%= a.getStudent().getName() %>
                        <input type="hidden" name="stuid" value="<%= a.getStudent().getId() %>"/>
                    </td>
                    <td>
                        <input type="radio" <%= !a.getStatus() ? "checked='checked'" : "" %> name="status<%= a.getStudent().getId() %>" value="absent"/>absent
                        <input type="radio" <%= a.getStatus() ? "checked='checked'" : "" %> name="status<%= a.getStudent().getId() %>" value="present"/>present
                    </td>
                    <td>
                        <input type="text" value="<%= a.getDescription() %>" name="description<%= a.getStudent().getId() %>"/>
                    </td>
                    <td><%= a.getDatetime() %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <input type="hidden" value="<%= request.getAttribute("ses.id") %>" name="sesid"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>


<%--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Page</title>
</head>
<body>
    ${requestScope.ses.group.name}-${requestScope.ses.subject.name}-${requestScope.ses.room.rid}
    -${requestScope.ses.time.description}
    <br/>
    <form action="attendance" method="POST">
        <table border="1px">
            <tr>
                <td>Student</td>
                <td>Status</td>
                <td>Description</td>
                <td>Time</td>
            </tr>
            <c:forEach var="a" items="${requestScope.atts}">
                <tr>
                    <td>${a.student.name}
                        <input type="hidden" name="stuid" value="${a.student.id}"/>
                    </td>
                    <td>
                        <input type="radio"
                            <c:if test="${!a.status}">
                                checked="checked"
                            </c:if>
                            name="status${a.student.id}" value="absent"/>absent
                        <input type="radio"
                            <c:if test="${a.status}">
                                checked="checked"
                            </c:if>
                            name="status${a.student.id}" value="present"/>present
                    </td>
                    <td>
                        <input type="text" value="${a.description}"
                            name="description${a.student.id}"/>
                    </td>
                    <td>${a.datetime}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" value="${requestScope.ses.id}" name="sesid"/>
        <input type="submit" value="Save"/>
    </form>
</body>
</html>
--%>
