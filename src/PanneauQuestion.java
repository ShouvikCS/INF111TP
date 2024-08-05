import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauQuestion extends PanneauOuiNon {
    private BdQuestionsReponses bdQuestionsReponses;
    private PanneauPrincipal panneauPrincipal;

    public PanneauQuestion(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
        String question = (String) this.bdQuestionsReponses.getLaChaineActuelle();
        JLabel questionLabel = new JLabel(question);
        ajouteComposant(questionLabel);
//        Classe interne Anonyme pour écouteuroui
        ajouterEcouteurOui(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_NEGATIVE);
                panneauPrincipal.miseAJour();
            }
        });
//        Classe interne Anonyme pour écouteurNon
        ajouterEcouteurNon(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_POSITIVE);
                panneauPrincipal.miseAJour();
            }
        });
    }
// Je pense pas qu'on doit redéfinir les classe hérité de la classe parent (PanneauOUINON)! C'est pourquoi les classe interne anonyme sont le meilleur choix

//    @Override
//    public void ajouterEcouteurOui(ActionListener ecouteur) {
//        super.ajouterEcouteurOui(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_NEGATIVE);
//                panneauPrincipal.miseAJour();
//            }
//        });
//    }
//
//    @Override
//    public void ajouterEcouteurNon(ActionListener ecouteur) {
//        super.ajouterEcouteurNon(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                bdQuestionsReponses.deplacerDansArbre(Constantes.REPONSE_POSITIVE);
//                panneauPrincipal.miseAJour();
//            }
//        });
//    }

}
