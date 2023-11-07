
<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 0 auto;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        input[type="text"] {
            width: 100%;
        }
        .save-button {
            display: block;
            width: 200px;
            margin: 0 auto;
            padding: 10px;
            text-align: center;
            background-color: #0074d9;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>
    </head>
    
    <body>
       <h1 class="title">${requestScope.ses.group.name} - ${requestScope.ses.subject.name} - ${requestScope.ses.room.rid} 
           - ${requestScope.ses.time.description} - ${requestScope.ses.semester.semester_name}</h1>
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
                       <input type="hidden" name="student_id" value="${a.student.id}"/>
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
            <input type="hidden" value="${requestScope.ses.id}" name="session_id"/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Attendance Page</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 0 auto;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        input[type="text"] {
            width: 100%;
        }

        .save-button {
            display: block;
            width: 200px;
            margin: 0 auto;
            padding: 10px;
            text-align: center;
            background-color: #0074d9;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>${requestScope.ses.group.name} - ${requestScope.ses.subject.name} - ${requestScope.ses.room.rid} - ${requestScope.ses.time.description} - ${requestScope.ses.semester.semester_name}</h1>
    <form action="attendance" method="POST">
        <table>
            <tr>
                <th>Code</th>
                <th>Student</th>
                <th>Status</th>
                <th>Description</th>
                <th>Time</th>
            </tr>
            <c:forEach items="${requestScope.atts}" var="a">
                <tr>
                    <td>${a.student.id}
                        <input type="hidden" name="student_id" value="${a.student.id}" />
                    </td>
                    <td>${a.student.name}
                        <input type="hidden" name="student_id" value="${a.student.id}" />
                    </td>
                    <td>
                        <input type="radio"
                            <c:if test="${!a.status}">
                                checked="checked"
                            </c:if>
                            name="status${a.student.id}" value="absent" /> Absent
                        <input type="radio"
                            <c:if test="${a.status}">
                                checked="checked"
                            </c:if>
                            name="status${a.student.id}" value="present" /> Present
                    </td>
                    <td>
                        <input type="text" value="${a.description}" name="description${a.student.id}" />
                    </td>
                    <td>${a.datetime}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="hidden" value="${requestScope.ses.id}" name="session_id"/>
        <button class="save-button" type="submit">Save</button>
    </form>
    <form action="login"> 
        <button class="back-button" type="submit">Back to Login</button>
    </form>
</body>
</html>
