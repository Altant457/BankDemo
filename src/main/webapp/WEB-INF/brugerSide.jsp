<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Din side</title>
  </head>
  <body>
    <h1>Velkommen, du er nu logget ind på din konto ${sessionScope.konto.name}</h1>
    <h3>Din saldo er ${sessionScope.konto.saldo}</h3>
  </body>
</html>
