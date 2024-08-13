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
        reponseLabel.setVerticalTextPosition(JLabel.TOP);
        reponseLabel.setHorizontalTextPosition(JLabel.CENTER);

//        Ajout du composant au panneauOuiNon

        ajouterEcouteurOui(e -> {
            JOptionPane.showMessageDialog(null, "Bonne reponse trouvée!");
            bd.choisirPremiereQuestion();
            pp.miseAJour();
        });

        ajouterEcouteurNon(e -> {
            UtilitaireES.demanderReponseValide(bd);
            bd.choisirPremiereQuestion();
            pp.miseAJour();
        });

        ajouteComposant(reponseLabel);

    }

}
