<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign in</title>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="/styles/main.css">
    <link rel="stylesheet" href="/styles/sign.css">
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
    <div class="form-layout">
        <div class="form-header">
            Sign in
            <div class="form-desc">Hi, new user! Welcome to our resource!!!</div>
        </div>
        <form action="/signIn" method="post">
            <label for="email">Enter your email:</label>
            <input id="email" name="email" type="email" placeholder="fedor@main.ru" required/>
            <br/>
            <label for="password">Enter your password:</label>
            <input id="password" name="password" type="password" required/>
            <br/>
            <input id="submit-button" type="submit" value="Sign In"/>
        </form>
        <h3 class="sign-in-redirect">Have no account? <a href="/signUp">Sign up</a></h3>
    </div>

</main>
</body>
</html>