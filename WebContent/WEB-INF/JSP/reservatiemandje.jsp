<%--@elvariable id="reservaties" type="be.vdab.servlets.reservatiemandjeservlet"--%>
<%--@elvariable id="pagina" type="be.vdab.servlets.reservatiemandjeservlet"--%>
<%--@elvariable id="totaal" type="be.vdab.servlets.reservatiemandjeservlet"--%>

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
<form method="post" id="verwijderform">
<table class="zebra">
    <tr>
        <th>Datum</th>
        <th>Titel</th>
        <th>Uitvoerders</th>
        <th>Prijs</th>
        <th>Plaatsen</th>
        <th><input type="submit" value="Verwijderen" id="verwijderknop"></th>
    </tr>
    <c:forEach var="reservatie" items="${reservaties}">
        <tr>
            <td><fmt:formatDate value="${reservatie.voorstelling.datum}" type="BOTH" dateStyle="short" timeStyle="short"/></td>
            <td>${reservatie.voorstelling.titel}</td>
            <td>${reservatie.voorstelling.uitvoerders}</td>
            <td><fmt:formatNumber value="${reservatie.voorstelling.prijs}" type="currency"/></td>
            <td>${reservatie.plaatsen}</td>
            <td>
                <label>
                    <input type="checkbox" name="verwijder" value="${reservatie.voorstelling.id}">
                </label>
            </td>
        </tr>
    </c:forEach>
</table>
</form>
<p>
    Te betalen: <fmt:formatNumber value="${totaal}" type="currency"/>
</p>
<script>
    document.getElementById("verwijderform").onsubmit = function() {
        document.getElementById("verwijderknop").disabled = true;
    };
</script>
</body>
</html>