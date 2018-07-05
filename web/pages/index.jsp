<%-- 
    Document   : index
    Created on : 04.05.2018, 19:26:39
    Author     : Вадим
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Онлайн Библиотека :: Вход</title>
        <link href="css/style_index.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="main">
            <div class="content">
                <p class="title"><span class="text"><img src="images/bookshelf.png"wight="76" height="77" hspace="10" vspace="10" align="midle"></span></p>
                <p class="title">Онлайн библиотека</p>
                <p class="text">Добро пожаловать в онлайн библиотеку, где вы сможите найти любую книгу на ваш вкус.
                                Доcтупны функции поиска, просмора, сортировки и многое другое.</p>
                <p class="text">Проект находится в разработке, поэтому дизайн и функционал будут постоянно меняться.</p>
                <p class="text">По всем вопросм обращайтесь по адресу <a href="mailto:dvvv09@gmail.com">dvvv09@gmail.com</a> </p>
            </div>
            
            <div class="login_div">
                <p class="title">Для входа введите свои данные</p>
                <form class="login_form" name="username" action="pages/main.jsp" method="POST">
                Имя: <input type="text" name="username" value="" size="20" />
                <input type="submit" value="Войти" />
                </form>
            </div>
            
            <div class="footer">
                Разработчик: Вадим Валунский, 2018г
            </div>
        </div>>
    </body>
</html>
