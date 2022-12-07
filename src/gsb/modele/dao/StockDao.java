package gsb.modele.dao;

import gsb.modele.Medicament;
import gsb.modele.Stock;
import gsb.modele.Visiteur;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gwendal
 *
 * Créée le 7/11/2019
 *
 */

public class StockDao {

    /**
     *
     * @param unStock un objet Stock
     * @return 0 si échec, 1 si réussie
     */
	public static int ajouter(Stock unStock) {
        int result = 0;
        String unVisiteur = unStock.getUnVisiteur().getMatricule();
        String unMedicament = unStock.getUnMedicament().getDepotLegal();
        int qteStock = unStock.getQteStock();
        String requete = "INSERT INTO STOCKER VALUES ('" + unMedicament + "', '" + unVisiteur + "', '"  + qteStock + "') ON DUPLICATE KEY UPDATE qteStock = qteStock + '"  + qteStock + "'";

        try {
            result = ConnexionMySql.execReqMaj(requete);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur avec la requete INSERT INTO STOCKER VALUES ('" + unMedicament + "', '" + unVisiteur + "', '"  + qteStock + "') ON DUPLICATE KEY UPDATE qteStock = qteStock + '"  + qteStock + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return result;
    }

    /**
     *
     * @param depotLegal le dépot legal d'un medicament
     * @param matricule le matricule d'un visiteur
     * @return un objet Stock ou null
     */
    public static Stock rechercher(String depotLegal, String matricule) {
        Stock unStock = null;
        Visiteur unVisiteur = null;
        Medicament unMedicament = null;
        String requete = "SELECT * FROM STOCKER WHERE DepotLegal = '" + depotLegal + "' AND Matricule = '" + matricule + "'";

        ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);

        try {
            if(reqSelection.next()) {
                unMedicament = MedicamentDao.rechercher(reqSelection.getString(1));
                unVisiteur = VisiteurDao.rechercher(reqSelection.getString(2));
                unStock = new Stock(unMedicament, unVisiteur, reqSelection.getInt(3));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur avec la requete SELECT * FROM STOCKER WHERE DepotLegal = '" + depotLegal + "' AND Matricule = '" + matricule + "'");
        }
        ConnexionMySql.fermerConnexionBd();

        return unStock;
    }
	
	
}
