<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">

    <title><%=request.getAttribute("title")%></title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/dashboard.css" rel="stylesheet">
</head>

<body>
<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">Jagos</a>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Settings</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Help</a>
            </li>
        </ul>
        <div class="form-inline mt-2 mt-md-0">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Выход</button>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-sm-3 col-md-2 hidden-xs-down bg-faded sidebar">
            <ul class="nav nav-pills flex-column">
                <li class="nav-item">
                    <a class="nav-link" href="/dashboard/">Обзор</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/courses/">Курсы</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Комментарии</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="/users/">Пользователи <span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </nav>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><%=request.getAttribute("title")%></h1>
            <form class="col-sm-5" method="post">
                <div class="form-group">
                    <label for="name">Ваше имя</label>
                    <input name="name" type="text" class="form-control" id="name" placeholder="Введите ваше имя" value="<c:out value="${user.name}" />" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Ваша фамилия</label>
                    <input name="lastName" type="text" class="form-control" id="lastName" placeholder="Введите вашу фамилию" value="<c:out value="${user.lastName}" />" required>
                </div>
                <div class="form-group">
                    <label for="email">Электронная почта</label>
                    <input name="email" type="email" class="form-control" id="email" placeholder="Введите ваш email" value="<c:out value="${user.email}" />" required>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input name="password" type="password" class="form-control" id="password" placeholder="Введите ваш пароль">
                </div>
                <div class="form-group">
                    <label for="group">Группа</label>
                    <select name="group" class="form-control" id="group">
                        <option value="0" disabled selected>Выберите группу</option>
                        <option value="1"<c:if test="${user.group == 1}"> selected</c:if>>Администратор</option>
                        <option value="2"<c:if test="${user.group == 2}"> selected</c:if>>Пользователь</option>
                    </select>
                </div>
                <div class="form-check">
                    <label class="form-check-label">
                        <input name="isBlocked" type="checkbox" class="form-check-input">
                        Заблокировать
                    </label>
                </div>
                <input name="userId" type="hidden" value="<c:out value="${user.id}" />">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
        </main>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
<script src="../js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>