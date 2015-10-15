<%--@elvariable id="genre" type="be.vdab.servlets.indexservlet"--%>
<%--@elvariable id="genrenaam" type="be.vdab.servlets.indexservlet"--%>
<%--@elvariable id="voorstellingen" type="be.vdab.servlets.IndexServlet"--%>
<%--@elvariable id="pagina" type="be.vdab.servlets.indexservlet"--%>

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
                        <td><fmt:formatDate value="${voorstelling.datum}" type="BOTH" dateStyle="short" timeStyle="short"/></td>
                        <td>${voorstelling.titel}</td>
                        <td>${voorstelling.uitvoerders}</td>
                        <td><fmt:formatNumber value="${voorstelling.prijs}" type="currency"/></td>
                        <td style="text-align:right">${voorstelling.vrijeplaatsen}</td>
                        <td><c:if test="${voorstelling.vrijeplaatsen > 0}">
                            <c:url value='/reserveren.htm' var='reserverenURL'>
                                <c:param name='voorstellingsid'
                                         value='${voorstelling.id}' />
                            </c:url>
                            <a href='${reserverenURL}'>Reserveren</a>
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </c:if>
</body>
</html>