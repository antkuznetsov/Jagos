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
    <a class="navbar-brand" href="/dashboard/">Jagos</a>

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
            <button onclick="window.location = '/auth/?action=logout'" class="btn btn-outline-success my-2 my-sm-0" type="submit">Выход</button>
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
                    <a class="nav-link active" href="/courses/">Курсы <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Комментарии</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/users/">Пользователи</a>
                </li>
            </ul>
        </nav>

        <main class="col-sm-9 offset-sm-3 col-md-10 offset-md-2 pt-3">
            <h1><%=request.getAttribute("title")%></h1>
            <form method="post">
                <div class="form-group">
                    <label for="title">Название</label>
                    <input name="title" type="text" class="form-control" id="title" placeholder="Введите название курса" value="<c:out value="${course.title}" />">
                </div>
                <div class="form-group">
                    <label for="description">Описание</label>
                    <input name="description" type="text" class="form-control" id="description" placeholder="Введите описание курса" value="<c:out value="${course.description}" />">
                </div>
                <div class="form-group">
                    <label for="authorId">Автор</label>
                    <select name="authorId" class="form-control" id="authorId">
                        <option disabled selected>Выберите автора</option>
                        <c:forEach items="${requestScope.authors}" var="author">
                            <option value="<c:out value="${author.id}"/>"<c:if test="${course.authorId == author.id}"> selected</c:if>>
                                <c:out value="${author.name}"/> <c:out value="${author.lastName}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <input name="courseId" type="hidden" value="<c:out value="${course.id}" />">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </form>
            <br>
            <h2>Список уроков</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Название</th>
                        <th>Управление</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.list}" var="item">
                        <tr>
                            <td><c:out value="${item.id}"/></td>
                            <td><c:out value="${item.title}"/></td>
                            <td>
                                <a class="edit" href="/lessons/?action=edit&id=<c:out value="${item.id}"/>">Редактировать</a>
                                <a class="delete" href="/lessons/?action=delete&id=<c:out value="${item.id}"/>&course=<c:out value="${course.id}"/>">Удалить</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="/lessons/?action=new&course=<c:out value="${course.id}" />" class="add btn btn-primary">Добавить</a><br>
            </div>
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