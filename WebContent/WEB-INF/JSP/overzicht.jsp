<%--@elvariable id="pagina" type="be.vdab.servlets.overzichtservlet"--%>
<%--@elvariable id="gelukt" type="be.vdab.servlets.overzichtservlet"--%>
<%--@elvariable id="mislukt" type="be.vdab.servlets.overzichtservlet"--%>

<%@page contentType='text/html' pageEncoding='UTF-8' %>
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
    <h2>Gelukte reserveringen</h2>
    <table class="zebra">
        <tr>
            <th>Datum</th>
            <th>Titel</th>
            <th>Uitvoerders</th>
            <th>Prijs</th>
            <th>Plaatsen</th>
        </tr>
        <tr>
            <c:forEach var="reservatie" items="${gelukt}">
                <td><fmt:formatDate value="${reservatie.voorstelling.datum}" type="BOTH" dateStyle="short" timeStyle="short"/></td>
                <td>${reservatie.voorstelling.titel}</td>
                <td>${reservatie.voorstelling.uitvoerders}</td>
                <td><fmt:formatNumber value="${reservatie.voorstelling.prijs}" type="currency"/></td>
                <td>${reservatie.plaatsen}</td>
            </c:forEach>
        </tr>
    </table>
    <h2>Mislukte reserveringen</h2>
    <table class="zebra">
        <tr>
            <th>Datum</th>
            <th>Titel</th>
            <th>Uitvoerders</th>
            <th>Prijs</th>
            <th>Plaatsen</th>
            <th>Vrije plaatsen</th>
        </tr>
        <tr>
            <c:forEach var="reservatie" items="${mislukt}">
                <td><fmt:formatDate value="${reservatie.voorstelling.datum}" type="BOTH" dateStyle="short" timeStyle="short"/></td>
                <td>${reservatie.voorstelling.titel}</td>
                <td>${reservatie.voorstelling.uitvoerders}</td>
                <td><fmt:formatNumber value="${reservatie.voorstelling.prijs}" type="currency"/></td>
                <td>${reservatie.plaatsen}</td>
                <td>${reservatie.voorstelling.vrijeplaatsen}</td>
            </c:forEach>
        </tr>
    </table>
</body>
</html>