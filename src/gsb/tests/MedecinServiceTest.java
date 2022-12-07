package gsb.tests;

import gsb.modele.Medecin;
import gsb.service.MedecinService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de test de MedecinService
 *
 * @author womain
 * 
 * 28/10/2019
 */
class MedecinServiceTest {

    private MedecinService unMedecinService;

    @BeforeEach
    void setUp() {
        unMedecinService = new MedecinService();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void rechercherMedecinCodeNull() {
        System.out.println("--------------------------------- rechercherMedecinCodeNull ------------------------------");
        Assertions.assertNull(unMedecinService.rechercherMedecin(null), "Résultat null car code null");
    }

    @Test
    void rechercherMedecinCode5Carac() {
        System.out.println("--------------------------------- rechercherMedecinCode5Carac ------------------------------");
        Assertions.assertNull(unMedecinService.rechercherMedecin("AAAAA"), "Résultat null car code trop long");
    }

    @Test
    void rechercherMedecinCodeKo() {
        System.out.println("--------------------------------- rechercherMedecinCodeKo ------------------------------");
        Assertions.assertNull(unMedecinService.rechercherMedecin("BBBB"), "Résultat null car code non existant dans la BDD");
    }

    @Test
    void rechercherMedecinCodeOk() {
        System.out.println("--------------------------------- rechercherMedecinCodeOk ------------------------------");
        Assertions.assertNotNull(unMedecinService.rechercherMedecin("m001"), "Résultat non null car code ok");

        Medecin unMedecin = unMedecinService.rechercherMedecin("m001");
        System.out.println("Code Médecin : " + unMedecin.getCodeMed());
        System.out.println("Nom : " + unMedecin.getNom());
        System.out.println("Prénom : " + unMedecin.getPrenom());
        System.out.println("Adresse : " + unMedecin.getAdresse());
        System.out.println("Ville : " + unMedecin.getLaLocalite().getVille());
        System.out.println("Téléphone : " + unMedecin.getTelephone());
        System.out.println("Potentiel : " + unMedecin.getPotentiel());
        System.out.println("Spécialité : " + unMedecin.getSpecialite());
    }

}