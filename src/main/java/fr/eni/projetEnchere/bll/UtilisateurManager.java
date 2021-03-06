package fr.eni.projetEnchere.bll;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.DAOFactory;
import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.erreur.BusinessException;


/**
 * Permet d'effectuer les traitements et vérification pour les utilisateurs
 * @author Axou
 *
 */
public class UtilisateurManager {
	private UtilisateurDAO enchereDAO;
	
	public UtilisateurManager() {
		this.enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public Utilisateur ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String rue, 
			String codePoste, String ville, String motDePasse, String confirmationPass) throws BusinessException{
		// Vérification des champs ŕ faire
		BusinessException erreur = new BusinessException();
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, codePoste, ville, motDePasse);
		verifCodePostal(codePoste, erreur);
		verifMdpEtConfirmation(motDePasse, confirmationPass, erreur);
		int id = verifPseudo(pseudo, erreur);
		if(id != -1) {
			erreur.ajouterErreur(CodeErreurBLL.ERREUR_PSEUDO);
		}
		id = verifMail(email, erreur);
		if(id != -1) {
			erreur.ajouterErreur(CodeErreurBLL.ERREUR_EMAIL_DEJA_UTILISER);
		}
		if(!erreur.hasErreurs()) {
			System.out.println("UserManager !!");
			enchereDAO.insertionUtilisateur(utilisateur);
		}
		else {
			throw erreur;
		}
		return utilisateur;
	}
	

	/**
	 * 
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param rue
	 * @param codePoste
	 * @param ville
	 * @param motDePasse
	 * @param confirmationPass
	 * @param numTelephone
	 * @param userId
	 * @throws BusinessException
	 */
	public void modificationUtilisateur(String pseudo, String nom, String prenom, String email, String rue, 
			String codePoste, String ville, String motDePasse, String confirmationPass, String numTelephone, int userId) throws BusinessException{
		BusinessException exception = new BusinessException();
		verifCodePostal(codePoste, exception);
		if(!motDePasse.trim().isEmpty() && !confirmationPass.trim().isEmpty()) {
			verifMdpEtConfirmation(motDePasse, confirmationPass, exception);
			System.out.println("mdp non vide !");
			System.out.println(motDePasse);
		}else {
			motDePasse = enchereDAO.recuperationMotDePasse(userId);
			System.out.println("recupération pass existant");
			System.out.println(motDePasse);
		}
		int id = verifPseudo(pseudo, exception);
		System.out.println("id = " + id + " id user : " + userId);
		if(id != -1 && id != userId) {
			exception.ajouterErreur(CodeErreurBLL.ERREUR_PSEUDO);
		}
		id = verifMail(email, exception);
		if(id != -1 && id != userId) {
			exception.ajouterErreur(CodeErreurBLL.ERREUR_EMAIL_DEJA_UTILISER);
		}
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, codePoste, ville, numTelephone, motDePasse);
		if(!exception.hasErreurs()) {
			enchereDAO.modificationUtilisateurByID(utilisateur, userId);
		}
		else {
			throw exception;
		}
	}
	
	/**
	 * 
	 * @param pseudo
	 * @return le mdr lieu au pseudo si existant, sinon retourne null
	 */
	public String selectionMotDePasse (String pseudo) {
		String mdp = null;
		mdp = enchereDAO.recuperationMotDePasse(pseudo);
		return mdp;
	}
	
	/**
	 * Vérifie que le code postal est bien un nombre compris entre 01000 et 99999
	 * @param codePostale
	 * @param erreur
	 */
	private void verifCodePostal(String codePostale, BusinessException erreur) {
		try {
			int codePostal = Integer.parseInt(codePostale);
			if(codePostal < 1000 || codePostal > 99999) {
				erreur.ajouterErreur(CodeErreurBLL.ERREUR_CODE_POSTAL);
			}
		}
		catch(NumberFormatException n) {
			erreur.ajouterErreur(CodeErreurBLL.ERREUR_FORMAT_CP);
		}
	}
	
	/**
	 * Vérifie que le mdp et ça confirmation sont identique
	 * @param mdp
	 * @param confirmation
	 * @param exception : génčre une exception ŕ afficher ŕ l'utilisateur en cas d'erreur de mots de passe
	 */
	private void verifMdpEtConfirmation(String mdp, String confirmation, BusinessException exception) {
		if(!mdp.equals(confirmation)) {
			exception.ajouterErreur(CodeErreurBLL.ERREUR_CONFIRMATION_MOT_DE_PASSE);
		}
	}
	/**
	 * Vérifie que le pseudo est libre
	 * @param pseudo que l'utilisateur veux utilisé
	 * @param exception généčre une exception si le pseudo est utilisé
	 */
	private int verifPseudo(String pseudo, BusinessException exception) {
		int id = enchereDAO.recuperationID(pseudo);
		return id;
	}
	
	/**
	 * Vérifie que le mail est libre
	 * @param mail
	 * @param exception génčre une exception si le mail est utilisé
	 */
	private int verifMail(String mail, BusinessException exception) {
		int id = enchereDAO.recuperationMail(mail);
		return id;
	}

	public int verificationMDP(String pseudo, String mdpEntrer) throws BusinessException{
		BusinessException erreur = new BusinessException();
		String mdp = selectionMotDePasse(pseudo);
		if(mdp != null) {
			if(mdpEntrer.equals(mdp)) {
				return enchereDAO.recuperationID(pseudo);
			}
			else {
				erreur.ajouterErreur(CodeErreurBLL.ERREUR_MDP_INCORRECT);
				throw erreur;
			}
		}
		else {
			erreur.ajouterErreur(CodeErreurBLL.ERREUR_IDENTIFIANT_INCORRECT);
			throw erreur;		
		}	
	}
	
	
	/**
	 * Renvoie les informations de l'utilisateur ŕ partir de son ID
	 * @param idUser
	 * @return
	 */
	public Utilisateur recuperationUtilateurParID(int idUser) throws BusinessException{
		Utilisateur user = enchereDAO.selectById(idUser);
		return user;
	}
}
