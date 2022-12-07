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
 * Créee le 23 février 2015
 *
 * Modifié le 28/10/2019
 */
public class MedecinService {

	/**
	 *
	 * @param unCodeMedecin le code d'un médecin
	 * @return un objet Medecin ou null
	 */
	public Medecin rechercherMedecin(String unCodeMedecin){
		Medecin unMedecin = null;

		try {
			//Code du médecin obligatoire
			if (unCodeMedecin==null) {
				throw new Exception("Le code du médecin est obligatoire");
			}
			//4 caractères maximum pour le code du médecin
			if (unCodeMedecin.length() > 4) {
				throw new Exception("Le code du médecin ne peut pas dépasser 4 caractères");
			}
			//Le code doit correspondre à un médecin
			if(MedecinDao.rechercher(unCodeMedecin) == null) {
				throw new Exception("Il n'y a pas de médecin correspondant à ce code");
			}
			unMedecin = MedecinDao.rechercher(unCodeMedecin);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return unMedecin;
	}
	
}
