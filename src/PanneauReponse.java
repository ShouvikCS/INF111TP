import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauReponse extends PanneauOuiNon {
    private BdQuestionsReponses questionsReponses;
    private PanneauPrincipal panneauPrincipal;

    public PanneauReponse(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
        Reponse reponse = (Reponse)this.questionsReponses.getLaChaineActuelle();
        JLabel reponseLabel = new JLabel(reponse.getReponse());
        JLabel imageLabel = new JLabel(reponse.getImage());
        JLabel reponseImageLabel = new JLabel();
//        La position de l'image et de la reponse et ainsi que la disposition utilisé sera revu...

        reponseImageLabel.setLayout(new GridLayout(2, 1));
        reponseImageLabel.add(reponseLabel);
        reponseImageLabel.add(imageLabel);
//        Ajout du composant au panneauOuiNon
        ajouteComposant(reponseImageLabel);
//        Classe Anonyme pour les écouteurs
        ajouterEcouteurOui(e -> {
            JOptionPane.showMessageDialog(null, "Bonne Reponse Trouvé!");
            questionsReponses.choisirPremiereQuestion();
            panneauPrincipal.miseAJour();
        });

        ajouterEcouteurNon(e -> {
            UtilitaireES.demanderReponseValide(questionsReponses);
            panneauPrincipal.miseAJour();
        });

    }

}
