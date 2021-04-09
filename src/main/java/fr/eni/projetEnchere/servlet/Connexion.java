package fr.eni.projetEnchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.erreur.BusinessException;

/**
 * Servlet permettant la connexion d'un utilisateur
 * GET : renvoie vers la JSP
 * POST : envoie les informations vers la BLL pour vérification
 * Return : en cas de login/MDP correct, créer un parametre de session pour indiqué la connexion et l'id de l'user
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
		String mdpEntrer = request.getParameter("pass");
		try {
			int idUser = userManager.verificationMDP(pseudo, mdpEntrer);
			HttpSession session = request.getSession();
			session.setAttribute("status", "login");
			session.setAttribute("id", idUser);
		}catch (BusinessException erreur) {
			request.setAttribute("listeCodeErreur", erreur.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
			rd.forward(request, response);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/Acceuil.jsp");
		rd.forward(request, response);
	}
}
