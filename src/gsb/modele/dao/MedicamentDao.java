/*Cr�� le 17/10/2019 */
package gsb.modele.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import gsb.modele.Medicament;
import gsb.modele.Famille;
import gsb.modele.Medecin;

/**
 * @author Gwendal
 *
 * Créé le 17/10/2019
 *
 * Classe Dao de la classe Medicament
 * 
 */

public class MedicamentDao {

	/**
	 *
	 * @param depotLegal le dépot légal d'un médicament
	 * @return un objet Medicament ou null
	 */
	public static Medicament rechercher(String depotLegal){
		Medicament unMedicament=null;
		Famille uneFamille;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDICAMENT where DEPOTLEGAL ='"+depotLegal+"'");
		try {
			if (reqSelection.next()) {
				uneFamille = FamilleDao.rechercher(reqSelection.getString(7));
				unMedicament = new Medicament(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), reqSelection.getFloat(6), uneFamille);	
			}
		}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requete - select * from MEDICAMENT where DEPOTLEGAL ='"+depotLegal+"'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();

		return unMedicament;
	}

	/**
	 *
	 * @return une collection avec tous les médicaments de la bdd
	 */
	public static ArrayList<Medicament> rechercherTousLesMed(){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select DEPOTLEGAL from MEDICAMENT");

		try{
			while (reqSelection.next()) {
				String depotLegal = reqSelection.getString(1);
		    	collectionDesMedicaments.add(MedicamentDao.rechercher(depotLegal));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedicaments()");
		}
		ConnexionMySql.fermerConnexionBd();

		return collectionDesMedicaments;
	}

	/**
	 *
	 * @return un dictionnaire avec tous les médicaments de la bdd
	 */
	public static HashMap<String,Medicament> retournerDictionnaireDesMedicaments(){
		HashMap<String, Medicament> diccoDesMedicaments = new HashMap<String, Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select DEPOTLEGAL from MEDICAMENT");

		try{
			while (reqSelection.next()) {
				String depotLegal = reqSelection.getString(1);
		    	diccoDesMedicaments.put(depotLegal, MedicamentDao.rechercher(depotLegal));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesMedicaments()");
		}
		ConnexionMySql.fermerConnexionBd();

		return diccoDesMedicaments;
	}
}
