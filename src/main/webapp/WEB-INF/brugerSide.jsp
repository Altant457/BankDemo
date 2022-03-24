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
      <label for="haevebeloeb">Angiv beløb: </label>
      <input type="number" id="haevebeloeb" name="haevebeloeb" placeholder="0" /><br />
      <input type="submit" value="Hæv beløb" />
    </form>

    <h3>Indsæt penge</h3>
    <form action="TransaktionServlet" method="post">
      <label for="indsaetbeloeb">Angiv beløb: </label>
      <input type="number" id="indsaetbeloeb" name="indsaetbeloeb" placeholder="0" /><br />
      <input type="submit" value="Indsæt beløb" />
    </form>

  </body>
</html>
