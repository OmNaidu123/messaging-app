<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>
            Welcome
        </title>
        <link rel="stylesheet" href="/welcome.css">
    </head>
    <body>
        <div class="center-div">
            <div class="user-welcome">
                <p class="user-greeting">
                    WELCOME ${user.getDisplayname()}
                </p>
            </div>
            <ul class="other-users">
                <c:forEach var="otherUsers" items="${others.getUsers()}">
                    <li>
                        <form action="chat" method="get" class="other-user">
                            <p>${otherUsers.getDisplayname()}</p>
                            <input type="hidden" name="Ousername" value="${otherUsers.getUsername()}"/>
                            <input type="hidden" name="Yusername" value="${user.getUsername()}"/>
                            <button class="chat-button" type="submit">Chat</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </body>
</html>