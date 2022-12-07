/*
 * Cr?? le 22 f?vr. 2015
 *
 * TODO Pour changer le mod?le de ce fichier g?n?r?, allez ? :
 * Fen?tre - Pr?f?rences - Java - Style de code - Mod?les de code
 */
package gsb.modele.dao;

import gsb.modele.Localite;
import gsb.modele.Medecin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Isabelle
 *
 * Créée le 22 février 2015
 *
 * Classe Dao de la classe Medecin
 */
public class MedecinDao {

	/**
	 *
	 * @param codeMedecin le code d'un medecin
	 * @return un objet Medecin ou null
	 */
	public static Medecin rechercher(String codeMedecin){
		Medecin unMedecin=null;
		Localite uneLocalite= null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDECIN where CODEMED ='"+codeMedecin+"'");

		try {
			if (reqSelection.next()) {
				uneLocalite = LocaliteDao.rechercher(reqSelection.getString(8));
				unMedecin = new Medecin(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), uneLocalite, reqSelection.getString(5), reqSelection.getString(6), reqSelection.getString(7) );
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unMedecin;
	}

	/**
	 *
	 * @return une collection contenant tous les medecins de la bdd
	 */
	public static ArrayList<Medecin> retournerCollectionDesMedecins(){
		ArrayList<Medecin> collectionDesMedecins = new ArrayList<Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");

		try{
			while (reqSelection.next()) {
				String codeMedecin = reqSelection.getString(1);
		    	collectionDesMedecins.add(MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecins()");
		}
		ConnexionMySql.fermerConnexionBd();

		return collectionDesMedecins;
	}

	/**
	 *
	 * @return un dictionnaire contenant tous les medecins de la bdd
	 */
	public static HashMap<String,Medecin> retournerDictionnaireDesMedecins(){
		HashMap<String, Medecin> diccoDesMedecins = new HashMap<String, Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");

		try{
			while (reqSelection.next()) {
				String codeMedecin = reqSelection.getString(1);
		    	diccoDesMedecins.put(codeMedecin, MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesMedecins()");
		}
		ConnexionMySql.fermerConnexionBd();

		return diccoDesMedecins;
	}

}
