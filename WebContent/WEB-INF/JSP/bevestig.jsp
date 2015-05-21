<%--@elvariable id="pagina" type="be.vdab.servlets.bevestigenservlet"--%>

<%-- Written by Samuel Engelen | Date: 21/05/2015 --%>

<%@page contentType='text/html' pageEncoding='UTF-8' session='false' %>
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
    <h2>Stap 1: wie ben je?</h2>
    <form method="post" id="klantzoekenform" enctype="multipart/form-data">
        <label>Gebruikersnaam:
            <input name="naam" value="${param.naam}" autofocus required>
        </label>
        <label>Paswoord:
            <input name="paswoord" value="${param.paswoord}" required>
        </label>
        <input type="submit" value="Zoek me op" id="klantzoekenknop">
    </form>
    <form method="post" id="klanttoevoegenform">
        <input type="submit" value="Ik ben nieuw" id="klanttoevoegenknop">
    </form>
    <h2>Stap 2: Bevestigen</h2>
    <form method="post" id="bevestigenform">
        <input type="submit" value="Bevestigen" id="bevestigenknop" disabled>
    </form>
    <script>
        document.getElementById("klantzoekenform").onsubmit = function() {
            document.getElementById("klantzoekenform").disabled = true;
            document.getElementById("klanttoevoegenform").disabled = true;
            document.getElementById("bevestigenform").disabled = false;
        };
    </script>
</body>
</html>