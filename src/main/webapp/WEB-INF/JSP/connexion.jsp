<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>
	<h1>Connexion</h1>
	<%
		if(request.getAttribute("erreurPseudo") != null){
	%>
		<h2 style="color:red">Le pseudo n'existe pas !</h2>
		<br>
	<%
		}
	%>
	<%
		if(request.getAttribute("erreurMDP") != null){
	%>
		<h2 style="color:red">Le mot de passe est incorrect !</h2>
		<br>
	<%
		}
	%>
	
	<form action="<%=request.getContextPath()%>/Connexion" method="post">
	<label for="pseudo">Pseudo :</label>
	<input type="text" name="pseudo">
	<br>
	<label for="mdp">Mot de passe :</label>
	<input type="text" name="mdp">
	
	<input type="submit" value="Valider"/>
	</form>
</body>
</html>