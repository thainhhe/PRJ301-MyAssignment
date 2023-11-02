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
        <% 
            Object ses = request.getAttribute("ses");
            String groupName = (ses != null) ? ((Group)ses.getClass().getMethod("getGroup").invoke(ses)).getName() : "";
            String subjectName = (ses != null) ? ((Subject)ses.getClass().getMethod("getSubject").invoke(ses)).getName() : "";
            String roomRid = (ses != null) ? ((Room)ses.getClass().getMethod("getRoom").invoke(ses)).getRid() : "";
            String timeDescription = (ses != null) ? ((TimeSlot)ses.getClass().getMethod("getTime").invoke(ses)).getDescription() : "";
        %>
        <%= groupName + "-" + subjectName + "-" + roomRid + "-" + timeDescription %>
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
        Object atts = request.getAttribute("atts");
        if (atts != null && atts instanceof java.util.ArrayList) {
            java.util.ArrayList attsList = (java.util.ArrayList) atts;
            for (Object a : attsList) {
                Object student = a.getClass().getMethod("getStudent").invoke(a);
                String studentName = (student != null) ? (String) student.getClass().getMethod("getName").invoke(student) : "";
                int studentId = (student != null) ? (int) student.getClass().getMethod("getId").invoke(student) : 0;
                boolean status = (boolean) a.getClass().getMethod("isStatus").invoke(a);
                String description = (String) a.getClass().getMethod("getDescription").invoke(a);
                Object datetime = a.getClass().getMethod("getDatetime").invoke(a);
                %>
                <tr>
                    <td><%= studentName %>
                        <input type="hidden" name="stuid" value="<%= studentId %>"/>
                    </td>
                    <td>
                        <input type="radio"
                               <% if (!status) { %>
                               checked="checked"
                               <% } %>
                               name="status<%= studentId %>" value="absent"/>absent
                        <input type="radio"
                               <% if (status) { %>
                               checked="checked"
                               <% } %>
                               name="status<%= studentId %>" value="present"/>present
                    </td>
                    <td>
                        <input type="text" value="<%= description %>"
                               name="description<%= studentId %>"/>
                    </td>
                    <td><%= datetime %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
            <input type="hidden" value="<%= a.getClass().getMethod("getSession").invoke(a).getClass().getMethod("getId").invoke(a.getSession()) %>" name="sesid"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
