<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./index.css"/>
    <title>WebLogin_ex3</title></head>
<body >
<div class="wrapper">
    <form action="login" method="post" class="form-signin">
        <h2 class="form-signin-heading">登录</h2>
        <input type="text" class="form-control" name="username" placeholder="username" required>
        <input type="password" class="form-control" name="password" placeholder="password" required>
        <div class = "vcode-container">
            <input type="text" class="vcode-input" name="verifycode" placeholder="请输入验证码" required>
            <div class = "vcode-pic">
                <img src="get_verifycode">
                <a href="#" id="vcode_refresh">换一张</a>
            </div>
        </div>
        <font color="red">${requestScope.error}</font>
        <button class="btn btn-lg btn-primary btn-block mybtn" type="submit">Login</button>
    </form>
</div>

<script type="text/javascript">
    document.getElementById("vcode_refresh").onclick = function () {
        document.getElementsByTagName("img")[0].src =
            "get_verifycode?time=" + new Date().getTime();
    };
</script>

</body>
</html>

