package gsb.service;

import gsb.modele.Medecin;
import gsb.modele.Visite;
import gsb.modele.Visiteur;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import gsb.utils.ValidationUtils;

import java.util.ArrayList;

/**
 * @author womain
 *
 * Créée 26/10/2019
 */
public class VisiteService {

    /**
     *
     * @param uneReference la référence d'une visite
     * @return un objet Visite ou null
     */
    public Visite rechercherVisite(String uneReference) {
        Visite uneVisite = null;
        try {
            //La référence entrée en paramètre ne peut pas être nulle
            if(uneReference == null) {
                throw new Exception("Référence de la visite obligatoire");
            }
            //La référence doit avoir 5 caractères
            if (uneReference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //Une visite correspondante à la référence doit exister
            if(VisiteDao.rechercher(uneReference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            uneVisite = VisiteDao.rechercher(uneReference);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return uneVisite;
    }

    /**
     *
     * @param reference chaine de caracteres
     * @param date chaine de caracteres
     * @param commentaire chaine de caracteres
     * @param matricule chaine de caracteres
     * @param codeMedecin chaine de caracteres
     * @return
     */
    public int creerVisite(String reference, String date, String commentaire, String matricule, String codeMedecin) {
        int result = 0;
        Visite uneVisite;
        Visiteur unVisiteur;
        Medecin unMedecin;

        try {
            //Les champs ne peuvent pas être vides excepté le champ commentaire
            if(reference == null || date == null || matricule == null || codeMedecin == null) {
                throw new Exception("Tous les champs sont obligatoires sauf le commentaire");
            }
            //La référence doit avoir 5 caractères
            if(reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //La visite ne doit pas déjà existé
            if(VisiteDao.rechercher(reference) != null) {
                throw new Exception("Une visite avec cette référence existe déjà");
            }
            //La date doit être au format dd/MM/yyyy
            if(!ValidationUtils.isDateValide(date)){
                throw new Exception("La date doit être au format dd/MM/yyyy");
            }
            //La matricule doit être de 4 caractères
            if(matricule.length() > 4) {
                throw new Exception("Le matricule visiteur ne peut pas dépasser 4 caractères");
            }
            //Le visiteur doit exister dans la base
            if(VisiteurDao.rechercher(matricule) == null) {
                throw new Exception("Le visiteur possédant cette référence n'existe pas");
            }
            //Le code du médecin doit être de 4 caractères
            if(codeMedecin.length() > 4) {
                throw new Exception("Le code du médecin ne peut pas dépasser 4 caractères");
            }
            //Le médecin doit exister dans la base
            if(MedecinDao.rechercher(codeMedecin) == null) {
                throw new Exception("Le médecin possédant ce code n'existe pas");
            }
            unMedecin = MedecinDao.rechercher(codeMedecin);
            unVisiteur = VisiteurDao.rechercher(matricule);
            uneVisite = new Visite(reference, date, commentaire, unVisiteur, unMedecin);
            result = VisiteDao.creer(uneVisite);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    /**
     *
     * @param uneReference la référence d'une visite
     * @return 0 si echec ou 1 si réussite
     */
    public int supprimerVisite(String uneReference) {
        int result = 0;

        try {
            //La référence entrée en paramètre ne peut pas être nulle
            if(uneReference == null) {
                throw new Exception("Référence de la visite obligatoire");
            }
            //La référence doit avoir 5 caractères
            if (uneReference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //Une visite correspondante à la référence doit exister
            if(VisiteDao.rechercher(uneReference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            result = VisiteDao.supprimer(uneReference);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @param uneDate chaine de caracteres
     * @param unMatricule chaine de caracteres
     * @return une collection de Visite
     */
    public ArrayList<Visite> rechercheVisiteDateMat(String uneDate, String unMatricule) {
        ArrayList<Visite> colVisiteDateRef = new ArrayList<Visite>();

        try {
            //Les champs ne peuvent pas être null
            if (uneDate == null || unMatricule == null) {
                throw new Exception("Tous les champs sont obligatoires");
            }
            //La référence ne peut pas dépasser 4 caracteres
            if(unMatricule.length() > 4) {
                throw new Exception("Le matricule ne peut pas dépasser 5 caractères");
            }
            //Une visite correspondante à la référence doit exister
            if(VisiteurDao.rechercher(unMatricule) == null) {
                throw new Exception("Le visiteur correspondant à ce matricule n'existe pas");
            }
            //La date doit être au format dd/MM/yyyy
            if(!ValidationUtils.isDateValide(uneDate)){
                throw new Exception("La date doit être au format dd/MM/yyyy");
            }
            colVisiteDateRef = VisiteDao.rechercheDateMat(uneDate, unMatricule);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return colVisiteDateRef;
    }

    /**
     *
     * @param commentaire le commentaire d'une visite
     * @param reference la reference
     * @return 0 si echec, 1 si réussie
     */
    public int updateVisiteCommentaire(String commentaire, String reference) {
        int result = 0;

        try {
            //La référence entrée en paramètre ne peut pas être nulle
            if(reference == null) {
                throw new Exception("Référence de la visite obligatoire");
            }
            //La référence doit avoir 5 caractères
            if (reference.length() > 5) {
                throw new Exception("La référence ne peut pas dépasser 5 caractères");
            }
            //Une visite correspondante à la référence doit exister
            if(VisiteDao.rechercher(reference) == null) {
                throw new Exception("La visite correspondant à cette référence n'existe pas");
            }
            if(commentaire.length() > 255) {
                throw new Exception("Le commentaire ne peut pas dépasser 255 caractères");
            }
            result = VisiteDao.updateCommentaire(commentaire, reference);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
