/*
 * Cr?? le 22 f?vr. 2015
 *
 * TODO Pour changer le mod?le de ce fichier g?n?r?, allez ? :
 * Fen?tre - Pr?f?rences - Java - Style de code - Mod?les de code
 */
package gsb.modele;

/**
 * @author Isabelle
 *
 * Créée le 22 février 2015
 */

public class Localite {

	protected String codePostal;
	protected String ville;
	/**
	 * @param codePostal le code postal de la ville
	 * @param ville le nom de la ville
	 */
	public Localite(String codePostal, String ville) {
		this.codePostal = codePostal;
		this.ville = ville;
	}
	/**
	 * @return Renvoie codePostal.
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal codePostal ? d?finir.
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * @return Renvoie ville.
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville ville ? d?finir.
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

}
