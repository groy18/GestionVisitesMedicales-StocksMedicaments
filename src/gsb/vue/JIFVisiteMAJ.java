package gsb.vue;

import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.modele.dao.StockDao;
import gsb.modele.dao.VisiteDao;
import gsb.service.MedicamentService;
import gsb.service.OffrirService;
import gsb.service.StockService;
import gsb.service.VisiteService;
import gsb.utils.ValidationUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JIFVisiteMAJ extends JInternalFrame implements ActionListener, MouseListener {

    //Déclaration des JPanel
    protected JPanel p;
    protected JPanel pRechercheRef;
    protected JPanel pErreurRecherche;
    protected JPanel pBoutonsRecherche;
    protected JPanel pTexteVisite;
    protected JPanel pErreurCommentaire;
    protected JPanel pTexteOffre;
    protected JPanel pErreurOffre;
    protected JPanel pBoutonsModif;
    protected JPanel pBoutonsAjout;

    //Déclaration des JLabel
    protected JLabel JLReference;
    protected JLabel JLDate;
    protected JLabel JLCommentaire;
    protected JLabel JLVisiteur;
    protected JLabel JLMedecin;
    protected JLabel JLDepotLegal;
    protected JLabel JLQuantite;
    protected JLabel JLErreurRecherche;
    protected JLabel JLErreurOffre;
    protected JLabel JLErreurCommentaire;

    //Déclaration des JTextField
    protected JTextField JTReference;
    protected JTextField JTDate;
    protected JTextField JTCommentaire;
    protected JTextField JTVisiteur;
    protected JTextField JTMedecin;
    protected JTextField JTDepotLegal;
    protected JTextField JTQuantite;

    //Déclaration des boutons
    protected JButton JBRechercher;
    protected JButton JBModifier;
    protected JButton JBAJout;

    //Déclaration des variables
    private VisiteService uneVisiteService;
    private OffrirService uneOffreService;
    private MedicamentService unMedicamentService;
    private StockService unStockService;
    private Visite uneVisite;

    Container contentPane = getContentPane();

    public JIFVisiteMAJ() {

        //Instanciation des variables
        uneVisiteService = new VisiteService();
        uneOffreService = new OffrirService();
        unMedicamentService = new MedicamentService();
        unStockService = new StockService();

        //Instanciation des panneaux et de leur taille
        p = new JPanel();
        pRechercheRef = new JPanel(new GridLayout(1, 2));
        pErreurRecherche = new JPanel();
        pErreurRecherche.setPreferredSize(new Dimension(1000, 20));
        pBoutonsRecherche = new JPanel();
        pBoutonsRecherche.setPreferredSize(new Dimension(1000, 50));
        pTexteVisite = new JPanel(new GridLayout(4, 2, 5, 5));
        pErreurCommentaire = new JPanel();
        pErreurCommentaire.setPreferredSize(new Dimension(1000, 20));
        pTexteOffre = new JPanel(new GridLayout(2, 2, 5, 5));
        pErreurOffre = new JPanel();
        pErreurOffre.setPreferredSize(new Dimension(1000, 20));
        pBoutonsModif = new JPanel();
        pBoutonsModif.setPreferredSize(new Dimension(1000, 50));
        pBoutonsAjout = new JPanel();
        pBoutonsAjout.setPreferredSize(new Dimension(1000, 50));

        //Instanciation des JLabel et de leur couleur
        JLReference = new JLabel("Reférence");
        JLDate = new JLabel("Date");
        JLCommentaire = new JLabel("Commentaire");
        JLVisiteur = new JLabel("Matricule Visiteur");
        JLMedecin = new JLabel("Code Médecin");
        JLDepotLegal = new JLabel("Dépot Légal");
        JLQuantite = new JLabel("Quantité Offerte");
        JLErreurOffre = new JLabel();
        JLErreurOffre.setForeground(new Color(255, 0,0));
        JLErreurCommentaire = new JLabel();
        JLErreurCommentaire.setForeground(new Color(255, 0, 0));
        JLErreurRecherche = new JLabel();
        JLErreurRecherche.setForeground(new Color(255,0,0));

        //Instanciation des JTextfield et des écouteurs de la souris
        JTReference = new JTextField(15);
        JTReference.addMouseListener(this);
        JTCommentaire = new JTextField(15);
        JTCommentaire.addMouseListener(this);
        JTDate = new JTextField(15);
        JTDate.setEditable(false);
        JTVisiteur = new JTextField(15);
        JTVisiteur.setEditable(false);
        JTMedecin = new JTextField(15);
        JTMedecin.setEditable(false);
        JTDepotLegal = new JTextField(15);
        JTDepotLegal.addMouseListener(this);
        JTQuantite = new JTextField(15);
        JTQuantite.addMouseListener(this);


        //Instanciation des boutons
        JBModifier = new JButton("Modifier");
        JBModifier.addActionListener(this);
        JBRechercher = new JButton("Rechercher");
        JBRechercher.addActionListener(this);
        JBAJout = new JButton("Ajouter");
        JBAJout.addActionListener(this);

        //Ajout des éléments de recherche au panneau rechercheRef
        pRechercheRef.add(JLReference);
        pRechercheRef.add(JTReference);
        pErreurRecherche.add(JLErreurRecherche);
        pBoutonsRecherche.add(JBRechercher);

        //AJout des elements au panneau de la visite
        pTexteVisite.add(JLDate);
        pTexteVisite.add(JTDate);
        pTexteVisite.add(JLVisiteur);
        pTexteVisite.add(JTVisiteur);
        pTexteVisite.add(JLMedecin);
        pTexteVisite.add(JTMedecin);
        pTexteVisite.add(JLCommentaire);
        pTexteVisite.add(JTCommentaire);
        pTexteVisite.setVisible(false);

        //Ajout du label dans le panneau erreur du commentaire
        pErreurCommentaire.add(JLErreurCommentaire);
        pErreurCommentaire.setVisible(false);

        //Ajout du bouton d'ajout d'une offre
        pBoutonsAjout.add(JBAJout);
        pBoutonsAjout.setVisible(false);

        //Ajout des éléments dans le panneau d'ajout de l"offre
        pTexteOffre.add(JLDepotLegal);
        pTexteOffre.add(JTDepotLegal);
        pTexteOffre.add(JLQuantite);
        pTexteOffre.add(JTQuantite);
        pTexteOffre.setVisible(false);

        //AJjout du label dans le panneau erreur de l'offre
        pErreurOffre.add(JLErreurOffre);
        pErreurOffre.setVisible(false);

        //Ajout du bouton de modification de la visite
        pBoutonsModif.add(JBModifier);
        pBoutonsModif.setVisible(false);

        //Ajout des panneaux au panneau principal
        p.add(pRechercheRef);
        p.add(pErreurRecherche);
        p.add(pBoutonsRecherche);
        p.add(pTexteVisite);
        p.add(pErreurCommentaire);
        p.add(pBoutonsModif);
        p.add(pTexteOffre);
        p.add(pErreurOffre);
        p.add(pBoutonsAjout);

        //Ajout du panneau principal à la fenêtre;
        contentPane.add(p);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //Recherche de la visite
        if(source == JBRechercher) {
            String reference = JTReference.getText().toString();
            try {
                //La reférence de doit pas être vide
                if(reference.equals("")){
                    throw new Exception("La référence ne peut pas être vide");
                }
                //La référence ne doit pas dépasser 5 caractères
                if(reference.length() > 5) {
                    throw new Exception("La référence ne peut pas dépasser 5 caractères");
                }
                //Une visite doit correspondre à la reference
                if(uneVisiteService.rechercherVisite(reference) == null) {
                    throw new Exception("Aucune visite correspondant à cette référence");
                }
                uneVisite = uneVisiteService.rechercherVisite(reference);
                JTDate.setText(uneVisite.getDateVisite());
                JTVisiteur.setText(uneVisite.getUnVisiteur().getMatricule());
                JTMedecin.setText(uneVisite.getUnMedecin().getCodeMed());
                JTCommentaire.setText(uneVisite.getUnCommentaire());

                //Affiche les panneaux de la visite et de l'offre de médicament
                pTexteVisite.setVisible(true);
                pErreurCommentaire.setVisible(true);
                pBoutonsAjout.setVisible(true);
                pTexteOffre.setVisible(true);
                pErreurOffre.setVisible(true);
                pBoutonsModif.setVisible(true);
            }
            catch (Exception erreur) {
                JLErreurRecherche.setText(erreur.getMessage()); //Affiche les exceptions dans un JLabel
            }
        }
        //Modification du commentaire d'une visite
        if(source == JBModifier) {
            String commentaire = JTCommentaire.getText().toString();
            String reference = JTReference.getText().toString();

            try {
                //Le commentaire d'une visite ne doit pas dépasser 255 caractères
                if(commentaire.length() > 255) {
                    throw new Exception("Le commentaire ne peut pas dépasser 255 caractères");
                }
                //La référence de la visite ne doit pas être vide
                if(reference.equals("")){
                    throw new Exception("La référence ne peut pas être vide");
                }
                //La référence ne doit pas dépasser 5 caractères
                if(reference.length() > 5) {
                    throw new Exception("La référence ne peut pas dépasser 5 caractères");
                }
                //Une visite doit correspondre à la reference
                if(uneVisiteService.rechercherVisite(reference) == null) {
                    throw new Exception("Aucune visite correspondant à cette référence");
                }
                uneVisiteService.updateVisiteCommentaire(commentaire, reference);
                JLErreurCommentaire.setText("Votre commentaire a bien été pris en compte !");
            }
            catch (Exception erreur) {
                JLErreurCommentaire.setText(erreur.getMessage()); //Affiche les exceptions dans un JLabel
            }
        }
        //Ajout d'une offre de médicament
        if(source == JBAJout) {
            String depotLegal = JTDepotLegal.getText().toString();
            String quantite = JTQuantite.getText().toString();
            String reference = JTReference.getText().toString();

            int quantiteOfferte = -1;

            try {
                //Tous les champs sont obligatoires
                if(depotLegal.equals("") || quantite.equals("")) {
                    throw new Exception("Le dépôt légal et la quantité offerte sont obligatoires");
                }
                //Le dépot légal d'un médicament ne doit pas dépasser 50 caractères
                if(depotLegal.length() > 50) {
                    throw new Exception("La longueur du dépôt est inférieure à 50 caractères");
                }
                //Un médicament doit correspondre au dépot légal
                if(unMedicamentService.rechercherMedicament(depotLegal) == null) {
                    throw new Exception("Ce dépôt légal ne correspond à aucun médicament");
                }
                //Vérifie si la quantite est un entier
                if(!ValidationUtils.estUnEntier(quantite)) {
                    throw new Exception("La quantite proposée n'est pas un entier !");
                }
                //Si oui, converti la quantité en int
                else {
                    quantiteOfferte = Integer.parseInt(quantite);
                }
                //Au moins un medicament à offrir
                if(quantiteOfferte < 1) {
                    throw new Exception("La quantité offerte doit être supérieur à 0");
                }
                //La référence de la visite ne doit pas être vide
                if(reference.equals("")){
                    throw new Exception("La référence ne peut pas être vide");
                }
                //La référence doit être de 5 caractères max
                if(reference.length() > 5) {
                    throw new Exception("La référence ne peut pas dépasser 5 caractères");
                }
                //Une visite doit correspondre à la référence
                if(uneVisiteService.rechercherVisite(reference) == null) {
                    throw new Exception("Aucune visite correspondant à cette référence");
                }
                //Le visiteur doit posséder le médicament offert en stock
                if(unStockService.rechercherStock(depotLegal, JTVisiteur.getText().toString()) == null) {
                    throw new Exception("Vous ne posséder pas ce médicament en stock");
                }
                //La quantié offerte doit être inférieur au stock du visiteur
                if(unStockService.rechercherStock(depotLegal, VisiteDao.rechercher(reference).getUnVisiteur().getMatricule()).getQteStock() < quantiteOfferte) {
                    throw new Exception("Votre stock de ce médicament est insuffisant");
                }
                //Vérifie si le médicament n'a pas déjà été offert lors de la visite
                if(uneOffreService.rechercherOffrir(depotLegal,reference) != null) {
                    throw new Exception("Vous avez déjà offert ce médicament lors de cette visite");
                }
                uneOffreService.creerOffrir(depotLegal, reference,quantiteOfferte);
                uneOffreService.soustraireStock(depotLegal, reference,quantiteOfferte);
                JLErreurOffre.setText("Votre offre de médicaments à été prise en compte !");
                JTDepotLegal.setText("");
                JTQuantite.setText("");
            }
            catch(Exception erreur) {
                JLErreurOffre.setText(erreur.getMessage()); //Affiche les exceptions dans un JLabel
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        //Masque les panneaux de la visite et de l'offre au clic dans le champ reference
        if(source == JTReference) {
            pTexteVisite.setVisible(false);
            pErreurCommentaire.setVisible(false);
            pBoutonsAjout.setVisible(false);
            pTexteOffre.setVisible(false);
            pErreurOffre.setVisible(false);
            pBoutonsModif.setVisible(false);
            JLErreurCommentaire.setText("");
        }
        //Réinitialise le label d'erreur au clic dans les JTextfield
        if(source == JTDepotLegal || source == JTQuantite) {
            JLErreurOffre.setText("");
        }
        //Réinitialise le label d'erreur au clic dans le JTextfield
        if(source == JTCommentaire) {
            JLErreurCommentaire.setText("");
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
