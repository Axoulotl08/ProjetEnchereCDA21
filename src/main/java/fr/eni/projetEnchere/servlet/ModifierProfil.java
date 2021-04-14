package fr.eni.projetEnchere.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.util.mime.RFC2231Utility;

import fr.eni.projetEnchere.bll.UtilisateurManager;
import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.erreur.BusinessException;

/**
 * Servlet implementation class ModifierProfil
 */
@WebServlet("/ModifierProfil")
public class ModifierProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idUser = (int)session.getAttribute("id");
		//System.out.println("ID en sessions : " + idUser);
		UtilisateurManager userManager = new UtilisateurManager();
		Utilisateur user = null;
		try {
			user = userManager.recuperationUtilateurParID(idUser);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/modifierProfil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		String adresse = request.getParameter("rue");
		String cp = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("pass");
		String confMDP = request.getParameter("confirmation");
		Utilisateur userRequest = (Utilisateur) request.getAttribute("user");
		HttpSession session = request.getSession();
		int idUser = (int)session.getAttribute("id");
		UtilisateurManager userManager = new UtilisateurManager();
		try {
			userManager.modificationUtilisateur(pseudo, nom, prenom, mail, adresse, cp, ville, mdp, confMDP, telephone, idUser);
			RequestDispatcher rd = request.getRequestDispatcher("/Acceuil.jsp");
			
			rd.forward(request, response);
		}catch (BusinessException e) {
			request.setAttribute("listeCodeErreur", e.getListeCodesErreur());
			request.setAttribute("user", userRequest);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/modifierProfil.jsp");
			rd.forward(request, response);
		}
	}

}
