package fr.eni.projetEnchere.dal;

import java.util.List;

import fr.eni.projetEnchere.bo.Utilisateur;

/**
 * Interface pour les m�thodes du CRUD
 * @author Alexandra
 *
 */
public interface EnchereDAO {
	
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
	public List<String> recuperationMail();
	/**
	 * R�cup�ration de la liste des pseudos
	 * @return la liste des pseudo d�j� utiliser sur le site
	 */
	public List<String> recuperationPseudo();
	
	// Insertion
	/**
	 * Insertion d'un utilisateur dans la BDD
	 * @param utilisateur : utilisateur � ins�rer
	 */
	public void insertionUtilisateur (Utilisateur utilisateur);
	
	// Modification
	
	
	// Suppression
	
	
}
