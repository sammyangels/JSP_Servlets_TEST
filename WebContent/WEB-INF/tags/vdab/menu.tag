<%--@elvariable id="genres" type="be.vdab.servlets.IndexServlet"--%>

<%@tag description='menu' pageEncoding='UTF-8'%>
<%@attribute name='pagina' required='true' type='java.lang.String'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<h1>Cultuurhuis:${pagina} <img src="<c:url value="/images/${pagina}.png"/>" alt="pagina"></h1>
<h2>Genres</h2>
    <nav>
        <c:forEach var="genre" items="${genres}">
            <a href="<c:url value="/index.htm?genre=${genre.id}"/>">${genre.naam}</a>
        </c:forEach>
    </nav>