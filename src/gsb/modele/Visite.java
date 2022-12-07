package gsb.modele;

/**
 *
 * @author womain
 *
 * Créée le 26/10/2019
 */

public class Visite {

    protected String reference;
    protected String dateVisite;
    protected String unCommentaire;
    protected Visiteur unVisiteur;
    protected Medecin unMedecin;

    /**
     *
     * @param reference code de la visite
     * @param dateVisite date de la visite
     * @param unCommentaire commentaire concernant la visite
     * @param unVisiteur visiteur qui effectue la visite
     * @param unMedecin medecin chez qui la visite est effectuée
     */

    public Visite(String reference, String dateVisite, String unCommentaire, Visiteur unVisiteur, Medecin unMedecin) {
        this.reference = reference;
        this.dateVisite = dateVisite;
        this.unCommentaire = unCommentaire;
        this.unVisiteur = unVisiteur;
        this.unMedecin = unMedecin;
    }

    /**
     *
     * @return la référence de la visite
     */
    public String getReference() {
        return reference;
    }

    /**
     *
     * @param reference chaine de caracteres
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     *
     * @return la date de la visite
     */
    public String getDateVisite() {
        return dateVisite;
    }

    /**
     *
     * @param dateVisite chaine de caracteres
     */
    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    /**
     *
     * @return commentaire sur la visite
     */
    public String getUnCommentaire() {
        return unCommentaire;
    }

    /**
     *
     * @param unCommentaire chaine de caractere
     */
    public void setUnCommentaire(String unCommentaire) {
        this.unCommentaire = unCommentaire;
    }

    /**
     *
     * @return un objet Visiteur
     */
    public Visiteur getUnVisiteur() {
        return unVisiteur;
    }

    /**
     *
     * @param unVisiteur objet Visisteur
     */
    public void setUnVisiteur(Visiteur unVisiteur) {
        this.unVisiteur = unVisiteur;
    }

    /**
     *
     * @return un objet Medecin
     */
    public Medecin getUnMedecin() {
        return unMedecin;
    }

    /**
     *
     * @param unMedecin objet Medecin
     */
    public void setUnMedecin(Medecin unMedecin) {
        this.unMedecin = unMedecin;
    }
}
