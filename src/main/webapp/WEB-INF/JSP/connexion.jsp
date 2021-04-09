<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetEnchere.erreur.LecteurMessage"%>
<%@page import="fr.eni.projetEnchere.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Connexion</title>
</head>
<body>
	<section class="container">
	    <div class="container-fluid">
            <div class="row">
                <div class="col-4">
	                <h2>ENI-Enchères</h2>
	            </div>   
            </div>
	        <div class="row justify-content-center">
                <div class="col-2">
                    <h3>Connexion</h3>
                </div>	            
            </div>
            <form action="<%=request.getContextPath()%>/Connexion" method="post">
            <%
            	List<Integer> listErreurs = (List<Integer>)request.getAttribute("listeCodeErreur");
            	if(listErreurs != null){
            %>
            <div class="row justify-content-center">
            	<div class="col-md-12">
            		<%
            			for(int codeErreur:listErreurs){
            		%>
						<h2 class="color:red"><%=LecteurMessage.getMessageErreur(codeErreur) %></h2>
					<%
            			}
					%>
            	</div>
            </div>
            <%
            	}
            %>
           <div class="row">
                <div class="col-md-4 offset-md-2">
                    <label for="pseudo">Identifiant :</label>
                </div>
                <div class="col-md-4">
                    <input type="text" name="pseudo">
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 offset-md-2">
                    <label for="prenom">Mot de passe :</label>
                </div>
                <div class="col-md-4">
                    <input type="password" name="pass">
                </div>
            </div>
            <div class="row">
                <div class="col-md-4 offset-md-2">
                    <input type="submit" value="Connexion" class="btn btn-light btn-lg"/>
                </div>
            	<div class="col-md-4">
               		<input type="checkbox" name="resterConnecter"><label for="resterConnecter">Se souvenir de moi</label><br>
               	 	<a href="#">Mot de passe oublié</a>
               	 </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-2">
                    <a class="btn btn-light btn-lg" href="<%=request.getContextPath()%>/Inscription" role="button">Créer un compte</a>
                </div>
            </div>
            </form>
        </div> 
	</section>
</body>
</html>