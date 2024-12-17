<%@ page import="ru.itis.vhsroni.dto.ChatDto" %>
<%@ page import="ru.itis.vhsroni.dto.MessageDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.itis.vhsroni.dto.UserDataResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Your chats</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/styles/main.css"/>
    <link rel="stylesheet" href="/styles/main_layout.css"/>
    <link rel="stylesheet" href="/styles/chat.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lilita+One&display=swap" rel="stylesheet">
</head>

<body>

<header>
    <div class="title">Odnogrupniki
        <div class="little-title">v-ITIS-e</div>
    </div>
    <div class="logo-container">
        <img src="/images/logo_1.png" alt="logo-1"/>
        <img src="/images/logo_2.png" alt="logo-2"/>
        <img src="/images/logo_3.png" alt="logo-3"/>
    </div>
</header>

<main>
    <div class="main-page">
        <div class="left-layout"></div>
        <div class="navigation-layout">
            <div>
                <div>
                    <img id="avatar"
                         src="/avatar?file=<%=((UserDataResponse)request.getAttribute("user")).getAvatarId()%>"/>
                </div>
                <div id="nickname">
                    <%=((UserDataResponse) request.getAttribute("user")).getNickname()%>
                </div>
                <div>
                    <form id="avatarForm" action="/avatar" method="post" enctype="multipart/form-data">
                        <input type="file" id="file" name="file" accept="image/jpeg"/>
                        <input type="submit" value="Изменить аватар"/>
                    </form>
                </div>
            </div>
            <div class="nav-item">
                <img src="/images/nav_item.png" alt="nav-item-img"/>
                <div><a href="/">Greeting</a></div>
            </div>
            <div class="nav-item">
                <img src="/images/nav_item.png" alt="nav-item-img"/>
                <div>Profile</div>
            </div>
            <div class="nav-item">
                <img src="/images/nav_item.png" alt="nav-item-img"/>
                <div><a href="/main">Main Page</a></div>
            </div>
            <div class="nav-item">
                <img src="/images/nav_item.png" alt="nav-item-img"/>
                <div>Friends</div>
            </div>
            <div class="nav-item">
                <img src="/images/nav_item.png" alt="nav-item-img"/>
                <div><a href="/chats">Messenger</a></div>
            </div>
            <div class="nav-item">
                <img src="/images/nav_item.png" alt="nav-item-img"/>
                <div><a href="/logout">Log out</a></div>
            </div>
        </div>
        <div class="chat-layout">
            <div class="chat-header"><%=((ChatDto) request.getAttribute("chat")).getTitle()%></div>

            <div id="messages">
                <% for (MessageDto message : (List<MessageDto>) request.getAttribute("messages")) { %>
                <div class="message-item">
                    <img src="/avatar?file=<%=message.getAuthorAvatarId()%>"/>
                    <div class="author"><%=message.getAuthorNickname()%></div>
                    <div class="message-text"> <%=message.getText()%></div>
                </div>
                <% } %>
            </div>
            <hr>
            <form id="new-message-form" method="post" >
                <label for="message">
                    Новое сообщение:
                    <input id="message" type="text" name="message"/>
                </label>
                <input id="message-submit-button" type="button" value="Отправить"/>
            </form>

        </div>
        <div class="advertisement-layout">
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
            <div class="advertisement-item">
                <img src="/images/advertisement_img.png" alt="advertisement image"/>
                <div class="advertisement-text">Advertisement text</div>
            </div>
        </div>
    </div>
</main>
<script>
    console.log("Script started");
    const url1 = new URL(window.location.href);
    const websocketUrl = 'ws://' + url1.host + '/websocket';
    var ws = new WebSocket(websocketUrl);
    ws.onopen = function () {
        console.log("Connected to ws")
    }
    ws.onerror = function () {
        console.log("Error with ws")
    }
    ws.onmessage = function processMessage(message) {
        console.log("Got message " + JSON.stringify(message.data));
        var json = JSON.parse(message.data);
        if(json.chatId === <%=((ChatDto) request.getAttribute("chat")).getId()%>) {
            var messageBlock = document.getElementById('messages');
            messageBlock.innerHTML += '<img src="/avatar?file=' + json.authorAvatarId + '" width="50" height="50"/>' + json.userName + ': ' + json.message + '<br/>';
        }
    }
    document.getElementById('message-submit-button').onclick = function() {
        var textFieldValue = document.getElementById('message').value;
        document.getElementById('message').value = ""
        myFunction(textFieldValue);
    };

    function myFunction(value) {
        console.log("Нажали на кнопку и передали " + value);
        var sendJs = {
            "chatId": <%=((ChatDto) request.getAttribute("chat")).getId()%>,
            "message": value,
            "userName": "<%=((UserDataResponse)request.getAttribute("user")).getNickname()%>",
            "authorAvatarId": "<%=((UserDataResponse)request.getAttribute("user")).getAvatarId()%>"
        };
        var sendJsString = JSON.stringify(sendJs);
        console.log("Отправляю " + sendJsString);
        ws.send(sendJsString);
    }
</script>
</body>
</html>
