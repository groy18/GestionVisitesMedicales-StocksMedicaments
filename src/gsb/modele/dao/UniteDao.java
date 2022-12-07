package gsb.modele.dao;

import gsb.modele.Unite;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author womain
 *
 * Créée le 26/10/2019
 *
 * Classe Dao d'une unite
 */

public class UniteDao {

    /**
     *
     * @param codeUnite le code d'une unite
     * @return un objet Unite ou null
     */
    public static Unite rechercher(String codeUnite) {
        Unite uneUnite = null;
        String requete = "SELECT * FROM UNITE WHERE CodeUnit = '" + codeUnite + "'";
        ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);

        try {
            if (reqSelection.next()) {
                uneUnite = new Unite(reqSelection.getString(1), reqSelection.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur avec la requete SELECT * FROM UNITE WHERE CodeUnit = '" + codeUnite + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return uneUnite;
    }
}
