<%--@elvariable id="genres" type="be.vdab.servlets.indexservlet"--%>

<%-- Written by Samuel Engelen | Date: 13/05/2015 --%>

<%@page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!doctype html>
<html lang="nl">
<head>
    <vdab:head title="Het Cultuurhuis:voorstellingen"/>
</head>
<body>
    <h1>Het Cultuurhuis:voorstellingen<img src="<c:url value="/images/voorstellingen.png"/>" alt="voorstellingen" class="floating"/></h1>
        <h2>Genres</h2>
            <nav>
                <c:forEach var="genre" items="${genres}">
                    <a href="<c:url value="/index.htm?genre=${genre.id}"/>">${genre.naam}</a>
                </c:forEach>
            </nav>
</body>
</html>