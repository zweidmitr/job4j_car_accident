<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Регистрация Автонарушения</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Автонарушения</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%--                <a class="nav-item nav-link active" href="<c:url value="/"/>">Все инциденты</a>--%>
                <%--                <a class="nav-item nav-link " href='<c:url value="/create"/>'>Добавить инцидент</a>--%>
            </div>
        </div>
    </nav>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Регистрация нового автонарушения
            </div>
            <div class="card-body">
                <form action="<c:url value="/save"/>" method="post">
                    <table>
                        <tbody>
                        <colgroup>
                            <col>
                            <col width="75%">
                        </colgroup>
                        <tr>
                            <td>Название автонарушения:</td>
                            <td><input type="text" class="form-control" name="name"></td>
                        </tr>
                        <tr>
                            <td>Тип:</td>
                            <td>
                                <select class="form-control" name="type.id">
                                    <c:forEach var="type" items="${types}">
                                        <option value="${type.id}">${type.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Описание автонарушения:</td>
                            <td><textarea class="form-control" name="text" rows="3"></textarea></td>
                        </tr>
                        <tr>
                            <td>Адрес нарушения:</td>
                            <td><textarea class="form-control" name="address" rows="2"></textarea></td>
                        </tr>
                        <tr>
                            <td>
                                <form action="<c:url value="/"/>" method="get">
                                    <button type="submit" class="btn btn-info mt-3">Вернуться назад</button>
                                </form>
                                <button type="submit" class="btn btn-primary mt-3">Сохранить</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>