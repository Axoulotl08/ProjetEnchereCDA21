package fr.eni.projetEnchere.dal;


import fr.eni.projetEnchere.bo.Utilisateur;

/**
 * Interface pour les m�thodes du CRUD
 * @author Alexandra
 *
 */
public interface UtilisateurDAO {
	
	// Selection
	/**
	 * R�cup�ration du mot de passe en fonction de l'utilisateur pour permettre sa connexion
	 * @param pseudo de l'utilisateur entrant
	 * @return null si pseudo introuvable, sinon retourne le motDePasse li� au pseudo
	 */
	public String recuperationMotDePasse(String pseudo);
	/**
	 * R�cup�ration de la liste des mots de passe
	 * @return la liste des mail d�j� utilis� sur le site
	 */
	public int recuperationMail(String mail);
	/**
	 * R�cup�ration l'id du pseudo
	 * @return l'id du pseudo demand�, -1 si n'existe pas.
	 */
	public int recuperationID(String pseudo);
	/**
	 * Selection des informations de l'utilisateur � partir de son id
	 * @param idUser id de l'utilisateur
	 * @return les donn�es de l'utilisateur dans un objet Utilisateur
	 */
	public Utilisateur selectById(int idUser);
	

	// Insertion
	/**
	 * Insertion d'un utilisateur dans la BDD
	 * @param utilisateur : utilisateur � ins�rer
	 */
	public void insertionUtilisateur (Utilisateur utilisateur);
	
	// Modification
	/**
	 * Modification des informatios d'un utilisateur dans la BDD
	 * @param idUser id de l'utilsateur
	 * @param utilisateur information de l'utilisateur
	 */
	public void modificationUtilisateurByID(Utilisateur utilisateur, int idUser);
	
	// Suppression
	
	
}
