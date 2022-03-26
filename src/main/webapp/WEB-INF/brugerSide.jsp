<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Din side</title>
  </head>
  <body>
    <h1>Velkommen, du er nu logget ind på din konto ${sessionScope.konto.name}</h1>
    <h2>Din saldo: ${sessionScope.konto.saldo} kr.</h2>

    <a href="LogOutServlet">Log af</a>

    <h1>${requestScope.errmsg}</h1>

    <div style="position: absolute;top: 0; right: 0;">
      <form action="LogOutServlet" method="post">
        <label for="confirmDelete">Bekræft kodeord: </label>
        <input type="password" id="confirmDelete" name="confirmDelete" placeholder="********" required /><br />
        <input type="submit" value="Slet konto" />
      </form>
    </div>

    <div style="float: left; width: 20%;">
      <h3>Hæv penge</h3>
      <form action="TransaktionServlet">
        <label for="haevebeloeb">Angiv beløb: </label>
        <input type="number" id="haevebeloeb" name="haevebeloeb" placeholder="0" required /><br/>
        <input type="submit" value="Hæv beløb"/>
      </form>
    </div>

    <div style="float: left; width: 25%">
      <h3>Indsæt penge</h3>
      <form action="TransaktionServlet" method="post">
        <label for="indsaetbeloeb">Angiv beløb: </label>
        <input type="number" id="indsaetbeloeb" name="indsaetbeloeb" placeholder="0" required /><br />
        <input type="submit" value="Indsæt beløb" />
      </form>
    </div>

    <div style="float: left; width: 35%">
      <h3>Overfør penge</h3>
      <form action="TransferServlet" method="post">
        <label for="tokonto">Angiv modtager: </label>
        <input type="text" id="tokonto" name="tokonto" placeholder="Navn" required /><br />
        <label for="transferbeloeb">Angiv beløb: </label>
        <input type="number" id="transferbeloeb" name="transferbeloeb" placeholder="0" required /><br />
        <label for="passconf">Bekæft kodeord: </label>
        <input type="password" id="passconf" name="passconf" placeholder="password" required /><br />
        <input type="submit" value="Overfør penge" />
      </form>
    </div>

    <h4>${requestScope.status}</h4>

    <div>
      <h3>Liste af transaktioner</h3>
      <c:forEach items="${sessionScope.konto.transactions}" var="transaction">
        ${transaction}<br />
      </c:forEach>
    </div>
  </body>
</html>
