<%-- 
    Document   : timetable
    Created on : Oct 28, 2023, 1:01:39 PM
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
        <form action="timetable" method="GET">
            From <input type="date" name="from" value="${requestScope.from}"/> <br/>
            To <input type="date" name="to" value="${requestScope.to}"/>
            <input type="hidden" value="${param.id}" name="id"/>
            <input type="submit" value="View"/>
        </form>
        <table border="">
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
                                <c:if test="${ses.time.id eq s.id and ses.date eq d}">
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
