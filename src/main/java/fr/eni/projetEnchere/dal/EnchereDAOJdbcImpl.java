package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetEnchere.bo.Utilisateur;
/**
 * Implémentation JDBC des méthodes du CRUD
 * @author Axou
 *
 */
public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, rue, code_postal, ville, mot_de_passe,"
			+ " credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
	private static String SELECT_MDP = "SELECT mot_de_passe FROM UTILISATEURS WHERE pseudo = ?";
	private static String SELECT_PSEUDO = "SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo = ?";
	private static String SELECT_MAIL = "SELECT no_utilisateur FROM UTILISATEURS WHERE email = ?";
	
	/**
	 * Insertion d'un utilisateur
	 */
	@Override
	public void insertionUtilisateur(Utilisateur utilisateur) {
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getAdresse());
			stmt.setString(6, utilisateur.getCodePostale());
			stmt.setString(7, utilisateur.getVille());
			stmt.setString(8, utilisateur.getMotDePasse());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				utilisateur.setNumUtilisateur(rs.getInt(1));
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur d'insertion");
		}
		
	}

	/**
	 * Récupération du mot de passe en fonction du pseudo de l'utilisateur
	 * Retourne null si pas de MDP (donc utilisateur eronné)
	 */
	@Override
	public String recuperationMotDePasse(String pseudo) {
		String mdp = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(SELECT_MDP);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				mdp = rs.getString(1);				
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur Récupération MDP");
		}
		return mdp;
	}

	

	@Override
	public int recuperationPseudo(String pseudo) {
		String temp = null;
		int id = 0;
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(SELECT_PSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				temp = rs.getString(1);				
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur Récupération MDP");
		}
		if(temp != null)
			return Integer.parseInt(temp);
		else 
			return -1;
	}

	@Override
	public int recuperationMail(String mail) {
		String temp = null;
		int id = 0;
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(SELECT_MAIL);
			stmt.setString(1, mail);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				temp = rs.getString(1);				
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur Récupération MDP");
		}
		if(temp != null)
			return Integer.parseInt(temp);
		else 
			return -1;
	}

}
