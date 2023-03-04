<%-- 
    Document   : timetable
    Created on : Mar 1, 2023, 10:48:02 PM
    Author     : CucLe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <table border="1px"> 
            <tr>
                <td>Week</td>
                <td>MON</td>
                <td>TUE</td>
                <td>WED</td>
                <td>THU</td>
                <td>FRI</td>
                <td>SAT</td>
                <td>SUN</td>

            </tr>

            <tr>
                <c:forEach items="${requestScope.lessons}" var="l" varStatus="loop">
                    <td>${l.course.cname}</td>
                </c:forEach>
            </tr>

        </table>
    </body>
</html>
