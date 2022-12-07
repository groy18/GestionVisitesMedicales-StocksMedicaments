/*Cr�� le 17/10/2019*/
package gsb.modele.dao;

import java.sql.ResultSet;

import gsb.modele.Famille;

/**
 * @author Gwendal
 *
 * Créée le 17/10/2019
 *
 * Classe Dao de la classe Famille
 * 
 */

public class FamilleDao {

	/**
	 *
	 * @param codeFamille le code d'une famille de médicaments
	 * @return un objet Famille ou null
	 */
	public static Famille rechercher(String codeFamille){
		Famille uneFamille=null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from FAMILLE where CODEFAMILLE='"+codeFamille+"'");

		try {
			if (reqSelection.next()) {
				uneFamille = new Famille(reqSelection.getString(1), reqSelection.getString(2));	
			}
		}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from FAMILLE where CODEFAMILLE='"+codeFamille+"'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();

		return uneFamille;
	}
}
