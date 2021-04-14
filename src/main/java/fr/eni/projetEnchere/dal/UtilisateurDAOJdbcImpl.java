package fr.eni.projetEnchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.projetEnchere.bo.Utilisateur;
/**
 * Implémentation JDBC des méthodes du CRUD
 * @author Alexandra
 *
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	private static String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, rue, code_postal, ville, mot_de_passe,"
			+ " credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
	private static String SELECT_MDP = "SELECT mot_de_passe FROM UTILISATEURS WHERE pseudo = ?";
	private static String SELECT_MDP_BY_ID = "SELECT mot_de_passe FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static String SELECT_PSEUDO = "SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo = ?";
	private static String SELECT_MAIL = "SELECT no_utilisateur FROM UTILISATEURS WHERE email = ?";
	private static String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static String UPDATE_USER_BY_ID = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, "
					+ "rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";
	
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
			e.printStackTrace();
			System.out.println("Erreur Récupération MDP");
		}
		return mdp;
	}

	

	@Override
	public int recuperationID(String pseudo) {
		int temp = 0;
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(SELECT_PSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				temp = rs.getInt(1);				
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(temp > 0) {
			System.out.println("retour id pseudo database : " + temp);
			return temp;
		}
		else 
			return -1;
	}

	@Override
	public int recuperationMail(String mail) {
		String temp = null;
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
			e.printStackTrace();
		}
		if(temp != null) {
			return Integer.parseInt(temp);
		}
		else 
			return -1;
	}

	@Override
	public Utilisateur selectById(int idUser) {
		Utilisateur user = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID);
			stmt.setInt(1, idUser);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				user.setNumUtilisateur(rs.getInt("no_utilisateur"));
				user.setNom(rs.getString("nom"));
				user.setPseudo(rs.getString("pseudo"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setNumTelephone(rs.getString("telephone"));
				user.setAdresse(rs.getString("rue"));
				user.setCodePostale(rs.getString("code_postal"));
				user.setCredit(rs.getInt("credit"));
				user.setVille(rs.getString("ville"));
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void modificationUtilisateurByID(Utilisateur utilisateur, int idUser) {
		// TODO Auto-generated method stub
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_USER_BY_ID);
			stmt.setString(1, utilisateur.getPseudo());
			stmt.setString(2, utilisateur.getNom());
			stmt.setString(3, utilisateur.getPrenom());
			stmt.setString(4, utilisateur.getEmail());
			stmt.setString(5, utilisateur.getNumTelephone());
			stmt.setString(6, utilisateur.getAdresse());
			stmt.setString(7, utilisateur.getCodePostale());
			stmt.setString(8, utilisateur.getVille());
			stmt.setString(9, utilisateur.getMotDePasse());
			stmt.setInt(10, idUser);
			stmt.executeUpdate();
			cnx.commit();
			cnx.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String recuperationMotDePasse(int idUser) {
		String mdp = null;
		try(Connection cnx = ConnectionProvider.getConnection()){
			cnx.setAutoCommit(false);
			PreparedStatement stmt = cnx.prepareStatement(SELECT_MDP_BY_ID);
			stmt.setInt(1, idUser);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				mdp = rs.getString(1);				
			}
			cnx.commit();
			cnx.close();
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erreur Récupération MDP");
		}
		return mdp;
	}
}
