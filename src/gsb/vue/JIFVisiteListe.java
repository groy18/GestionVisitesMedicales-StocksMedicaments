package gsb.vue;

import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;
import gsb.service.VisiteService;
import gsb.utils.ValidationUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @author womain
 * 02/11/2019
 *
 * Fenêtre interne de consultation des visites
 */
public class JIFVisiteListe extends JInternalFrame implements ActionListener, MouseListener {

    //Déclaration des JPanel
    protected JPanel p;
    protected JPanel pRecherche;
    protected JPanel pRechercheTexte;
    protected JPanel pRechercheTexte1;
    protected JPanel pRechercheBoutons;
    protected JPanel pErreur;
    protected JPanel pDetail;
    protected JPanel pErreurVisite;

    //Déclaration des composants du tableau
    protected JScrollPane scrollPane;
    protected JTable table;

    //Déclaration des JTextField
    protected JTextField JTDateVisite;
    protected JTextField JTMatriVisite;
    protected JTextField JTRefVisite;

    //Déclaration des labels
    protected JLabel JLDate;
    protected JLabel JLMatri;
    protected JLabel JLRef;
    protected JLabel JLErreurRecherche;
    protected JLabel JLErreurVisite;

    //Déclaration des boutons
    protected JButton JBRechercher;
    protected JButton JBDetail;

    //Variables pour le tableau
    private String[][] data;
    private String[] columnNames;
    private DefaultTableModel model;

    private VisiteService uneVisiteService = new VisiteService();

    protected MenuPrincipal fenetreContainer;

