<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Acceuil</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
 integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
	<!-- NAVBAR  -->
	
	<%
		if(session.getAttribute("status") != null){
			String status = (String)session.getAttribute("status");
			if(status.equals("login")){
	%>
	<h1 style="color:green">Vous �tes connect� !</h1>
	<a href="<%=request.getContextPath()%>/ModifierProfil">Modifier profil essai</a>
	<a href="<%=request.getContextPath()%>/Deconnexion">Se d�connecter</a>
	<%
			}
			else{
	%>
	<p> Vous n'�tes pas connecter !</p>
	<%
			}
		}
	%>
</body>
</html>