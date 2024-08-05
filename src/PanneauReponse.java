import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauReponse extends PanneauOuiNon {
    private BdQuestionsReponses questionsReponses;
    private PanneauPrincipal panneauPrincipal;

    public PanneauReponse(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
        this.questionsReponses = bd;
        this.panneauPrincipal = pp;
    }

    @Override
    public void ajouteComposant() {
//   Faudrait transtyper le retour de getLachaineActuelle tel que démander dans l'énoncé du projet! don't know how to do this!
//        I'D tried this but it doesn't work
        String rep = this.questionsReponses.getLaChaineActuelle();
        Reponse reponse = Reponse.convertToString(rep, rep);
        JLabel reponseLabel = new JLabel(reponse.getReponse());
        JLabel imageLabel = new JLabel(reponse.getImage());
        this.add(reponseLabel, BorderLayout.WEST);
        this.add(imageLabel, BorderLayout.SOUTH);
    }

    @Override
    public void ajouterEcouteur() {
        JButton ouiButton = new JButton("Oui");
        JButton nonButton = new JButton("Non");
        JPanel ouiNonPanel = new JPanel();
        ouiNonPanel.add(ouiButton);
        ouiNonPanel.add(nonButton);
        this.add(ouiNonPanel, BorderLayout.CENTER);
        ouiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bonne Reponse Trouvé!");
                questionsReponses.choisirPremiereQuestion();
                panneauPrincipal.miseAJour();
            }
        });
        nonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UtilitaireES.demanderReponseValide(questionsReponses);
                panneauPrincipal.miseAJour();
            }
        });
        
    }
}