    public JIFVisiteListe(MenuPrincipal uneFenetreContainer) {
        super();

        fenetreContainer = uneFenetreContainer;

        //Instanciation des panneaux avec ou sans grille
        p = new JPanel();
        p.setSize(1100, 750);
        pRecherche = new JPanel(new GridLayout(2,1));
        pRechercheTexte = new JPanel(new GridLayout(1,2));
        pRechercheTexte1 = new JPanel(new GridLayout(2,2,5, 5));
        pRechercheBoutons = new JPanel();
        pErreur = new JPanel();
        pDetail = new JPanel(new GridLayout(1,3,5,5));
        pErreurVisite = new JPanel();
        pErreurVisite.setPreferredSize(new Dimension(1000,20));

        //Instanciation des JTexField
        JTDateVisite = new JTextField(10);
        JTDateVisite.addMouseListener(this);
        JTMatriVisite = new JTextField(10);
        JTMatriVisite.addMouseListener(this);
        JTRefVisite = new JTextField(10);

        //Instanciation des labels
        JLDate = new JLabel("Date");
        JLMatri = new JLabel("Matricule");
        JLRef = new JLabel("Référence");
        JLErreurRecherche = new JLabel("");
        JLErreurRecherche.setForeground(new Color(255,0,0));
        JLErreurVisite = new JLabel("");
        JLErreurVisite.setForeground(new Color(255,0,0));

        //Instanciation des événements
        JBRechercher = new JButton("Rechercher");
        JBRechercher.addActionListener(this); //Ecouteur d'événements
        JBDetail = new JButton("Détail");
        JBDetail.addActionListener(this); //Ecouteur d'événements

        //Variables pour le tableau
        columnNames = new String[]{"Référence", "Commentaire", "Code Médecin", "Lieu"}; //Colonnes du tableau
        model = new DefaultTableModel(columnNames, 0); //Modèle de données du tableau
        table = new JTable(model); //Ajout des données dans le tableau
        table.addMouseListener(this); //Ecouteur de la souris
        scrollPane = new JScrollPane(table); //Ajout du tableau avec barre de défilement
        scrollPane.setPreferredSize(new Dimension(1100, 400));

        //Ajout des éléments sur les sous-sous-panneaux de recherche
        pRechercheTexte1.add(JLDate);
        pRechercheTexte1.add(JTDateVisite);
        pRechercheTexte1.add(JLMatri);
        pRechercheTexte1.add(JTMatriVisite);

        //Ajout des éléments dans le panneau erreur
        pErreur.add(JLErreurRecherche);

        //Ajout des éléments dans le sous-sous panneau de recherche
        pRechercheBoutons.add(JBRechercher);

        //Ajout des éléments dans le sous panneau de recherche
        pRechercheTexte.add(pRechercheTexte1);
        pRechercheTexte.add(pRechercheBoutons);

        //AJout des éléments dans le panneau de recherche
        pRecherche.add(pRechercheTexte);
        pRecherche.add(pErreur);

        //Ajout des éléments sur le panneau de détail
        pDetail.add(JLRef);
        pDetail.add(JTRefVisite);
        pDetail.add(JBDetail);

        pErreurVisite.add(JLErreurVisite);

        //Ajouts au panbeau principal
        p.add(pRecherche);
        p.add(scrollPane);
        p.add(pDetail);
        p.add(pErreurVisite);

        //Ajout du panneau principal à la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    //Evenements
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //Recherche des visites
        if(source == JBRechercher) {
            model.setRowCount(0); //Vide les lignes du tableau

            ArrayList<Visite> colVisiteDateRef;
            String date = JTDateVisite.getText().toString();
            String matricule = JTMatriVisite.getText().toString();

            try {
                //Les champs ne peuvent pas être null
                if (date.equals("") || matricule.equals("")) {
                    throw new Exception("Tous les champs sont obligatoires");
                }
                //La date doit être au format dd/MM/yyyy
                if (!ValidationUtils.isDateValide(date)) {
                    throw new Exception("La date doit être au format dd/MM/yyyy");
                }
                //Une visite correspondante à la référence doit exister
                if (VisiteurDao.rechercher(matricule) == null) {
                    throw new Exception("Le visiteur correspondant à ce matricule n'existe pas");
                }
                //La référence ne peut pas dépasser 4 caracteres
                if (matricule.length() > 4) {
                    throw new Exception("Le matricule ne peut pas dépasser 5 caractères");
                }
                //On vérifie qu'une visite existe pour cette date
                if(uneVisiteService.rechercheVisiteDateMat(date, matricule).size() < 1) {
                    throw new Exception("Pas de visite à cette date");
                }
                colVisiteDateRef = uneVisiteService.rechercheVisiteDateMat(date, matricule);
                for(Visite uneVisite : colVisiteDateRef) {
                    String[] visite = {uneVisite.getReference(), uneVisite.getUnCommentaire(),uneVisite.getUnMedecin().getCodeMed(), uneVisite.getUnMedecin().getLaLocalite().getVille()};
                    model.addRow(visite); //Ajoute une visite dans le tableau
                }
            }
            catch (Exception erreur) {
                System.out.println(erreur.getMessage());
                JLErreurRecherche.setText(erreur.getMessage());
            }
        }
        //Ouvre la fenêtre du récapitulatif
        if(source == JBDetail) {
            String reference = JTRefVisite.getText().toString();
            try {
                //La référence ne peut pas être vide
                if(reference.equals("")) {
                    throw new Exception("La référence est obligatoire");
                }
                //La référence ne peut pas dépasser 5 caractères
                if(reference.length() > 5) {
                    throw new Exception("La référence ne peut pas dépasser 5 caractères");
                }
                //La visite correpondant à la référence doit exister
                if(uneVisiteService.rechercherVisite(reference) == null) {
                    throw new Exception("Pas de visite correspondant à cette référence");
                }
                Visite uneVisite = uneVisiteService.rechercherVisite(reference);
                fenetreContainer.ouvrirFenetre(new JIFVisiteRecap(uneVisite));
            }
            catch (Exception er) {
                JLErreurVisite.setText(er.getMessage());
            }
        }
    }

    //Ecouteur au clic de la souris
    @Override
    public void mouseClicked(MouseEvent e) {
        Object mouse = e.getSource();
        //AJoute la référence de la visite correspondant au clic dans le tableau
        if(mouse == table) {
            int ligne = table.getSelectedRow(); //Récupère la ligne du tableau
            String ref = table.getModel().getValueAt(ligne, 0).toString(); //Récupère la référence de la ligne
            JTRefVisite.setText(ref); //Initialise le JTextfield sur la référence
        }
        //Réinialise le label d'erreur au clic dans un des JTextfield
        if(mouse == JTDateVisite || mouse == JTMatriVisite) {
            JLErreurRecherche.setText("");
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
