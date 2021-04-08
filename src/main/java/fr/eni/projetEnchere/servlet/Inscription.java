package fr.eni.projetEnchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.erreur.BusinessException;

/**
 * Servlet permettant l'inscription d'un nouvel user
 * GET : renvoie vers le formulaire d'inscription
 * POST : envoie les informations vers la BLL pour vérification et insertion
 * En cas d'erreur, renvoie vers le formulaire avec les informations erronné et demandais une rectification
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/inscription.jsp");
		rd.forward(request, response);
		/*UtilisateurManager userManager = new UtilisateurManager();
		userManager.ajouterUtilisateur("test2", "Duchemin", "John", "ducheminj@gmail.com", "rue de la bistouqette", 15100, "Robinet-sur-buisson", "123456");*/	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String confirmationPass = request.getParameter("confirmation");
		String ville = request.getParameter("ville");
		String codePostal = request.getParameter("codePostal");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		UtilisateurManager userManager = new UtilisateurManager();
		Utilisateur user = new Utilisateur();
		try {
			user = userManager.ajouterUtilisateur(pseudo, nom, prenom, mail, rue, codePostal, ville, pass, confirmationPass);
			request.setAttribute("user", user);
		} catch (BusinessException e) {
			request.setAttribute("listeCodeErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/inscription.jsp");
			rd.forward(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Acceuil.jsp");
		rd.forward(request, response);
	}
}
