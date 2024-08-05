import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public  class PanneauOuiNon extends JPanel {
    private BdQuestionsReponses bd;
    private PanneauPrincipal pp;
    private JButton boutonOui;
    private JButton boutonNon;

    public PanneauOuiNon(BdQuestionsReponses bd, PanneauPrincipal pp) {
        this.bd = bd;
        this.pp = pp;
        initialiserComposant();
    }

    private void initialiserComposant() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        Création sous-panneau pour bouton du bas
        JPanel sousPanneau = new JPanel();
        sousPanneau.setLayout(new BoxLayout(sousPanneau, BoxLayout.LINE_AXIS));
//        Création des bouton
        boutonOui = new JButton("Oui");
        boutonNon = new JButton("Non");
        sousPanneau.add(Box.createHorizontalGlue()); // Espace flexible
        sousPanneau.add(boutonOui);
        sousPanneau.add(Box.createRigidArea(new Dimension(5, 0)));
        sousPanneau.add(boutonNon);
        sousPanneau.add(Box.createHorizontalGlue()); // Espace flexible
        this.add(sousPanneau);
    }

    public  void ajouteComposant(JLabel panneauOuiNon){
        this.remove(0);
        this.add(panneauOuiNon, 0);
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

