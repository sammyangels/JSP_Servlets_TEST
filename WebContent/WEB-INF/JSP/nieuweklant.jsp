<%--@elvariable id="pagina" type="be.vdab.servlets.NieuweKlantServlet"--%>

<%-- Written by Samuel Engelen | Date: 21/05/2015 --%>

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
<form method="post" id="nieuweklantform">
    <label>Voornaam:<input name="voornaam" value="${param.voornaam}" required></label>
    <label>Familienaam:<input name="familienaam" value="${param.familienaam}" required></label>
    <label>Straat:<input name="straat" value="${param.straat}" required></label>
    <label>Huisnr:<input name="huisnr" value="${param.huisnr}" required></label>
    <label>Postcode:<input name="postcode" value="${param.postcode}" required></label>
    <label>Gemeente:<input name="gemeente" value="${param.gebruikersnaam}" required></label>
    <label>Gebruikersnaam:<input name="gebruikersnaam" value="${param.gebruikersnaam}" required></label>
    <label>Paswoord:<input name="paswoord" type="password" value="${param.paswoord}" required></label>
    <label>Herhaal paswoord:<input name="controle" type="password" value="${param.controle}" required></label>
    <input type="submit" value="OK">
</form>
</body>
</html>