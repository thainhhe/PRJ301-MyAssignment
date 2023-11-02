
<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time Table</title>
    </head>
    <body>
        <h1>Time Table</h1>
        <form action="timetable" method="GET">
            From <input type="date" name="from" value="${requestScope.from}"/> <br/>
            To <input type="date" name="to" value="${requestScope.to}"/>
            <input type="hidden" value="${param.id}" name="id"/>
            <input type="submit" value="View"/>
        </form>
        <table border="1px">
            <tr>
                <td></td>
                <c:forEach items="${requestScope.dates}" var="d">
                    <td>
                        <fmt:formatDate value="${d}" pattern="dd-MM-yyyy" var="formattedDate" />
                        <p>${formattedDate}</p>
                    </td>
                </c:forEach>
            </tr>
            <c:forEach items="${requestScope.slots}" var="s">
                <tr>
                    <td>${s.description}</td>
                    <c:forEach items="${requestScope.dates}" var="d">
                        <td>
                            <c:forEach items="${requestScope.sessions}" var="ses">
                                <c:if test="${ses.time.id == s.id and ses.date == d}">
                                    ${ses.group.name}-${ses.subject.name}-${ses.room.rid}
                                    
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>  
            </c:forEach>
        </table>    
    </body>
</html>

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="entity.Session" %>
<%@page import="entity.TimeSlot" %>
<%@page import="java.sql.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="util.DateTimeHelper" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time Table</title>
        <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
        }

        form {
            text-align: center;
            margin: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #007BFF;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
    </head>
    <body>
        <h1>Time Table</h1>
        <form action="timetable" method="GET">
            From <input type="date" name="from" value="<%= request.getAttribute("from") %>"/> <br/>
            To <input type="date" name="to" value="<%= request.getAttribute("to") %>"/>
            <input type="hidden" value="<%= session.getAttribute("instructor_id") %>" name="instructor_id"/>
            <input type="submit" value="View"/>
        </form>
        <table border="1px">
            <tr>
                <td></td>
                <%
                    
                    ArrayList<Date> dates = (ArrayList<Date>) request.getAttribute("dates");
                    if (dates != null) {
                    for (Date d : dates) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                        String formattedDate = sdf.format(d);
                %>
                <td>
                    <%= formattedDate %>
                </td>
                <%
                    }
                }
                %>

            </tr>
            <%
                ArrayList<TimeSlot> slots = (ArrayList<TimeSlot>) request.getAttribute("slots");
                ArrayList<Session> sessions = (ArrayList<Session>) request.getAttribute("sessions");
                if (slots != null) {
                for (TimeSlot s : slots) {
            %>
            <tr>
                <td><%= s.getDescription() %></td>
                <%
                    for (Date d : dates) {
                        for (Session ses : sessions) {
                            if (ses.getTime().getId() == s.getId() && ses.getDate().equals(d)) {
                %>
                <td><%= ses.getGroup().getName() + "-" + ses.getSubject().getName() + "-" + ses.getRoom().getRid() %></td>
                <%
                            }
                        }
                    }
                %>
            </tr>
            <%
                }
            }
            %>
        </table>
    </body>
</html>
