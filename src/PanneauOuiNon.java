import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public  class PanneauOuiNon extends JPanel{
    protected BdQuestionsReponses bd;
    protected PanneauPrincipal pp;
    protected JButton boutonOui;
    protected JButton boutonNon;

    public PanneauOuiNon(BdQuestionsReponses bd, PanneauPrincipal pp) {
        this.bd = bd;
        this.pp = pp;
        initialiserComposant();
    }

    private void initialiserComposant() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        Création sous-panneau pour bouton du bas
        JPanel sousPanneau = new JPanel();
        sousPanneau.setLayout(new BoxLayout(sousPanneau, BoxLayout.LINE_AXIS));
//        Création des bouton
        boutonOui = new JButton("Oui");
        boutonNon = new JButton("Non");
        sousPanneau.add(boutonOui);
        sousPanneau.add(boutonNon);

        // Créer un contour
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Black border with thickness of 2 pixels
        this.setBorder(border);

        //On rajoute le sousPanneau cree a l'instance
        this.add(sousPanneau);
        this.add(Box.createVerticalGlue()); // Espace flexible
    }



    public  void ajouteComposant(JLabel composant){
        this.add(Box.createVerticalGlue(),0); // Espace flexible
        this.add(composant, 1);
        this.add(Box.createVerticalGlue(),2); // Espace flexible
        this.revalidate();
        this.repaint();
    }
    public  void ajouterEcouteurOui(ActionListener ecouteur){
        boutonOui.addActionListener(ecouteur);
    }
    public  void ajouterEcouteurNon(ActionListener ecouteur){
        boutonNon.addActionListener(ecouteur);
    }

}

