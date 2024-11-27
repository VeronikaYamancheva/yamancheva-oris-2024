<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/styles/main.css"/>
    <link rel="stylesheet" href="/styles/error.css"/>
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
    <div class="horizontal-container">
        <img src="/images/error.png" alt="error"/>
        <div class="vertical-container">
            <div class="error-title">ERROR</div>
            <div class="error-desc">
                <%=request.getParameter("err")%>
            </div>
            <a href="/">Go to greeting page!</a>
        </div>
    </div>
</main>
</body>
</html>