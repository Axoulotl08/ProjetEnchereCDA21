package fr.eni.projetEnchere.bo;

import java.time.LocalDate;

public class ArticleVendu {
	private int numArticle;
	private String nomArticle, description;
	private LocalDate debut, fin;
	private int prixInitial, prixVente;
	private Categorie categorie;
	private Retrait retrait;
	private Utilisateur utilisateur;
	private Enchere enchere;
}
