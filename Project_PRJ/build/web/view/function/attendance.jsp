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
                width: 50%;
                margin: 0 auto;
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
       
        <div>
            <ul style="list-style-type: none">
                <li>
                    <a href="home">HOME</a>
                </li>
                <li>
                    <a href="timetable">WEEKLY TIMETABLE</a>
                </li>
            </ul>
        </div>
         <h1>Attendance Report</h1>
        <form method="post" action="attendance">
            <input type="hidden" name="sid" value="${sid}">
            <label for="courseSelect">Select a course:</label>
            <select id="courseSelect" name="courseSelect">
                <option value="">--Select a course--</option>
                <c:forEach items="${requestScope.courses}" var="c">
                    <option value="${c.cid}">${c.cname}</option>
                </c:forEach>
            </select>
            <br/>
            <input type="submit" value="Submit">
        </form>
        <c:if test="${not empty param.courseSelect}">
            <table border="1px">
                <tr>
                    <th>No.</th>
                    <th>Date</th>
                    <th>Slot</th>
                    <th>Status</th>               
                </tr>
                <c:set var="index" value="0"/>
                <c:forEach items="${requestScope.lessons}" var="l">
                    <c:if test="${l.course.cid eq param.courseSelect}">
                        <tr>
                            <c:set var="index" value="${index+1}"/>
                            <td>${index}</td>  
                            <c:forEach items="${l.status}" var="s">
                                <td>${s.date}</td>
                            </c:forEach>
                            <td>${l.timeslot.slot_name}</td>
                            <c:forEach items="${l.status}" var="s">
                                <c:if test="${s.status}">
                                    <td style="color: green">present</td>
                                </c:if>  
                                <c:if test="${s.status == '0'}">
                                    <td style="color: red">absent</td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </c:if>

    </body>
</html>
