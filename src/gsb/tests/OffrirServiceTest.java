package gsb.tests;

import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.service.OffrirService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author womain
 *
 */
class OffrirServiceTest {

    private OffrirService uneOffreService;
    private Offrir uneOffre;
    private ArrayList<Offrir> colOffreVisite;

    @BeforeEach
    void setUp() {

        uneOffreService = new OffrirService();
        colOffreVisite = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        if(uneOffreService.rechercherOffrir("3MYC7", "v0001") != null) {
            uneOffreService.supprimerOffre("3MYC7", "v0001");
        }
    }

    @Test
    void rechercherOffrirDepotNull() {
        System.out.println("---------------------------------- rechercherOffrirDepotNull -----------------------------------");
        Assertions.assertNull(uneOffreService.rechercherOffrir(null, "v0001"), "Résultat null car Depot null");
    }

    @Test
    void rechercherOffrirReferenceNull() {
        System.out.println("---------------------------------- rechercherOffrirReferenceNull -----------------------------------");
        Assertions.assertNull(uneOffreService.rechercherOffrir("3MYC7", null), "Résultat null car reference null");
    }

    @Test
    void rechercherOffrirDepot50carac() {
        System.out.println("---------------------------------- rechercherOffrirDepot50carac -----------------------------------");
        Assertions.assertNull(uneOffreService.rechercherOffrir("3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3", "v0001"), "Résultat null car depot trop long");
    }

    @Test
    void rechercherOffrirDepotKO() {
        System.out.println("---------------------------------- rechercherOffrirDepotKO -----------------------------------");
        Assertions.assertNull(uneOffreService.rechercherOffrir("AAAAA", "v0001"), "Résultat null car le dépot n'existe pas dans la base");
    }

    @Test
    void rechercherOffrirReference6carac() {
        System.out.println("---------------------------------- rechercherOffrirReference6carac -----------------------------------");
        Assertions.assertNull(uneOffreService.rechercherOffrir("3MYC7", "v00066"), "Résultat null car la reference est trop longue");
    }

    @Test
    void rechercherOffrirReferenceKO() {
        System.out.println("---------------------------------- rechercherOffrirReferenceKO -----------------------------------");
        Assertions.assertNull(uneOffreService.rechercherOffrir("3MYC7", "v7777"), "Résultat null car la reference n'existe pas");
    }

    @Test
    void rechercherOffrirOK() {
        System.out.println("---------------------------------- rechercherOffrirOK -----------------------------------");
        uneOffreService.creerOffrir("3MYC7", "v0001", 4);
        Assertions.assertNotNull(uneOffreService.rechercherOffrir("3MYC7", "v0001"), "Résultat OK");
    }

