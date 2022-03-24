<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Din side</title>
  </head>
  <body>
    <h1>Velkommen, du er nu logget ind på din konto ${sessionScope.konto.name}</h1>
    <h2>Din saldo er ${sessionScope.konto.saldo}</h2>

    <h1>${requestScope.errmsg}</h1>

    <h3>Hæv penge</h3>
    <form action="TransaktionServlet">
      <label for="beloeb">Angiv beløb: </label>
      <input type="number" id="beloeb" name="beloeb" placeholder="0" /><br />
      <input type="submit" value="Hæv beløb" />
    </form>

  </body>
</html>
