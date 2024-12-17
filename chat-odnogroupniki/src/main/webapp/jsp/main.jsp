<%@ page import="ru.itis.vhsroni.dto.UserDataResponse" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main Page</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/styles/main.css"/>
    <link rel="stylesheet" href="/styles/main_layout.css"/>
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
                    <img id="avatar" src="/avatar?file=<%=((UserDataResponse)request.getAttribute("user")).getAvatarId()%>"/>
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
        <div class="news-layout">
            <div class="news-item">
                <div class="news-title">News title</div>
                <img src="/images/news_img_1.png" alt="news image"/>
                <div class="news-text">Some text</div>
            </div>
            <div class="news-item">
                <div class="news-title">News title</div>
                <img src="/images/news_img_2.png" alt="news image"/>
                <div class="news-text">Some text</div>
            </div>
            <div class="news-item">
                <div class="news-title">News title</div>
                <img src="/images/news_img_3.png" alt="news image"/>
                <div class="news-text">Some text</div>
            </div>
            <div class="news-item">
                <div class="news-title">News title</div>
                <img src="/images/news_img_4.png" alt="news image"/>
                <div class="news-text">Some text</div>
            </div>
            <div class="news-item">
                <div class="news-title">News title</div>
                <img src="/images/news_img_4.png" alt="news image"/>
                <div class="news-text">Some text</div>
            </div>
            <div class="news-item">
                <div class="news-title">News title</div>
                <img src="/images/news_img_5.png" alt="news image"/>
                <div class="news-text">Some text</div>
            </div>
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

</body>
</html>
