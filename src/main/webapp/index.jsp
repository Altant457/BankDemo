<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
    </head>
    <body>
        <h1><%= "Hello World!" %></h1><br />
        <a href="hello-servlet">Hent konto liste</a>
        <h1>Liste af konti</h1>
        <c:forEach items="${applicationScope.accounts}" var="account">
            ${account.value.name} : ${account.value.saldo} kr. <br />
        </c:forEach>
    </body>
</html>