    @Test
    void creerOffrirDepotNull() {
        System.out.println("---------------------------------- creerOffrirDepotNull -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir(null,"v0001", 4), "Résultat null car depot null");
    }

    @Test
    void creerOffrirReferenceNull() {
        System.out.println("---------------------------------- creerOffrirReferenceNull -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("3MYC7",null, 4), "Résultat null car reference null");
    }

    @Test
    void creerOffrirQuantiteInf1() {
        System.out.println("---------------------------------- creerOffrirQuantitéInf1 -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("3MYC7","v0001", 0), "Résultat null car quantité inférieur à 1");
    }

    @Test
    void creerOffrirDepot51Carac() {
        System.out.println("---------------------------------- creerOffrirDepot51Carac -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3","v0001", 4), "Résultat null car depot trop long");
    }

    @Test
    void creerOffrirDepotKO() {
        System.out.println("---------------------------------- creerOffrirDepotKO -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("AAAAAA","v0001", 4), "Résultat null car le depot n'existe pas dans la base");
    }

    @Test
    void creerOffrirRef6Carac() {
        System.out.println("---------------------------------- creerOffrirRef6Carac -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("3MYC7","v00066", 4), "Résultat null car la reference est trop longue");
    }

    @Test
    void creerOffrirRefKO() {
        System.out.println("---------------------------------- creerOffrirRefKO -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("3MYC7","v6666", 4), "Résultat null car la reference n'existe pas");
    }

    @Test
    void creerOffrirQuantiteKO() {
        System.out.println("---------------------------------- creerOffrirQuantiteKO -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.creerOffrir("3MYC7","v0001", 1000), "Résultat null car quantité offerte > quantité possédée");
    }

    @Test
    void creerOffrirOK() {
        System.out.println("---------------------------------- creerOffrirOK -----------------------------------");
        Assertions.assertEquals(1,uneOffreService.creerOffrir("3MYC7","v0001", 4), "Résultat OK");
    }

    @Test
    void soustraireStockDepotNull() {
        System.out.println("---------------------------------- soustraireStockDepotNull -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock(null,"v0001", 4), "Résultat null car depot null");
    }

    @Test
    void soustraireStockReferenceNull() {
        System.out.println("---------------------------------- soustraireStockReferenceNull -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("3MYC7",null, 4), "Résultat null car reference null");
    }

    @Test
    void soustraireStockQuantiteInf1() {
        System.out.println("---------------------------------- soustraireStockQuantiteInf1 -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("3MYC7","v0001", 0), "Résultat null car quantité inférieur à 1");
    }

    @Test
    void soustraireStockDepot51Carac() {
        System.out.println("---------------------------------- soustraireStockDepot51Carac -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3","v0001", 4), "Résultat null car depot trop long");
    }

    @Test
    void soustraireStockDepotKO() {
        System.out.println("---------------------------------- soustraireStockDepotKO -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("AAAAAA","v0001", 4), "Résultat null car le depot n'existe pas dans la base");
    }

    @Test
    void soustraireStockRef6Carac() {
        System.out.println("---------------------------------- soustraireStockRef6Carac -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("3MYC7","v00066", 4), "Résultat null car la reference est trop longue");
    }

    @Test
    void soustraireStockRefKO() {
        System.out.println("---------------------------------- soustraireStockRefKO -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("3MYC7","v6666", 4), "Résultat null car la reference n'existe pas");
    }

    @Test
    void soustraireStockQuantiteKO() {
        System.out.println("---------------------------------- soustraireStockQuantiteKO -----------------------------------");
        Assertions.assertEquals(0,uneOffreService.soustraireStock("3MYC7","v0001", 1000), "Résultat null car quantité offerte > quantité possédée");
    }

    @Test
    void soustraireStockOK() {
        System.out.println("---------------------------------- soustraireStockOK -----------------------------------");
        Assertions.assertEquals(1,uneOffreService.soustraireStock("3MYC7","v0001", 4), "Résultat OK");
    }

    @Test
    void supprimerOffrirDepotNull() {
        System.out.println("---------------------------------- supprimerOffrirDepotNull -----------------------------------");
        Assertions.assertEquals(0, uneOffreService.supprimerOffre(null, "v0001"), "Résultat null car Depot null");
    }

    @Test
    void supprimerOffrirReferenceNull() {
        System.out.println("---------------------------------- supprimerOffrirReferenceNull -----------------------------------");
        Assertions.assertEquals(0, uneOffreService.supprimerOffre("3MYC7", null), "Résultat null car reference null");
    }

    @Test
    void supprimerOffrirDepot50carac() {
        System.out.println("---------------------------------- supprimerOffrirDepot50carac -----------------------------------");
        Assertions.assertEquals(0, uneOffreService.supprimerOffre("3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3MYC7YYYYY3", "v0001"), "Résultat null car depot trop long");
    }

    @Test
    void supprimerOffrirDepotKO() {
        System.out.println("---------------------------------- supprimerOffrirDepotKO -----------------------------------");
        Assertions.assertEquals(0, uneOffreService.supprimerOffre("AAAAA", "v0001"), "Résultat null car le dépot n'existe pas dans la base");
    }

    @Test
    void supprimerOffrirReference6carac() {
        System.out.println("---------------------------------- supprimerOffrirReference6carac -----------------------------------");
        Assertions.assertEquals(0, uneOffreService.supprimerOffre("3MYC7", "v00066"), "Résultat null car la reference est trop longue");
    }

    @Test
    void supprimerOffrirReferenceKO() {
        System.out.println("---------------------------------- supprimerOffrirReferenceKO -----------------------------------");
        Assertions.assertEquals(0, uneOffreService.supprimerOffre("3MYC7", "v7777"), "Résultat null car la reference n'existe pas");
    }

    @Test
    void supprimerOffrirOK() {
        System.out.println("---------------------------------- supprimerOffrirOK -----------------------------------");
        uneOffreService.creerOffrir("3MYC7", "v0001", 4);
        Assertions.assertEquals(1, uneOffreService.supprimerOffre("3MYC7", "v0001"), "Résultat OK");
    }

    @Test
    void rechercherOffreVisiteReferenceNull() {
        System.out.println("---------------------------------- rechercherOffreVisiteReferenceNull -----------------------------------");
        Assertions.assertEquals(colOffreVisite,uneOffreService.rechercherOffreVisite(null), "Résultat vide car reference null");
    }

    @Test
    void rechercheOffreVisiteReference6Carac() {
        System.out.println("--------------------------- rechercheOffreVisiteReference6Carac ---------------------------------");
        Assertions.assertEquals(colOffreVisite, uneOffreService.rechercherOffreVisite("v00001"), "Résultat vide car mat trop long");
    }

    @Test
    void rechercheOffreVisiteReferenceKO() {
        System.out.println("--------------------------- rechercheOffreVisiteReferenceKO ---------------------------------");
        Assertions.assertEquals(colOffreVisite, uneOffreService.rechercherOffreVisite("v0066"), "Résultat vide car ref KO");
    }

    @Test
    void rechercheOffreVisiteOK() {
        System.out.println("--------------------------- rechercheOffreVisiteOK ---------------------------------");
        uneOffreService.creerOffrir("3MYC7", "v0001", 4);
        int nbElementsAttendus = 1;
        Assertions.assertEquals(nbElementsAttendus, uneOffreService.rechercherOffreVisite("v0001").size(), "Résultat 1 car une offre lors de cette visite");
    }





}