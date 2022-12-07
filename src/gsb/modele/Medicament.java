/* Créé le 10 Octobre 2019
*/
package gsb.modele;

/**
 * @author Gwendal
 *
 * Créée le 17/10/2019
 * 
 */


public class Medicament {
	
	protected String depotLegal;
	protected String nomCommercial;
	protected String composition;
	protected String effets;
	protected String contreIndication;
	protected float prixEchantillion;
	protected Famille uneFamille;

	/**
	 *
	 * @param depotLegal le depot legal du medicament
	 * @param nomCommercial le nom commercial du medicament
	 * @param composition la composition du medicament
	 * @param effets les effets du medicament
	 * @param contreIndication une contre indication d'un medicament
	 * @param prixEchantillion le prix du medicament
	 * @param uneFamille la famille à laquelle le medicament appartient
	 */
	public Medicament(String depotLegal, String nomCommercial, String composition, String effets, String contreIndication, float prixEchantillion, Famille uneFamille) {
		this.depotLegal = depotLegal;
		this.nomCommercial = nomCommercial;
		this.composition = composition;
		this.effets = effets;
		this.contreIndication = contreIndication;
		this.prixEchantillion = prixEchantillion;
		this.uneFamille = uneFamille;
	}
	
	/**
	 * @return Renvoie depotLegal.
	 */
	public String getDepotLegal() {
		return depotLegal;
	}
	/**
	 * @param depotLegal à définir.
	 */
	public void setDepotLegal(String depotLegal) {
		this.depotLegal = depotLegal;
	}
	
	/**
	 * @return Renvoie nomCommercial.
	 */
	public String getNomCommercial() {
		return nomCommercial;
	}
	/**
	 * @param nomCommercial à définir.
	 */
	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}
	
	/**
	 * @return Renvoie composition.
	 */
	public String getComposition() {
		return composition;
	}
	/**
	 * @param composition à définir.
	 */
	public void setComposition(String composition) {
		this.composition = composition;
	}
	
	/**
	 * @return Renvoie effets.
	 */
	public String getEffets() {
		return effets;
	}
	/**
	 * @param effets à définir.
	 */
	public void setEffets(String effets) {
		this.effets = effets;
	}
	
	/**
	 * @return Renvoie contreIndication.
	 */
	public String getContreIndication() {
		return contreIndication;
	}
	/**
	 * @param contreIndication à définir.
	 */
	public void setContreIndication(String contreIndication) {
		this.contreIndication = contreIndication;
	}
	
	/**
	 * @return Renvoie prixEchantillion.
	 */
	public float getPrixEchantillion() {
		return prixEchantillion;
	}
	/**
	 * @param prixEchantillion à définir.
	 */
	public void setPrixEchantillion(float prixEchantillion) {
		this.prixEchantillion = prixEchantillion;
	}
	
	/**
	 * @return Renvoie uneFamille.
	 */
	public Famille getUneFamille() {
		return uneFamille;
	}
	/**
	 * @param uneFamille à définir.
	 */
	public void setUneFamille(Famille uneFamille) {
		this.uneFamille = uneFamille;
	}
}
