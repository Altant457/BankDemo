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
            ${account.value.name} : ${account.value.saldo} kr.<br />
        </c:forEach>

        <h1>${requestScope.msg}</h1>

        <form action="LoginServlet">
            <label for="name">Indtast navn: </label>
            <input type="text" id="name" name="name" placeholder="John" required /><br />
            <label for="pass">Indtast password: </label>
            <input type="password" id="pass" name="pass" placeholder="******" required /><br />
            <input type="submit" value="Login" />
        </form><br /><br />

        <h1>Opret ny konto</h1>
        <form action="LoginServlet" method="post">
            <label for="newname">Skriv nyt navn: </label>
            <input type="text" id="newname" name="newname" required /><br />
            <label for="newpass">Skriv ny kode: </label>
            <input type="password" id="newpass" name="newpass" required /><br />
            <label for="passconfirm">Skriv koden igen: </label>
            <input type="password" id="passconfirm" name="passconfirm" required /><br />
            <label for="konto">Konto</label>
            <input type="radio" id="konto" name="type" value="Konto" /><br />
            <label for="user">Bruger</label>
            <input type="radio" id="user" name="type" value="User (WIP virker ikke)" /><br />
            <input type="submit" value="Opret konto" />
        </form>
    </body>
</html>
