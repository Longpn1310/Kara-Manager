<%@page import="Models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/login.js" type="text/javascript"></script>
        <%
            Account account = (Account) request.getAttribute("account");
        %>
    </head>
    <body style="background-color: gainsboro;">
        <form action="login" method="POST" style="text-align: center; font-size: 25px">
            Username: <input type="text" name="username" placeholder="Enter name"/> <br/>
            Password: <input type="password" name="password" placeholder="Enter password"/> <br/>
            <a href="url">Forget Password?</a><br/><!-- comment -->
            <input type="submit" value="Login" style="height: 40px; width: 150px; margin-top: 10px;"/>
            <h3 id="Error" style="color: red; ">Please enter account and password</h3>
        </form>
        <%if (account == null) {%>
        <script> accountalert(); </script>
        <%}%>
    </body>
</html>
