<%--@elvariable id="genre" type="be.vdab.servlets.indexservlet"--%>
<%--@elvariable id="genrenaam" type="be.vdab.servlets.indexservlet"--%>
<%--@elvariable id="voorstellingen" type="be.vdab.servlets.IndexServlet"--%>

<%-- Written by Samuel Engelen | Date: 13/05/2015 --%>

<%@page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="Het Cultuurhuis"/>
</head>
<body>
    <vdab:menu pagina="voorstellingen"/>
    <c:if test="${not empty genre}">
        <h2>${genre} voorstellingen</h2>
            <table class="zebra">
                <tr>
                    <th>Datum</th>
                    <th>Titel</th>
                    <th>Uitvoerders</th>
                    <th>Prijs</th>
                    <th>Vrije plaatsen</th>
                    <th>Reserveren</th>
                </tr>
                <c:forEach var="voorstelling" items="${voorstellingen}">
                    <tr>
                        <td>${voorstelling.datum}</td>
                        <td>${voorstelling.titel}</td>
                        <td>${voorstelling.uitvoerders}</td>
                        <td>${voorstelling.prijs}</td>
                        <td>${voorstelling.vrijeplaatsen}</td>
                        <td><c:if test="${voorstelling.vrijeplaatsen > 0}">
                            Reserveren
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </c:if>
</body>
</html>