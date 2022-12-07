/*
 * Cr?? le 23 f?vr. 2015
 *
 * TODO Pour changer le mod?le de ce fichier g?n?r?, allez ? :
 * Fen?tre - Pr?f?rences - Java - Style de code - Mod?les de code
 */
package gsb.service;

import gsb.modele.Medecin;
import gsb.modele.dao.MedecinDao;

/**
 * @author Isabelle
 *
 * Cr�ee le 23 f�vrier 2015
 *
 * Modifi� le 28/10/2019
 */
public class MedecinService {

	/**
	 *
	 * @param unCodeMedecin le code d'un m�decin
	 * @return un objet Medecin ou null
	 */
	public Medecin rechercherMedecin(String unCodeMedecin){
		Medecin unMedecin = null;

		try {
			//Code du m�decin obligatoire
			if (unCodeMedecin==null) {
				throw new Exception("Le code du m�decin est obligatoire");
			}
			//4 caract�res maximum pour le code du m�decin
			if (unCodeMedecin.length() > 4) {
				throw new Exception("Le code du m�decin ne peut pas d�passer 4 caract�res");
			}
			//Le code doit correspondre � un m�decin
			if(MedecinDao.rechercher(unCodeMedecin) == null) {
				throw new Exception("Il n'y a pas de m�decin correspondant � ce code");
			}
			unMedecin = MedecinDao.rechercher(unCodeMedecin);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return unMedecin;
	}
	
}
