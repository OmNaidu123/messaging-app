<%@page language="java" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="/LoginSignup.css">
    </head>
    <body>
        <div class="center-div">
            <div class="login-title-div">
                <p class="login-div">LOGIN</p>
            </div>
            <form action="verifyLogin" class="login-form" method="post">
                <label for="user">Enter user name</label>
                <input type = "text" id ="user" class="user-info" name = "username">
                <label for ="password">Enter password</label>
                <input type = "password" id = "password" class="user-info" name = "password">
                <input type = "submit" value = "submit" class = "sub-button">
            </form>
        </div>
    </body>
</html>