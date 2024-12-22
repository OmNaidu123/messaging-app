<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <title>Chatting</title>
        <link rel="stylesheet" href="/chat.css">
    </head>
    <body>
            <div>
                <ul>
                    <c:forEach var="chatSeq" items="${userChat}">
                            <li class="${chatSeq.selfSource ? 'self-message' : 'other-message'}">
                                <p>${chatSeq.getMessage()}</p>
                            </li>
                    </c:forEach>
                </ul>
            </div>
            <div class = "input-center">
                <form action="sendMsg" method="post" class = "msg-input">
                    <input placeholder="Enter Message" name="msg" type="text" class="input-box">
                    <input type="hidden" name="sender" value="${sender.getUsername()}">
                    <input type="hidden" name="receiver" value="${receiver.getUsername()}">
                    <button type="submit" class="send-msg">
                        <img class="logo-icon" src="/sendmsg.png" alt="send">
                    </button>
                </form>
                <form action="chat" method="get" class = "reload">
                    <input type="hidden" name="Ousername" value="${receiver.getUsername()}"/>
                    <input type="hidden" name="Yusername" value="${sender.getUsername()}"/>
                    <button class="reload-button" type="submit">reload</button>
                </form>
            </div>
    </body>
</html>