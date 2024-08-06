import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauReponse extends PanneauOuiNon {

    public PanneauReponse(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
        Reponse reponse = (Reponse)this.bd.getLaChaineActuelle();
        ImageIcon imageIcon = reponse.getImage();

        JLabel reponseLabel = new JLabel(reponse.getReponse(), imageIcon, JLabel.CENTER);
        reponseLabel.setAlignmentX(CENTER_ALIGNMENT);
        reponseLabel.setForeground(Color.red);
        //reponseLabel.getFontMetrics();
        reponseLabel.setVerticalTextPosition(JLabel.TOP);
        reponseLabel.setHorizontalTextPosition(JLabel.CENTER);
       // setFont(reponseLabel.getFont().deriveFont(64.0f));
//        La position de l'image et de la reponse et ainsi que la disposition utilisé sera revu...

//        Ajout du composant au panneauOuiNon
        ajouteComposant(reponseLabel);
//        Classe Anonyme pour les écouteurs
        ajouterEcouteurOui(e -> {
            JOptionPane.showMessageDialog(null, "Bonne Reponse Trouvé!");
            bd.choisirPremiereQuestion();
            pp.miseAJour();
        });

        ajouterEcouteurNon(e -> {
            UtilitaireES.demanderReponseValide(bd);
            bd.choisirPremiereQuestion();
            pp.miseAJour();
        });

    }

}
