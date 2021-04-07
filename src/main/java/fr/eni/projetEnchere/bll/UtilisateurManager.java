package fr.eni.projetEnchere.bll;

import java.util.List;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.DAOFactory;
import fr.eni.projetEnchere.dal.EnchereDAO;
import fr.eni.projetEnchere.erreur.BusinessException;


/**
 * Permet d'effectuer les traitements et vérification pour les utilisateurs
 * @author Axou
 *
 */
public class UtilisateurManager {
	private EnchereDAO enchereDAO;
	
	public UtilisateurManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public int ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String rue, 
			String codePoste, String ville, String motDePasse, String confirmationPass) throws BusinessException{
		// Vérification des champs à faire
		BusinessException erreur = new BusinessException();
		boolean verifCodePoste = verifCodePostal(Integer.parseInt(codePoste));
		if(verifCodePoste && verifMdpEtConfirmation(motDePasse, confirmationPass)) {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, codePoste, ville, motDePasse);
			System.out.println("UserManager !!");
			enchereDAO.insertionUtilisateur(utilisateur);
			return 1;
		}
		else {
			System.out.println("Erreur code postale ou mdp");
			return -1;
		}
		
	}
	
	/**
	 * 
	 * @param pseudo
	 * @return le pseudo lié au mots de passe si existant, sinon retourne null
	 */
	private String selectionMotDePasse (String pseudo) {
		String mdp = null;
		mdp = enchereDAO.recuperationMotDePasse(pseudo);
		return mdp;
	}
	
	private boolean verifCodePostal(int codePostale) {
		if(codePostale > 1000 && codePostale < 99999) {
			return true;
		}
		else 
			return false;
	}
	
	private boolean verifMdpEtConfirmation(String mdp, String confirmation) {
		return mdp.equals(confirmation);
	}
	
	
	private void verifPseudo(String pseudo, List<String> listePseudo, BusinessException exception) {
		for(String pseudoEnCours:listePseudo) {
			if(pseudo.equals(pseudoEnCours))
				exception.ajouterErreur(CodeErreurBLL.ERREUR_PSEUDO);
				break;
		}
	}
	
	private void verifMail(String mail, List<String> listeMail, BusinessException exception) {
		for(String mailEnCours:listeMail) {
			if(mail.equals(mail))
				exception.ajouterErreur(CodeErreurBLL.ERREUR_EMAIL);
				break;
		}
	}
	
}
