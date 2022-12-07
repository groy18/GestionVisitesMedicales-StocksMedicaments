package gsb.tests;

import gsb.modele.Visiteur;
import gsb.service.VisiteurService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author womain
 *
 * 03/11/2019
 */
class VisiteurServiceTest {

    private VisiteurService unVisiteurService;

    @BeforeEach
    void setUp() {
        unVisiteurService = new VisiteurService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void rechercherVisiteurMatriculeVide() {
        System.out.println("--------------------------- rechercherVisiteurMatriculeVide -------------------------------");
        Assertions.assertNull(unVisiteurService.rechercherVisiteur(null), "Résultat null");
    }

    @Test
    void rechercherVisiteurMatricule5Caracteres() {
        System.out.println("--------------------------- rechercherVisiteurMatricule5Caracteres -------------------------------");
        Assertions.assertNull(unVisiteurService.rechercherVisiteur("ABCDE"), "Résultat null");
    }

    @Test
    void rechercherVisiteurMatriculeKO() {
        System.out.println("--------------------------- rechercherVisiteurMatriculeKO -------------------------------");
        Assertions.assertNull(unVisiteurService.rechercherVisiteur("AAAA"), "Pas de visiteur correspondant donc résultat null");
    }

    @Test
    void rechercherVisiteurMatriculeOK() {
        System.out.println("--------------------------- rechercherVisiteurMatriculeOK -------------------------------");
        Assertions.assertNotNull(unVisiteurService.rechercherVisiteur("a131"), "Résultat non null car visiteur correspondant");

        Visiteur unVisiteur = unVisiteurService.rechercherVisiteur("a131");
        System.out.println("Matricule : " + unVisiteur.getMatricule());
        System.out.println("Nom : " + unVisiteur.getNom());
        System.out.println("Prénom : " + unVisiteur.getPrenom());
        System.out.println("Login : " + unVisiteur.getLogin());
        System.out.println("Mdp : " + unVisiteur.getMdp());
        System.out.println("Adresse : " + unVisiteur.getAdresse());
        System.out.println("Code Postal : " + unVisiteur.getCodePostal());
        System.out.println("Date entrée : " + unVisiteur.getDateEntrer());
        System.out.println("Prime : " + unVisiteur.getPrime());
        System.out.println("Unité : " + unVisiteur.getUnite().getNomUnite());
    }

}