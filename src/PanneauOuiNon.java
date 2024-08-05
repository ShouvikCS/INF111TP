import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauOuiNon extends JPanel {
    BdQuestionsReponses bd;
    int pp;

    JPanel buttonSubPanel = new JPanel();
    JButton buttonOui = new JButton("Oui");
    JButton buttonNon = new JButton("Non");



    public PanneauOuiNon(BdQuestionsReponses bd, int pp) {
        this.bd = bd;
        this.pp = pp;
        buttonSubPanel.add(buttonOui);
        buttonSubPanel.add(buttonNon);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(buttonSubPanel);
    }

    public void ajouteComposant() {
        JPanel contenuSubPanel = new JPanel();
        contenuSubPanel.add(new JButton("hello"));
        this.add(contenuSubPanel, 1);
    }
    public void ajouterEcouteur() {
        buttonOui.addActionListener(new EcouteurOui() {

        });
        buttonNon.addActionListener(new EcouteurNon() {

        });
    }
}

