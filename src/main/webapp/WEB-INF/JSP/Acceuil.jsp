<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Acceuil</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">

<!-- CSS only -->

</head>
<body>
	<!-- NAVBAR  -->
	<%
		if(session.getAttribute("status") != null){
			String statut = (String) session.getAttribute("status");
			int userId = (int) session.getAttribute("id");
	%>
			<p> Status : <%= statut%> </p><br>
			<p> id : <%= userId %></p>
			
	<%
		}
	%>
	
	</
	<h1 style="color:green">Vous ?tes connect? !</h1>
	<a href="<%=request.getContextPath()%>/ModifierProfil">Modifier profil essai</a>
</body>
