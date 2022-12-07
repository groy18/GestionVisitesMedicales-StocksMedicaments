package gsb.utils;

/**
 * @author womain
 *
 * Classe utilitaires pour Mysql
 *
 * 02/11/2019
 */

public class SQLUtils {

    /**
     *
     * @param uneDate chaine de caracteres de type 00/00/0000
     * @return une chaine de caracteres au format date 0000-00-00
     */

    public static String dateFormatSql(String uneDate) {
        String annee = uneDate.substring(6,10);
        String mois = uneDate.substring(3,5);
        String jour = uneDate.substring(0,2);

        String dateFormatSql = annee + "-" + mois + "-" + jour;

        return dateFormatSql;
    }
}
