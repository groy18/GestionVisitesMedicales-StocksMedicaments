package gsb.vue;

import gsb.modele.Offrir;
import gsb.modele.Visite;
import gsb.service.OffrirService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author womain
 *
 * Fen�tre r�capitulative d'une visite cibl�e
 *
 * 03/11/2019
 */
public class JIFVisiteRecap extends JIFVisite {

    //D�claration des composants du tableau
    protected JScrollPane scrollPane;
    protected JTable table;

    //Variables pour le tableau
    private String[][] data;
    private String[] columnNames;
    private DefaultTableModel model;

    private ArrayList<Offrir> colOffreVisite;
    private OffrirService uneOffreService;

    /**
     *
     * @param uneVisite objet Visite
     */
    public JIFVisiteRecap(Visite uneVisite) {
        super();
        this.remplirText(uneVisite);

        colOffreVisite = new ArrayList<Offrir>();
        uneOffreService = new OffrirService();

        //Permet de griser les JTextfield
        JTReference.setEditable(false);
        JTMedecin.setEditable(false);
        JTVisiteur.setEditable(false);
        JTDate.setEditable(false);
        JTCommentaire.setEditable(false);

        //Variables pour le tableau
        columnNames = new String[]{"Nom", "D�p�t L�gal", "Quantit�"}; //Colonnes du tableau
        model = new DefaultTableModel(columnNames, 0); //Mod�le de donn�es du tableau
        table = new JTable(model); //Ajout des donn�es dans le tableau
        scrollPane = new JScrollPane(table); //Ajout du tableau avec barre de d�filement
        scrollPane.setPreferredSize(new Dimension(1100, 400));

        colOffreVisite = uneOffreService.rechercherOffreVisite(uneVisite.getReference());
        for(Offrir uneOffre : colOffreVisite) {
            Object[] offre = {uneOffre.getUnMedicament().getNomCommercial(), uneOffre.getUnMedicament().getDepotLegal(), uneOffre.getQuantiteOfferte()};
            model.addRow(offre);
        }

        //Ajout du tableau dans le panneau principal
        p.add(scrollPane);
        
    }
}
