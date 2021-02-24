<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Some cool app</title>
    </head>
    <body>
    <p>afd</p>

    <%
        String anar = "anar!";
        request.setAttribute("anar", anar);
    %>

    <c:set scope="request" value="JSTL Core Tags Example" var="pageTitle"/>

    <c:out value="${anar}"/>

    <c:out value="${pageTitle}"/>


    <c:if test="${sessionScope.is_authorize != null}">
            <div class="center">
                <h1>Auth Page</h1>
                <p>Please login to proceed...</p>
                <form action="/helloworld/auth" method="POST">
                    <div>
                        <label for="fusername">Username:</label>
                        <input id="fusername" type="text" name="username" placeholder="Username..."/>
                    </div>
                    <div>
                        <label for="fpassword">Password:</label>
                        <input id="fpassword" type="password" name="password" placeholder="Password..."/>
                    </div>
                    <div style="clear: both"></div>
                    <input type="submit" name="dosubmit" value="Submit"/>
                </form>
            </div>
        </c:if>
    </body>
</html>