import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauQuestion extends PanneauOuiNon {
    private BdQuestionsReponses bdQuestionsReponses;
    private PanneauPrincipal panneauPrincipal;

    public PanneauQuestion(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
        this.bdQuestionsReponses = bd;
        this.panneauPrincipal = pp;
        ajouterEcouteur();
    }

    @Override
    public void ajouteComposant() {
        String question = this.bdQuestionsReponses.getLaChaineActuelle();
        JLabel questionLabel = new JLabel(question);
        this.add(questionLabel);

    }
    @Override
    public void ajouterEcouteur() {
        JButton ouiButton = new JButton("Oui");
        JButton nonButton = new JButton("Non");
        JPanel boutonOuiNon = new JPanel();
        boutonOuiNon.add(ouiButton);
        boutonOuiNon.add(nonButton);
        this.add(boutonOuiNon, BorderLayout.SOUTH);
        ouiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_NEGATIVE);
                panneauPrincipal.miseAJour();
            }
        });
        nonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_POSITIVE);
                panneauPrincipal.miseAJour();
            }
        });

    }

}
