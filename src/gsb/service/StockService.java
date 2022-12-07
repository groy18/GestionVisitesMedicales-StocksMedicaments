/*Cr�� le 7/11/2019 */
package gsb.service;

import gsb.modele.Stock;
import gsb.modele.Medicament;
import gsb.modele.Visiteur;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.StockDao;
import gsb.modele.dao.VisiteurDao;
import gsb.utils.ValidationUtils;

/**
 * @author Gwendal
 *
 * Créée le 21/11/2019
 * 
 */
public class StockService {

    /**
     *
      * @param depotLegal le dépot léal du médicament
     * @param matricule le matricule d'un visiteur
     * @param qteStock une quantité de médicament
     * @return 0 si échec, 1 si réussie
     */
	public int ajoutStock(String depotLegal, String matricule, int qteStock) {
        int result = 0;
        Visiteur unVisiteur;
        Medicament unMedicament;
        Stock unStock;

        try{
        	//Un dépot légal doit étre renseigné
        	if (depotLegal==null) {
                throw new Exception("Donn�e obligatoire : D�pot L�gal");
            }
        	//Un matricule doit étre renseigné
        	if (matricule==null) {
                throw new Exception("Donn�e obligatoire : Matricule");
            }
        	//Une quantité ajoutée ne doit pas étre inférieure égale à 0
    		if (qteStock<=0) {
                throw new Exception("On ne peut pas ajouter une quantit� inf�rieure ou �gale � 0");
            }
    		//Le d�pot l�gal doit exister dans la base
    		if (MedicamentDao.rechercher(depotLegal)==null) {
                throw new Exception("Le d�pot legal n'existe pas");
            }
    		//Le matricule doit exister dans la base
    		if (VisiteurDao.rechercher(matricule)==null) {
                throw new Exception("Le visiteur n'existe pas");
            }
    		unVisiteur = VisiteurDao.rechercher(matricule);
    		unMedicament = MedicamentDao.rechercher(depotLegal);          
            unStock = new Stock(unMedicament, unVisiteur, qteStock);
            result = StockDao.ajouter(unStock);
    		}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param depotLegal le dépot légal d'un médicament
     * @param matricule le matricule d'un visiteur
     * @return un objet stock ou null
     */
    public Stock rechercherStock(String depotLegal, String matricule) {
        Stock unStock = null;

        try {
            //Tous les champs sont obligatoires
            if(depotLegal == null || matricule == null) {
                throw new Exception("Tous les champs sont obligatoires !");
            }
            //Max 4 caractères pour le matricule d'un visiteur
            if(matricule.length() > 4) {
                throw new Exception("Le matricule du visiteur ne doit pas dépasser 4 caractères");
            }
            //Un visiteur doit correspondre au matricule
            if(VisiteurDao.rechercher(matricule) == null) {
                throw new Exception("Pas de visiteur correspondant à ce matricule");
            }
            //50 caractères max pour le dépot légal d'un médicament
            if(depotLegal.length() > 50) {
                throw new Exception("Le dépot légal ne peut pas dépasser 50 caractères");
            }
            //Un médicament correspondant au dépot légal doit exister
            if(MedicamentDao.rechercher(depotLegal) == null) {
                throw new Exception("Pas de médicament correspondant à ce dépot légal");
            }

            unStock = StockDao.rechercher(depotLegal,matricule);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return unStock;
    }
	
}
