package fr.eni.projetEnchere.dal;

public class DAOFactory {
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOJdbcImpl();
	}
	
}
