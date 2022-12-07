/*
 * Cr�� le 21/11/2019
*/

package gsb.tests;
import gsb.modele.*;
import gsb.service.StockService;
import gsb.service.VisiteService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * @author Gwendal
 * 21/11/2019
 */

public class StockServiceTest {
	 
	 private StockService unStockService;
	 private Stock unStock;
	 
	 @BeforeEach
	    void setUp() {
	        unStockService = new StockService();
	    }

	    @AfterEach
	    void tearDown() {
	        
	    }
	    
	    @Test
	    void ajoutStockAllNull() {
	        System.out.println("--------------------------- ajoutStockAllNull ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock(null, null, 0), "R�sultat 0 car param�tres null");
	    }
	    
	    @Test
	    void ajoutStockDepotLegalNull() {
	        System.out.println("--------------------------- ajoutStockDepotLegalNull ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock(null, "a131", 15), "R�sultat 0 car depotLegal null");
	    }
	    
	    @Test
	    void ajoutStockDepotLegalKO() {
	        System.out.println("--------------------------- ajoutStockDepotLegalKO ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock("AAAA", "a131", 15), "Résultat 0 car le depot legal n'existe pas");
	    }
	    
	    @Test
	    void ajoutStockMatriculeNull() {
	        System.out.println("--------------------------- ajoutStockMatriculeNull ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock("3MYC7", null, 15), "R�sultat 0 car matricule null");
	    }
	    
	    @Test
	    void ajoutStockMatriculeKO() {
	        System.out.println("--------------------------- ajoutStockMatriculeKO ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock("3MYC7", "AAAA", 15), "R�sultat 0 car le matricule visiteur n'existe pas");
	    }
	    
	    @Test
	    void ajoutStockQteStockNull() {
	        System.out.println("--------------------------- ajoutStockQteStockNull ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock("3MYC7", "a131", 0), "R�sultat 0 car qteStock null");
	    }
	    
	    @Test
	    void ajoutStockQteStockNegative() {
	        System.out.println("--------------------------- ajoutStockQteStockNegative ---------------------------------");
	        Assertions.assertEquals(0, unStockService.ajoutStock("3MYC7", "a131", -15), "R�sultat 0 car qteStock n�gative");
	    }

	    @Test
		void rechercherStockAllNull() {
			System.out.println("--------------------------- rechercherStockAllNull ---------------------------------");
			Assertions.assertNull(unStockService.rechercherStock(null,null), "Résultat null car tous les paramètres sont nuls");
		}

		@Test
		void rechercherStockDepotNull() {
			System.out.println("--------------------------- rechercherStockDepotNull ---------------------------------");
			Assertions.assertNull(unStockService.rechercherStock(null,"b59"), "Résultat null car depot légal null");
		}

		@Test
		void rechercherStockMatriNull() {
			System.out.println("--------------------------- rechercherStockMatriNull ---------------------------------");
			Assertions.assertNull(unStockService.rechercherStock("3MYC7",null), "Résultat null car depot matricule null");
		}

		@Test
		void rechercherStockMatri5Carac() {
			System.out.println("--------------------------- rechercherStockMatri5Carac ---------------------------------");
			Assertions.assertNull(unStockService.rechercherStock("3MYC7","AAAAA"), "Résultat null car depot matricule trop long");
		}

	@Test
	void rechercherStockMatriKO() {
		System.out.println("--------------------------- rechercherStockMatriKO ---------------------------------");
		Assertions.assertNull(unStockService.rechercherStock("3MYC7","BBB"), "Résultat null car pas de visiteur correspondant au matricule");
	}

	@Test
	void rechercherStockDepot51Carac() {
		System.out.println("--------------------------- rechercherStockDepot51Carac ---------------------------------");
		Assertions.assertNull(unStockService.rechercherStock("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA","b59"), "Résultat null car depot legal trop long");
	}

	@Test
	void rechercherStockDepotKO() {
		System.out.println("--------------------------- rechercherStockDepotKO ---------------------------------");
		Assertions.assertNull(unStockService.rechercherStock("BBBBBAAAA","b59"), "Résultat null car pas de médicament correspondant au dépot légal");
	}

	@Test
	void rechercherStockOK() {
		System.out.println("--------------------------- rechercherStockOK ---------------------------------");
		Assertions.assertNotNull(unStockService.rechercherStock("3MYC7","b59"), "Résultat non null");
	}
	    
}
