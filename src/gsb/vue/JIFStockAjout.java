package gsb.vue;

import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.VisiteurDao;
import gsb.service.StockService;
import gsb.utils.ValidationUtils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Gwendal
 *
 * Fenï¿½tre d'ajout d'un mï¿½dicament
 *
 * 05/12/2019
 */

public class JIFStockAjout extends JIFStock implements ActionListener, MouseListener {

	//Dï¿½claration des JPanels
	protected JPanel p;
	protected JPanel pSaisie;
	protected JPanel pErreur;
	
	//Dï¿½claration des JLabels
	protected JLabel JLMatricule;
	protected JLabel JLDepotLegal;
	protected JLabel JLQteStock;
	protected JLabel JLErreurAjout;
	
	//Dï¿½claration des JTextFields
	protected JTextField JTDepotLegal;
	protected JTextField JTMatricule;
	protected JTextField JTQteStock;
	
	//Dï¿½claration des JButtons
    private JButton JBValider;
    private JButton JBAnnuler;    
    
    private StockService unStockService = new StockService();

    public JIFStockAjout() {
        super();
        
        //Instanciation des JPanel
        p = new JPanel();
        pSaisie = new JPanel(new GridLayout(4, 2, 5, 5));
        pErreur = new JPanel();
        pErreur.setPreferredSize(new Dimension(1000, 20));
        pBoutons = new JPanel(new GridLayout(1,2,5,5));
        
        //Instanciation des JTextField
        JTDepotLegal = new JTextField(20);
		JTDepotLegal.setMaximumSize(JTDepotLegal.getPreferredSize());
		JTMatricule = new JTextField(20);
		JTMatricule.setMaximumSize(JTMatricule.getPreferredSize());
		JTQteStock = new JTextField(20);
		JTQteStock.setMaximumSize(JTMatricule.getPreferredSize());
        
		//Instanciation des JLabels
        JLMatricule = new JLabel("Matricule");
        JLDepotLegal = new JLabel("Dépot Légal");
        JLQteStock = new JLabel("Quantité");
        JLErreurAjout = new JLabel("");
        JLErreurAjout.setForeground(new Color(255,0,0));
		
        //Instanciation des JButtons
        JBValider = new JButton("Valider");
        JBValider.addActionListener(this);
        JBAnnuler = new JButton("Annuler");
        JBAnnuler.addActionListener(this);
        JBAnnuler.addMouseListener(this);
        
      //Ajout ï¿½couteur de la souris sur les JTexfields
        JTMatricule.addMouseListener(this);
        JTDepotLegal.addMouseListener(this);
        JTQteStock.addMouseListener(this);
        
        
        //Ajout des ï¿½lï¿½ments sur le panneau saisie
        pSaisie.add(JLMatricule);
        pSaisie.add(JTMatricule);
        pSaisie.add(JLDepotLegal);
        pSaisie.add(JTDepotLegal);
        pSaisie.add(JLQteStock);
        pSaisie.add(JTQteStock);
        
        //Ajout de ï¿½lï¿½ments sur le panneau boutons
        pBoutons.add(JBValider);
        pBoutons.add(JBAnnuler);
        
      //Ajout des ï¿½lï¿½ments dans le panneau erreur
        pErreur.add(JLErreurAjout);
        
        //Ajout des ï¿½lï¿½ments sur le panneau principal
        p.add(pSaisie);
        p.add(pErreur);
        p.add(pBoutons);

      //Ajout du panneau principal ï¿½ la fenï¿½tre
     	Container contentPane = getContentPane();
     	contentPane.add(p);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Ajout d'un stock");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == JBValider) {
        	String qte = JTQteStock.getText();   	
        	String depotLegal = JTDepotLegal.getText();
        	String matricule = JTMatricule.getText();
        	int quantite = 0;
        	
        	
        	try {
                //Les champs ne peuvent pas ï¿½tre null
                if (matricule.equals("") || depotLegal.equals("") || qte.equals("")) {
                    throw new Exception("Tous les champs sont obligatoires");
                }
                //Un medicament correspondant au dï¿½pot lï¿½gal doit exister
                if (MedicamentDao.rechercher(depotLegal) == null) {
                    throw new Exception("Le medicament correspondant à ce dépot légal n'existe pas");
                }
              //Un visiteur correspondant au matricule doit exister
                if (VisiteurDao.rechercher(matricule) == null) {
                    throw new Exception("Le visiteur correspondant à ce matricule n'existe pas");
                }
              //Un visiteur correspondant au matricule doit exister
                
                if (!ValidationUtils.estUnEntier(qte)) {
                	throw new Exception("La quantité ajouté doit être un entier");
                }
                else {
                	quantite = Integer.parseInt(qte);
                }
                if (quantite <= 0) {
                    throw new Exception("On ne peut pas ajouter un stock inférieur ou égal à 0");
                }
                
                unStockService.ajoutStock(JTDepotLegal.getText().toString(),JTMatricule.getText().toString(), quantite);
                this.videTexte();
                JLErreurAjout.setText("Stock ajouté avec succès");
            }
            catch (Exception erreur) {
                System.out.println(erreur.getMessage());
                JLErreurAjout.setText(erreur.getMessage());
            }        
        	
        }
        if(source == JBAnnuler) {
            this.videTexte();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        Object mouse = e.getSource();
        //Rï¿½initialise le label d'erreur au clic dans un des JTextfield
        if(mouse == JTMatricule || mouse == JTDepotLegal || mouse == JTQteStock) {
            JLErreurAjout.setText("");
        }
        if(mouse == JBAnnuler) {
        	JTMatricule.setText("");
        	JTQteStock.setText("");
        	JTDepotLegal.setText("");
        	JLErreurAjout.setText("");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
