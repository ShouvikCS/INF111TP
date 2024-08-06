import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

public  class PanneauOuiNon extends JPanel implements ActionListener {
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
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        Création sous-panneau pour bouton du bas
        JPanel sousPanneau = new JPanel();
        sousPanneau.setLayout(new BoxLayout(sousPanneau, BoxLayout.LINE_AXIS));
//        Création des bouton
        boutonOui = new JButton("Oui");
        boutonNon = new JButton("Non");
        boutonOui.addActionListener(this);
        sousPanneau.add(boutonOui);
        sousPanneau.add(Box.createRigidArea(new Dimension(5, 0)));
        sousPanneau.add(boutonNon);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Black border with thickness of 2 pixels
        this.setBorder(border);
        this.add(sousPanneau);
    }



    public  void ajouteComposant(JLabel composant){
//        this.remove(0);
        this.add(Box.createVerticalGlue(),0); // Espace flexible
        this.add(composant, 1);
        this.add(Box.createVerticalGlue()); // Espace flexible

        this.revalidate();
        this.repaint();
    }
    public  void ajouterEcouteurOui(ActionListener ecouteur){
        boutonOui.addActionListener(ecouteur);
    }
    public  void ajouterEcouteurNon(ActionListener ecouteur){
        boutonNon.addActionListener(ecouteur);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello");

//        bd.deplacerDansArbre(Constantes.REPONSE_POSITIVE);
        pp.miseAJour();
    }
}

