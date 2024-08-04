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
//        Reponse reponse = (Reponse)this.questionsReponses.getLaChaineActuelle();
        String reponse = this.questionsReponses.getLaChaineActuelle();
//        Reponse reponse = new Reponse(rep,)
        JLabel reponseLabel = new JLabel(reponse);
//        JLabel imageLabel = new JLabel(reponse.getImage());
        this.add(reponseLabel, BorderLayout.NORTH);
//        this.add(imageLabel, BorderLayout.CENTER);

    }

    @Override
    public void ajouterEcouteur() {
        JButton ouiButton = new JButton("Oui");
        JButton nonButton = new JButton("Non");
        JPanel ouiNonPanel = new JPanel();
        ouiNonPanel.add(ouiButton);
        ouiNonPanel.add(nonButton);
        this.add(ouiNonPanel, BorderLayout.SOUTH);
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
