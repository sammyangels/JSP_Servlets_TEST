<%--@elvariable id="plaatsen" type="be.vdab.servlets.reserverenservlet"--%>
<%--@elvariable id="pagina" type="be.vdab.servlets.reserverenservlet"--%>
<%--@elvariable id="foutmelding" type="be.vdab.servlets.reserverenservlet"--%>
<%--@elvariable id="voorstelling" type="be.vdab.entities.voorstelling"--%>

<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="nl_BE"/>
<!doctype html>
<html lang="nl">
<head>
  <vdab:head title="Het Cultuurhuis"/>
</head>
<body>
  <vdab:menu pagina="${pagina}"/>
    <dl>
      <dt>Voorstelling:</dt>
      <dd>${voorstelling.titel}</dd>
      <dt>Uitvoerders:</dt>
      <dd>${voorstelling.uitvoerders}</dd>
      <dt>Datum:</dt>
      <dd><fmt:formatDate value="${voorstelling.datum}" dateStyle="short" timeStyle="short"/></dd>
      <dt>Prijs:</dt>
      <dd><fmt:formatNumber value="${voorstelling.prijs}" type="currency"/></dd>
      <dt>Vrije plaatsen:</dt>
      <dd>${voorstelling.vrijeplaatsen}</dd>
    </dl>
    <form method="post" id="reserveerform">
      <label>Plaatsen:<span>${foutmelding}</span>
        <input name="plaatsen" value="${plaatsen}" autofocus required type="number" step="1"/>
      </label>
      <input type="submit" value="Reserveren" id="reserveerknop"/>
    </form>
    <script>
      document.getElementById("reserveerform").onsubmit = function() {
        document.getElementById("reserveerform").disabled = true;
      };
    </script>
</body>
</html>