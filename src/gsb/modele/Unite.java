package gsb.modele;

/**
 *
 * @author womain
 *
 * Créée le 26/10/2019
 *
 * Classe Unite, les visiteurs font partis d'une unite
 */

public class Unite {

    protected String codeUnite;
    protected String nomUnite;

    /**
     *
     * @param codeUnite chaine de caracteres
     * @param nomUnite chaine de caracteres
     */

    public Unite(String codeUnite, String nomUnite) {
        this.codeUnite = codeUnite;
        this.nomUnite = nomUnite;
    }

    /**
     *
     * @return le code de l'unite
     */
    public String getCodeUnite() {
        return codeUnite;
    }

    /**
     *
     * @param codeUnite chaine de caracteres
     */
    public void setCodeUnite(String codeUnite) {
        this.codeUnite = codeUnite;
    }

    /**
     *
     * @return le nom de l'unite
     */
    public String getNomUnite() {
        return nomUnite;
    }

    /**
     *
     * @param nomUnite chaine de caracteres
     */
    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }
}
