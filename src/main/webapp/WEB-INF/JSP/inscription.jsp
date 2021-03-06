<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.projetEnchere.erreur.LecteurMessage"%>
<%@page import="fr.eni.projetEnchere.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INSCRIPTION</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
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
                    <h3>Inscription</h3>
                </div>	            
            </div>
            <form action="<%=request.getContextPath()%>/Inscription" method="post">
            <%
            	Utilisateur user = (Utilisateur) request.getAttribute("user");
            %>
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
                <div class="col-md-3">
                    <label for="pseudo">Pseudo :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="pseudo" value="<%=listErreurs!=null?request.getParameter("pseudo"):""%>"/>
                </div>
                 <div class="col-md-3">
                    <label for="nom">Nom :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="nom" value="<%=listErreurs!=null?request.getParameter("nom"):""%>"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label for="prenom">Prénom :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="prenom" value="<%=listErreurs!=null?request.getParameter("prenom"):""%>"/>
                </div>
                 <div class="col-md-3">
                    <label for="Email">Email :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="mail" value="<%=listErreurs!=null?request.getParameter("mail"):""%>"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label for="telephone">Téléphone :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="telephone" value="<%=listErreurs!=null?request.getParameter("telephone"):""%>"/>
                </div>
                 <div class="col-md-3">
                    <label for="rue">Rue :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="rue" value="<%=listErreurs!=null?request.getParameter("rue"):""%>"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label for="codePostal">Code postal :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="codePostal" value="<%=listErreurs!=null?request.getParameter("codePostal"):""%>"/>
                </div>
                 <div class="col-md-3">
                    <label for="ville">Ville :</label>
                </div>
                <div class="col-md-3">
                    <input type="text" name="ville" value="<%=listErreurs!=null?request.getParameter("ville"):""%>"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label for="motdepasse">Mot de pass :</label>
                </div>
                <div class="col-md-3">
                    <input type="password" name="pass">
                </div>
                 <div class="col-md-3">
                    <label for="confirmation">Confirmation :</label>
                </div>
                <div class="col-md-3">
                    <input type="password" name="confirmation">
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-4">
                    <input type="submit" value="Valider" class="btn btn-success btn-lg"/>
                </div>
                <div class="col-md-4">
                    <a class="btn btn-danger btn-lg" href="Acceuil.jsp" role="button">Annuler</a>
                </div>
            </div>
            </form>
        </div> 
	</section>
</body>
</html>