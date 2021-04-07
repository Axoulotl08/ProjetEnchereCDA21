package fr.eni.projetEnchere.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilisateur : contient les donnée d'un utilisateur du site
 * @author Alexandra
 *
 */
public class Utilisateur {
	private int numUtilisateur;
	private String pseudo, nom, prenom, email, adresse;
	private String codePostale;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean admin;
	private List<ArticleVendu> venteUtilisateur = new ArrayList<>();
	private List<ArticleVendu> achatUtilisateur = new ArrayList<>();
	private List<Enchere> listEnchere = new ArrayList<>();
	
	/**
	 * Constructeur avec les champs requis pour l'inscrption
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 * @param codePostale
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String adresse, String codePostale,
			String ville, String motDePasse) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.codePostale = codePostale;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = 0;
		this.admin = false;
	}

	/**
	 * Constructeur utilisant tout les champs
	 * @param numUtilisateur
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param adresse
	 * @param codePostale
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param admin
	 * @param venteUtilisateur
	 * @param achatUtilisateur
	 * @param listEnchere
	 */
	public Utilisateur(int numUtilisateur, String pseudo, String nom, String prenom, String email, String adresse,
			String codePostale, String ville, String motDePasse, int credit, boolean admin,
			List<ArticleVendu> venteUtilisateur, List<ArticleVendu> achatUtilisateur, List<Enchere> listEnchere) {
		super();
		this.numUtilisateur = numUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
		this.codePostale = codePostale;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.admin = admin;
		this.venteUtilisateur = venteUtilisateur;
		this.achatUtilisateur = achatUtilisateur;
		this.listEnchere = listEnchere;
	}

	public int getNumUtilisateur() {
		return numUtilisateur;
	}

	public void setNumUtilisateur(int numUtilisateur) {
		this.numUtilisateur = numUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(String codePostale) {
		this.codePostale = codePostale;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<ArticleVendu> getVenteUtilisateur() {
		return venteUtilisateur;
	}

	public void setVenteUtilisateur(List<ArticleVendu> venteUtilisateur) {
		this.venteUtilisateur = venteUtilisateur;
	}

	public List<ArticleVendu> getAchatUtilisateur() {
		return achatUtilisateur;
	}

	public void setAchatUtilisateur(List<ArticleVendu> achatUtilisateur) {
		this.achatUtilisateur = achatUtilisateur;
	}

	public List<Enchere> getListEnchere() {
		return listEnchere;
	}

	public void setListEnchere(List<Enchere> listEnchere) {
		this.listEnchere = listEnchere;
	}
	
}
