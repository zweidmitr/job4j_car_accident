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
    <title>Регистрация статьи</title>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Статья</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="<c:url value="/"/>">Все инциденты</a>
                <a class="nav-item nav-link " href='<c:url value="/addType"/>'>Добавить тип</a>
            </div>
        </div>
    </nav>
    <div class="row pt-3">
        <div class="card" style="width: 100%">
            <div class="card-header">
                СтатьяТест
            </div>
            <div class="card-body">
                <form action="<c:url value="/saveRule"/>" method="post">
                    <table>
                        <tbody>
                        <colgroup>
                            <col>
                            <col>
                            <col width="75%">
                        </colgroup>
                        <tr>
                            <td><b>Название:</b></td>
                            <td><input type="text" class="form-control" name="name"></td>
                            <td align="right">
                                <button type="submit" class="btn btn-success mt-3">Сохранить</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <div class="row">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Статья</th>
                        </tr>
                        </thead>
                        <tbody>
                        <colgroup>
                            <col width="75%">
                            <col>
                        </colgroup>
                        <c:forEach var="rule" items="${rules}">
                            <tr>
                                <td><c:out value="${rule.name}"/></td>
                                <td>
                                    <form action="<c:url value="/deleteRule/${rule.id}"/>" method="get">
                                        <button type="submit" class="btn btn-danger mt-3">Удалить</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
