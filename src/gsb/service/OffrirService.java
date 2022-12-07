package gsb.service;

import gsb.modele.Medicament;
import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.*;
import gsb.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * @author womain
 *
 * CRéée le 21/11/2019
 *
 * Classe service de OffrirDao
 */

public class OffrirService {

    /**
     *
     * @param depotLegal le depot du medicament
     * @param reference la reference de la visite
     * @return un objet Offrir ou null
     */
    public Offrir rechercherOffrir(String depotLegal, String reference) {
        Offrir unOffrir = null;

        try {
            //Les champs ne peuvent pas être vides
            if(depotLegal == null || reference == null) {
                throw new Exception("Tous les champs sont obligatoires");
            }
            //Longueur du dépôt dans la bdd : 50 caractères max
            if(depotLegal.length() > 50) {
                throw new Exception("La longueur du dépôt doit être inférieur à 50 caractères");
            }
            //Le médicament doit exister dans la bdd
            if(MedicamentDao.rechercher(depotLegal) == null) {
                throw new Exception("Aucun médicament ne correspond à ce dépôt ");
            }
            //Longueur d'une référence dans la bdd : 5 caractères max
            if(reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //La visite doit exister dans la bdd
            if(VisiteDao.rechercher(reference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            unOffrir = OffrirDao.rechercher(depotLegal, reference);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return unOffrir;
    }

    /**
     *
     * @param depotLegal un dépot legal d'un médicament
     * @param reference la référence de la visite
     * @param uneQuantiteOfferte une quantité de médicament à offrir
     * @return 0 si échec, 1 si réussi
     */
    public int creerOffrir(String depotLegal, String reference, int uneQuantiteOfferte) {
        int result = 0;
        Visite uneVisite;
        Medicament unMedicament;
        Offrir unOffrir;

        try {
            //Les champs ne peuvent pas être null
            if(depotLegal == null || reference == null) {
                throw new Exception("Tous les champs sont obligatoires !");
            }
            //La quantité de médoc offerte doit être supérieur à 0
            if(uneQuantiteOfferte < 1) {
                throw new Exception("Vous devez offrir au moins 1 médicament");
            }
            //Longueur du dépôt dans la bdd : 50 caractères max
            if(depotLegal.length() > 50) {
                throw new Exception("La longueur du dépôt doit être inférieur à 50 caractères");
            }
            //Le médicament doit exister dans la bdd
            if(MedicamentDao.rechercher(depotLegal) == null) {
                throw new Exception("Aucun médicament ne correspond à ce dépôt ");
            }
            //Longueur d'une référence dans la bdd : 5 caractères max
            if(reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //La visite doit exister dans la bdd
            if(VisiteDao.rechercher(reference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            //Le stock de ce médicament doit exister pour être offert
            if(StockDao.rechercher(depotLegal,VisiteDao.rechercher(reference).getUnVisiteur().getMatricule()) == null) {
                throw new Exception("Il n'y a pas de stock correspondant à ce médicament");
            }
            //Le stock du médoc doit être supérieur à la quantité offerte
            if(StockDao.rechercher(depotLegal, VisiteDao.rechercher(reference).getUnVisiteur().getMatricule()).getQteStock() < uneQuantiteOfferte) {
                throw new Exception("Votre stock de ce médicament est insuffisant");
            }
            unMedicament = MedicamentDao.rechercher(depotLegal);
            uneVisite = VisiteDao.rechercher(reference);
            unOffrir = new Offrir(unMedicament, uneVisite, uneQuantiteOfferte);
            result = OffrirDao.creer(unOffrir);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param depotLegal le dépôt légal du médicament
     * @param reference la référence de la visite
     * @param uneQuantiteOfferte la quantité offerte de médicament
     * @return 0 si échec, 1 si réussi
     */
    public int soustraireStock(String depotLegal, String reference, int uneQuantiteOfferte) {
        int result = 0;
        Visite uneVisite;
        Medicament unMedicament;
        Offrir unOffrir;

        try {
            //Les champs ne peuvent pas être null
            if(depotLegal == null || reference == null) {
                throw new Exception("Tous les champs sont obligatoires !");
            }
            //La quantité de médoc offerte doit être supérieur à 0
            if(uneQuantiteOfferte < 1) {
                throw new Exception("Vous devez offrir au moins 1 médicament");
            }
            //Longueur du dépôt dans la bdd : 50 caractères max
            if(depotLegal.length() > 50) {
                throw new Exception("La longueur du dépôt doit être inférieur à 50 caractères");
            }
            //Le médicament doit exister dans la bdd
            if(MedicamentDao.rechercher(depotLegal) == null) {
                throw new Exception("Aucun médicament ne correspond à ce dépôt ");
            }
            //Longueur d'une référence dans la bdd : 5 caractères max
            if(reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //La visite doit exister dans la bdd
            if(VisiteDao.rechercher(reference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            //le stock de ce médicament doit exister pour être offert
            if(StockDao.rechercher(depotLegal,VisiteDao.rechercher(reference).getUnVisiteur().getMatricule()) == null) {
                throw new Exception("Il n'y a pas de stock correspondant à ce médicament");
            }
            //Le stock du médoc doit être supérieur à la quantité offerte
            if(StockDao.rechercher(depotLegal, VisiteDao.rechercher(reference).getUnVisiteur().getMatricule()).getQteStock() < uneQuantiteOfferte) {
                throw new Exception("Votre stock de ce médicament est insuffisant");
            }
            unMedicament = MedicamentDao.rechercher(depotLegal);
            uneVisite = VisiteDao.rechercher(reference);
            unOffrir = new Offrir(unMedicament, uneVisite, uneQuantiteOfferte);
            result = OffrirDao.soustraireStock(unOffrir);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param depotLegal le depot du medicament
     * @param reference la reference de la visite
     * @return 0 si échec, 1 si réussi
     */
    public int supprimerOffre(String depotLegal, String reference) {
        int result = 0;

        try {
            //Les champs ne peuvent pas être null
            if(depotLegal == null || reference == null) {
                throw new Exception("Tous les champs sont obligatoires !");
            }
            //Longueur du dépôt dans la bdd : 50 caractères max
            if(depotLegal.length() > 50) {
                throw new Exception("La longueur du dépôt doit être inférieur à 50 caractères");
            }
            //Le médicament doit exister dans la bdd
            if(MedicamentDao.rechercher(depotLegal) == null) {
                throw new Exception("Aucun médicament ne correspond à ce dépôt ");
            }
            //Longueur d'une référence dans la bdd : 5 caractères max
            if(reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //La visite doit exister dans la bdd
            if(VisiteDao.rechercher(reference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            result = OffrirDao.supprimer(depotLegal, reference);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param reference la reference d'une visite
     * @return une collection d'offre correspondant àa la visite
     */
    public ArrayList<Offrir> rechercherOffreVisite(String reference) {
        ArrayList<Offrir> colOffreVisite = new ArrayList<Offrir>();

        try {
            //Les champs ne peuvent pas être null
            if(reference == null) {
                throw new Exception("La reference est obligatoire!");
            }
            //Longueur d'une référence dans la bdd : 5 caractères max
            if(reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //La visite doit exister dans la bdd
            if(VisiteDao.rechercher(reference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            colOffreVisite = OffrirDao.rechercherOffreVisite(reference);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return colOffreVisite;
    }
}
