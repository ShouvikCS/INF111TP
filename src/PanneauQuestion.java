import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauQuestion extends PanneauOuiNon {

    public PanneauQuestion(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
        String question = (String) this.bd.getLaChaineActuelle();
        JLabel questionLabel = new JLabel(question);
        questionLabel.setAlignmentX(CENTER_ALIGNMENT); // center text in label
        questionLabel.setForeground(Color.blue);// center text in label

        boutonOui.addActionListener(e -> {
            bd.deplacerDansArbre(0);
            pp.miseAJour();
        });

        boutonNon.addActionListener(e -> {
            if (bd.deplacerDansArbre(1) == false) {
                UtilitaireES.demanderReponseValide(bd);
                bd.choisirPremiereQuestion();
            }
            pp.miseAJour();
        });
        ajouteComposant(questionLabel);
    }
}
