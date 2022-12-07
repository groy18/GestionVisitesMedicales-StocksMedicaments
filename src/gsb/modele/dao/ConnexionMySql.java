package gsb.modele.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Isabelle
 *
 * Créée le 23 sept. 2014
 */
public class ConnexionMySql { // DAO = Data Access Object
	
	static Connection cnx;
	
	public ConnexionMySql(){
		cnx = null;
	}
	
	/**
	 * méthode qui permet la connexion à la base de données
	 * le fait que la méthode soit static permet d'éviter d'instancier dans une classe un objet ConnexionMySql
	 * pour utiliser cette méthode écrire : ConnexionMySql.connecterBd()
	 */
	public static void connecterBd(){
		String url = "jdbc:mysql://192.178.1.13:3306/gsbJava"; // url : Chaine de connexion
		// try permet d'essayer de lancer la connexion
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Utilisation du connecteur JDBC
			cnx = DriverManager.getConnection(url,"admindbppe","password");
		} 
		// si la connexion échoue un message d'erreur est affiché dans la console
        catch(Exception e) {
			System.out.println("Echec lors de la connexion");
		}
	}
	
	/**
	 * @param laRequete requête SQL de type SELECT
	 * @return un curseur qui contient les lignes obtenues lors de l'exécution de la requ�te, null sinon
	 *
	 * Pour utiliser cette méthode écrire : ConnexionMySql.execReqSelection(uneRequete) ou uneRequete est de type String
	 */
	public static ResultSet execReqSelection(String laRequete){ 
		connecterBd();
		ResultSet resultatReq = null;
		try {
				Statement requete = cnx.createStatement(); 
				resultatReq =requete.executeQuery(laRequete); 
		} 
		catch(Exception e) {
			System.out.println("Erreur requete : "+ laRequete);
		}
		return resultatReq;	
	}
	
	/**
	 * @param laRequete requete SQL de type INSERT, UPDATE ou DELETE
	 * @return 1 si la MAJ s'est bien déroulée, 0 sinon
	 * pour utiliser cette méthode écrire : ConnexionMySql.execReqMaj(uneRequete) ou uneRequete est de type String
	 */
	public static int execReqMaj(String laRequete){
		connecterBd();
		int nbMaj = 0;
		try {
			Statement s = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        	nbMaj = s.executeUpdate(laRequete);
        	s.close();
		}
		catch (Exception er) {
			er.printStackTrace(); 
			System.out.println("echec requ�te : "+laRequete);
		}
		return nbMaj;       
	}
	
	/**
	 * attention : tant que la connexion n'est pas fermée,
	 * les MAJ ne sont pas effectives, on reste en mode déconnecté
	 * pour utiliser cette m�thode �crire : ConnexionMySql.fermerConnexionBd()
	 */
	public static void fermerConnexionBd(){
		try{
			cnx.close();
		}
		catch(Exception e) {
			System.out.println("Erreur sur fermeture connexion");
		}
	}

}
