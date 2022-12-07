package gsb.service;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;

/**
 * @author womain
 *
 * Créée 26/10/2019
 *
 * Modifié le 27/10/2019
 */
public class VisiteurService {

    /**
     *
     * @param matricule chaine de caracteres
     * @return un objet Visiteur ou null
     */
    public Visiteur rechercherVisiteur (String matricule) {
        Visiteur unVisiteur = null;

        try {
            //Le matricule ne doit pas être null
            if(matricule == null) {
                throw new Exception("Le matricule ne peut pas être vide");
            }
            //4 caracteres maximum pour le matricule du visiteur
            if(matricule.length() > 4) {
                throw new Exception("Le matricule ne peut pas dépasser 4 caractères");
            }
            //Le visiteur doit exister dans la base
            if(VisiteurDao.rechercher(matricule) == null) {
                throw new Exception("Il n'y a pas de visiteur correspondant à ce matricule");
            }
            unVisiteur = VisiteurDao.rechercher(matricule);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return unVisiteur;
    }
}
