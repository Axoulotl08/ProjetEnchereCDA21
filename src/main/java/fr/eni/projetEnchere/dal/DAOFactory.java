package fr.eni.projetEnchere.dal;

public class DAOFactory {
	public static UtilisateurDAO getEnchereDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
	
}
