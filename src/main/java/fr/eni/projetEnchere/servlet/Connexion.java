package fr.eni.projetEnchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetEnchere.bll.UtilisateurManager;

/**
 * Servlet permettant la connexion d'un utilisateur
 * GET : renvoie vers la JSP
 * POST : envoie les informations vers la BLL pour vérification
 * Return : en cas de login/MDP correct, envoie un cookie pour validé la connexion sinon renvoie vers la JSP avec un message d'erreur
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager userManager = new UtilisateurManager();
		String pseudo = request.getParameter("pseudo");
		String mdpEntrer = request.getParameter("mdp");
		String mdp = userManager.selectionMotDePasse(pseudo);
		System.out.println(mdp);
		if(mdp == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
			request.setAttribute("erreurPseudo", pseudo);
			System.out.println("MDP null, pseudo incorrect");
			rd.forward(request, response);
		}
		else {
			if(mdpEntrer.equals(mdp)) {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Acceuil.jsp");
				System.out.println("pseudo et MDP OK");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
				request.setAttribute("erreurMDP", pseudo);
				System.out.println("Erreur MDP !");
				rd.forward(request, response);
			}
		}
	}
}
