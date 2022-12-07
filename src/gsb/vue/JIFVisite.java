package gsb.vue;

import gsb.modele.Visite;

import javax.swing.*;
import java.awt.*;

/**
 * @author womain
 *
 * Fenêtre générale d'une visite
 *
 * 28/10/2019
 */
public class JIFVisite extends JInternalFrame {

    //Déclaration des JPanel
    protected JPanel p;
    protected JPanel pTexte;
    protected JPanel pBoutons;
    protected JPanel pErreur;

    //Déclaration des JLabel
    protected JLabel JLReference;
    protected JLabel JLDate;
    protected JLabel JLCommentaire;
    protected JLabel JLVisiteur;
    protected JLabel JLMedecin;

    //Déclaration des JTextField
    protected JTextField JTReference;
    protected JTextField JTDate;
    protected JTextField JTCommentaire;
    protected JTextField JTVisiteur;
    protected JTextField JTMedecin;

    public JIFVisite() {

        //Instanciation des panneaux
        p = new JPanel();
        pTexte = new JPanel(new GridLayout(5,2,10,10));
        pErreur = new JPanel();
        pErreur.setPreferredSize(new Dimension(1000,50));
        pBoutons = new JPanel();

        //Instanciation des JLabel
        JLReference = new JLabel("Référence");
        JLDate = new JLabel("Date");
        JLCommentaire = new JLabel("Commentaire");
        JLVisiteur = new JLabel("Visiteur");
        JLMedecin = new JLabel("Médecin");

        //Instanciation des JTextField
        JTReference = new JTextField(15);
        JTDate = new JTextField(15);
        JTCommentaire = new JTextField(15);
        JTVisiteur = new JTextField(15);
        JTMedecin = new JTextField(15);

        //Ajout au panneau des textes
        pTexte.add(JLReference);
        pTexte.add(JTReference);
        pTexte.add(JLDate);
        pTexte.add(JTDate);
        pTexte.add(JLCommentaire);
        pTexte.add(JTCommentaire);
        pTexte.add(JLVisiteur);
        pTexte.add(JTVisiteur);
        pTexte.add(JLMedecin);
        pTexte.add(JTMedecin);

        //Ajout au panneau principal
        p.add(pTexte);
        p.add(pErreur);
        p.add(pBoutons);

        //Ajout du panneau principal à la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    /**
     *
     * @param uneVisite objet Visite
     *
     * Permet de remplir les JTextField avec les attributs d'une visite
     */
    public void remplirText(Visite uneVisite) {
        JTReference.setText(uneVisite.getReference());
        JTDate.setText(uneVisite.getDateVisite());
        JTCommentaire.setText(uneVisite.getUnCommentaire());
        JTVisiteur.setText(uneVisite.getUnVisiteur().getMatricule());
        JTMedecin.setText(uneVisite.getUnMedecin().getCodeMed());
    }

    //Permet de vider les JTextField
    public void videTexte() {
        JTReference.setText("");
        JTDate.setText("");
        JTCommentaire.setText("");
        JTVisiteur.setText("");
        JTMedecin.setText("");
    }
}
