package gsb.modele.dao;

import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author womain
 *
 * Créée le 21/11/2019
 *
 * Classe Dao de la classe offrir
 */

public class OffrirDao {

    /**
     *
     * @param depotLegal le depot legal du medicament
     * @param reference la reference de la visite
     * @return un objet Offrir ou null
     */
    public static Offrir rechercher(String depotLegal, String reference) {
        Offrir unOffrir = null;
        Medicament unMedicament;
        Visite uneVisite;
        String requete = "SELECT * FROM OFFRIR WHERE DepotLegal = '" + depotLegal + "' AND Reference = '" + reference + "'";
        ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);

        try {
            if(reqSelection.next()) {
                unMedicament = MedicamentDao.rechercher(reqSelection.getString(1));
                uneVisite = VisiteDao.rechercher(reqSelection.getString(2));
                unOffrir = new Offrir(unMedicament, uneVisite, reqSelection.getInt(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur avec la requete SELECT * FROM OFFRIR WHERE DepotLegal = '" + depotLegal + "' AND Reference = '" + reference + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return unOffrir;
    }

    /**
     *
     * @param unOffrir un objet Offrir
     * @return 0 si echec et 1 si réussi
     */
    public static int creer(Offrir unOffrir) {
        int result = 0;
        String unDepotLegal = unOffrir.getUnMedicament().getDepotLegal();
        String uneReference = unOffrir.getUneVisite().getReference();
        int uneQuantite = unOffrir.getQuantiteOfferte();
        String requete = "INSERT INTO OFFRIR VALUES ('" + unDepotLegal + "', '" + uneReference + "', " + uneQuantite + ")";

        try {
            result = ConnexionMySql.execReqMaj(requete);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur avec la requete INSERT INTO OFFRIR VALUES (" + unDepotLegal + "', '" + uneReference + "', " + uneQuantite + ")");
        }
        ConnexionMySql.fermerConnexionBd();

        return result;
    }

    /**
     *
     * @param unOffrir un objet Offrir
     * @return 0 si echec et 1 si réussi
     *
     * Soustrait une quantité offerte du stock de medicament correspondant du visiteur
     *
     */
    public static int soustraireStock(Offrir unOffrir) {
        int result = 0;
        String unDepotLegal = unOffrir.getUnMedicament().getDepotLegal();
        String unMatricule = unOffrir.getUneVisite().getUnVisiteur().getMatricule();
        int uneQuantite = unOffrir.getQuantiteOfferte();
        String requete = "UPDATE STOCKER SET QteStock = QteStock - " + uneQuantite + " WHERE DepotLegal = '" + unDepotLegal + "' AND Matricule = '" + unMatricule + "'";

        try {
            result = ConnexionMySql.execReqMaj(requete);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur avec la requete UPDATE STOCKER SET QteStock = QteStock - " + uneQuantite + " WHERE DepotLegal = '" + unDepotLegal + "' AND Matricule = '" + unMatricule + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return result;
    }

    /**
     *
     * @param depotLegal le depot legal du medicament
     * @param reference la reference de la visite
     * @return 1 si réussie, 0 si échec
     */
    public static int supprimer(String depotLegal, String reference) {
        int result = 0;
        String requete = "DELETE FROM OFFRIR WHERE DepotLegal = '" + depotLegal + "' AND Reference = '" + reference + "'";

        try {
            result = ConnexionMySql.execReqMaj(requete);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur requete DELETE FROM OFFRIR WHERE DepotLegal = '" + depotLegal + "' AND Reference = '" + reference + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return result;
    }

    /**
     *
     * @param reference la reference d'une visite
     * @return une collection d'objet Offrir correspondant à la visite
     */
    public static ArrayList<Offrir> rechercherOffreVisite(String reference) {
        Offrir unOffrir;
        Medicament unMedicament;
        Visite uneVisite;
        String requete = "SELECT * FROM OFFRIR WHERE Reference = '" + reference + "'";
        ArrayList<Offrir> colOffre = new ArrayList<Offrir>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);

        try {
            while(reqSelection.next()) {
                unMedicament = MedicamentDao.rechercher(reqSelection.getString(1));
                uneVisite = VisiteDao.rechercher(reqSelection.getString(2));
                unOffrir = new Offrir(unMedicament, uneVisite, reqSelection.getInt(3));
                colOffre.add(unOffrir);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur requete SELECT * FROM OFFRIR WHERE Reference = '" + reference + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return colOffre;
    }
}
