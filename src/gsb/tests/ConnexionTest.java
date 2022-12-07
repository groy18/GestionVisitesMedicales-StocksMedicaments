/*
 * Cr?? le 23 f?vr. 2015
 *
 * TODO Pour changer le mod?le de ce fichier g?n?r?, allez ? :
 * Fen?tre - Pr?f?rences - Java - Style de code - Mod?les de code
 */
package gsb.tests;

import gsb.modele.dao.ConnexionMySql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Isabelle
 * 23 f?vr. 2015
 * TODO Pour changer le mod?le de ce commentaire de type g?n?r?, allez ? :
 * Fen?tre - Pr?f?rences - Java - Style de code - Mod?les de code
 */
public class ConnexionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Raccord de m?thode auto-g?n?r?
		ResultSet resultat = ConnexionMySql
				.execReqSelection("select * from LOCALITE");
		try {
			while (resultat.next()) {
				System.out.println(resultat.getString(1) + "  "
						+ resultat.getString(2));
			}
			;
			System.out.println("fin");
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

}


