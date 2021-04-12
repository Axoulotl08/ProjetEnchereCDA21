package fr.eni.projetEnchere.bll;

import fr.eni.projetEnchere.bo.Utilisateur;
import fr.eni.projetEnchere.dal.DAOFactory;
import fr.eni.projetEnchere.dal.UtilisateurDAO;
import fr.eni.projetEnchere.erreur.BusinessException;


/**
 * Permet d'effectuer les traitements et v�rification pour les utilisateurs
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
		// V�rification des champs � faire
		BusinessException erreur = new BusinessException();
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, rue, codePoste, ville, motDePasse);
		verifCodePostal(codePoste, erreur);
		verifMdpEtConfirmation(motDePasse, confirmationPass, erreur);
		verifPseudo(pseudo, erreur);
		verifMail(email, erreur);
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
	 * @return le mdr lieu au pseudo si existant, sinon retourne null
	 */
	public String selectionMotDePasse (String pseudo) {
		String mdp = null;
		mdp = enchereDAO.recuperationMotDePasse(pseudo);
		return mdp;
	}
	
	/**
	 * V�rifie que le code postal est bien un nombre compris entre 01000 et 99999
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
	 * V�rifie que le mdp et �a confirmation sont identique
	 * @param mdp
	 * @param confirmation
	 * @param exception : g�n�re une exception � afficher � l'utilisateur en cas d'erreur de mots de passe
	 */
	private void verifMdpEtConfirmation(String mdp, String confirmation, BusinessException exception) {
		if(!mdp.equals(confirmation)) {
			exception.ajouterErreur(CodeErreurBLL.ERREUR_CONFIRMATION_MOT_DE_PASSE);
		}
	}
	/**
	 * V�rifie que le pseudo est libre
	 * @param pseudo que l'utilisateur veux utilis�
	 * @param exception g�n��re une exception si le pseudo est utilis�
	 */
	private void verifPseudo(String pseudo, BusinessException exception) {
		int id = enchereDAO.recuperationID(pseudo);
		if(id != -1) {
			exception.ajouterErreur(CodeErreurBLL.ERREUR_PSEUDO);
		}
	}
	
	/**
	 * V�rifie que le mail est libre
	 * @param mail
	 * @param exception g�n�re une exception si le mail est utilis�
	 */
	private void verifMail(String mail, BusinessException exception) {
		int id = enchereDAO.recuperationMail(mail);
		if(id != -1) {
			exception.ajouterErreur(CodeErreurBLL.ERREUR_EMAIL_DEJA_UTILISER);
		}
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
	 * Renvoie les informations de l'utilisateur � partir de son ID
	 * @param idUser
	 * @return
	 */
	public Utilisateur recuperationUtilateurParID(int idUser) throws BusinessException{
		BusinessException erreur = new BusinessException();
		Utilisateur user = enchereDAO.selectById(idUser);
		return user;
	}
}
