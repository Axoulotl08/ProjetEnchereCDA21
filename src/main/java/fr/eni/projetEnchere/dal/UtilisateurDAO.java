package fr.eni.projetEnchere.dal;


import fr.eni.projetEnchere.bo.Utilisateur;

/**
 * Interface pour les méthodes du CRUD
 * @author Alexandra
 *
 */
public interface UtilisateurDAO {
	
	// Selection
	/**
	 * Récupération du mot de passe en fonction de l'utilisateur pour permettre sa connexion
	 * @param pseudo de l'utilisateur entrant
	 * @return null si pseudo introuvable, sinon retourne le motDePasse lié au pseudo
	 */
	public String recuperationMotDePasse(String pseudo);
	/**
	 * Récupération de la liste des mots de passe
	 * @return la liste des mail déjà utilisé sur le site
	 */
	public int recuperationMail(String mail);
	/**
	 * Récupération l'id du pseudo
	 * @return l'id du pseudo demandé, -1 si n'existe pas.
	 */
	public int recuperationID(String pseudo);
	/**
	 * Selection des informations de l'utilisateur à partir de son id
	 * @param idUser id de l'utilisateur
	 * @return les données de l'utilisateur dans un objet Utilisateur
	 */
	public Utilisateur selectById(int idUser);
	

	// Insertion
	/**
	 * Insertion d'un utilisateur dans la BDD
	 * @param utilisateur : utilisateur à insérer
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
