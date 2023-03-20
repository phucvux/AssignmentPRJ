<%-- 
    Document   : timetable
    Created on : Mar 1, 2023, 10:48:02 PM
    Author     : CucLe
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        
        <a href="home">HOME</a><br>
        <a href="attendance">ATTENDANCE</a><br>
        <h1>Weekly Timetable</h1>
        <table border="1px"> 
            <tr>
                <td>
                    <form method="post" action="timetable">
                        <input type="hidden" name="sid" value="${sid}">
                        <label for="date">Choose a date:</label>
                        <input type="date" id="date" name="date" placeholder="yyyy-mm-dd" required>
                        <br>
                        <button type="submit">Show week</button>
                    </form>
                    <c:forEach items="${weekDays}" var="w">
                       <td>${w}<br/><fmt:formatDate value="${w}" pattern="EEEE"/>
                    </td>
                    </c:forEach>
                </td>
              
                    
            </tr>
            <c:forEach items="${requestScope.slots}" var="slot"> 
                <tr>
                    <td>${slot.slot_name}</td>
                    <c:forEach items="${weekDays}" var="w">
                        <td>
                            <c:forEach items="${requestScope.lessons}" var="l">
                                <c:forEach items="${l.status}" var="s">
                                    <c:if test="${s.date eq w and l.timeslot.tid eq slot.tid}">
                                        ${l.course.cname}<br/>
                                        ${l.room.rname}<br/>    
                                        <c:if test="${s.status}">
                                            present
                                        </c:if>
                                        <c:if test="${s.status == '0'}">
                                            absent
                                        </c:if>
                                    </c:if>

                                </c:forEach>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
    </body>
</body>
</html>
