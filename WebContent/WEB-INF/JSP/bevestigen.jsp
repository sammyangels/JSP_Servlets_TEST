<%--@elvariable id="pagina" type="be.vdab.servlets.bevestigenservlet"--%>
<%--@elvariable id="resultaat" type="be.vdab.servlets.bevestigenservlet"--%>
<%--@elvariable id="gebruiker" type="be.vdab.servlets.bevestigenservlet"--%>

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
    <c:set var="nietingelogd" value="disabled"/>
    <c:if test="${gebruiker != null}">
        <c:set var="ingelogd" value="disabled"/>
        <c:set var="nietingelogd" value=""/>
    </c:if>
    <h2>Stap 1: wie ben je?</h2>
    <form method="post" id="klantzoekenform">
        <label>Gebruikersnaam:
            <input name="naam" value="${gebruiker}" autofocus required ${ingelogd}>
        </label>
        <label>Paswoord:
            <input type="password" name="paswoord" required ${ingelogd}>
        </label>
        <input type="submit" value="Zoek me op" name="klantzoeken" ${ingelogd}>
    </form>
    <form method="post" id="klanttoevoegenform">
        <input type="submit" value="Ik ben nieuw" name="klanttoevoegen" ${ingelogd}>
    </form>
        <p>${resultaat}</p>
    <h2>Stap 2: Bevestigen</h2>
    <form method="post" id="bevestigenform">
        <input type="submit" value="Bevestigen" name="bevestigen" ${nietingelogd}>
    </form>
    <script>
        document.getElementById('bevestigenform').onsubmit = function() {
            document.getElementById('bevestigen').disabled=true;
        };
    </script>
</body>
</html>