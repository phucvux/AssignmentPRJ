<%-- 
    Document   : attendance
    Created on : Mar 4, 2023, 6:52:31 PM
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
                width: 50%; /* changed from 100% */
                margin: 0 auto; /* added to center table */
            }
            th, td {
                text-align: center;
                padding: 8px;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <table border = "1px">
            <tr>
                <td>No.</td>
                <td>Subject</td>
            </tr>
            
                <c:forEach items="${requestScope.courses}" var="c" varStatus="loop">                 
                <tr>
                    <td>${c.cid}</td>
                    <td><a href="">${c.cname}</a></td>
                </tr>
                </c:forEach>      
        </table>
    </body>
</html>
