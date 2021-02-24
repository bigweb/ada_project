<%--
  Created by IntelliJ IDEA.
  User: anario
  Date: 2021-02-14
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth Page | SuccessFactors</title>
    <style>
        .center {
            width: 960px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
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
</body>
</html>
