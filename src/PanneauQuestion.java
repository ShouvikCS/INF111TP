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
        String question = (String) this.bdQuestionsReponses.getLaChaineActuelle();
        JLabel questionLabel = new JLabel(question);
        questionLabel.setAlignmentX(CENTER_ALIGNMENT); // center text in label
        ajouteComposant(questionLabel);
//        Classe interne Anonyme pour écouteur oui
        ajouterEcouteurOui(e -> {
            bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_POSITIVE);
            panneauPrincipal.miseAJour();
        });
//        Classe interne Anonyme pour écouteurNon
        ajouterEcouteurNon(e -> {
            bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_NEGATIVE);
            panneauPrincipal.miseAJour();
        });
    }
}
