/* Cr�� le 10 Octobre 2019
*/
package gsb.modele;
/**
 * @author Gwendal
 *
 * Cr��e le 15/10/2019
 * 
 */
public class Visiteur {

	protected String matricule;
	protected String nom;
	protected String prenom;
	protected String login;
	protected String mdp;
	protected String adresse;
	protected String codePostal;
	protected String dateEntrer;
	protected int prime;
	protected Unite unite;

	/**
	 *
	 * @param matricule le matricule d'un visiteur
	 * @param nom le nom d'un visiteur
	 * @param prenom le prenom d'un visiteur
	 * @param login le login d'un visiteur
	 * @param mdp le mot de passe d'un visiteur
	 * @param adresse l'adresse du visiteur
	 * @param codePostal le code postal d'un visiteur
	 * @param dateEntrer la date d'entr�e du visiteur
	 * @param prime la prime du visiteur
	 * @param unite l'unite � laquelle le visiteur appartient
	 */
	
	public Visiteur(String matricule, String nom, String prenom, String login, String mdp, String adresse, String codePostal, String dateEntrer, int prime, Unite unite) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom= prenom;
		this.login = login;
		this.mdp = mdp;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.dateEntrer = dateEntrer;
		this.prime = prime;
		this.unite = unite;
	}
	
	/**
	 * @return Renvoie matricule.
	 */
	public String getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule � d�finir.
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	/**
	 * @return Renvoie nom.
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom � d�finir.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return Renvoie prenom.
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom � d�finir.
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * @return Renvoie login.
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login � d�finir.
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
	/**
	 * @return Renvoie mdp.
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param mdp � d�finir.
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	/**
	 * @return Renvoie adresse.
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse � d�finir.
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * @return Renvoie le code postal.
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal � d�finir.
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * @return Renvoie dateEntrer.
	 */
	public String getDateEntrer() {
		return dateEntrer;
	}
	/**
	 * @param dateEntrer � d�finir.
	 */
	public void setDateEntrer(String dateEntrer) {
		this.dateEntrer = dateEntrer;
	}
	
	/**
	 * @return Renvoie prime.
	 */
	public int getPrime() {
		return prime;
	}
	/**
	 * @param prime � d�finir.
	 */
	public void setPrime(int prime) {
		this.prime = prime;
	}

	/**
	 *
	 * @return l'unite du visiteur
	 */
	public Unite getUnite() {
		return unite;
	}

	/**
	 *
	 * @param unite
	 */
	public void setUnite(Unite unite) {
		this.unite = unite;
	}
}
