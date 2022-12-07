package gsb.vue;

import gsb.modele.Stock;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JIFStock extends JInternalFrame {
	
	//Déclaration des JPanel
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	//Déclaration des JLabel
	 protected JLabel JLDepotLegal;
	 protected JLabel JLMatricule;
	 protected JLabel JLQteStock;
	 
	 //Déclaration des JTextField
	 protected JTextField JTDepotLegal;
	 protected JTextField JTMatricule;
	 protected JTextField JTQteStock;

	
	 public JIFStock() {
	     
		 //Instanciation des JPanel
		 p = new JPanel();
	     pTexte = new JPanel(new GridLayout(5,2,5,5));
	     pBoutons = new JPanel();
	     
	     //Instanciation des JLabel
	     JLDepotLegal = new JLabel("Depot Legal");
	     JLMatricule = new JLabel("Matricule");
	     JLQteStock = new JLabel("Quantité");
	     
	     //Instanciation des JTextField
	     JTDepotLegal = new JTextField(15);
	     JTMatricule = new JTextField(15);
	     JTQteStock = new JTextField(15);

	     //Ajout des éléments sur le panneau texte
	     pTexte.add(JLMatricule);
	     pTexte.add(JTMatricule);
	     pTexte.add(JLDepotLegal);
	     pTexte.add(JTDepotLegal);
	     pTexte.add(JLQteStock);
	     pTexte.add(JTQteStock);

	     //Ajout des éléments sur le panneau principal
	     p.add(pTexte);
	     p.add(pBoutons);

	     Container contentPane = getContentPane();
	     contentPane.add(p);
	  }
	 
	 public void videTexte() {
		 JTDepotLegal.setText("");	
		 JTMatricule.setText("");		 	        
	     JTQteStock.setText("");
	   }

}