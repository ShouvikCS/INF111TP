import javax.swing.*;
import java.awt.*;

public class PanneauPrincipal extends JPanel {
    private CadreDivinateur cadreDivinateur;
    private BdQuestionsReponses bd;

    public PanneauPrincipal(CadreDivinateur cadre) {
        this.cadreDivinateur = cadre;
        initComposants();
    }
    public void initComposants() {

    }
    public void miseAJour() {

//        this.bd = cadreBd.getBd();
        if(!bd.estVide()){
            setVisible(true);
            creerPanCentral();
        }
    }
    public void creerPanCentral() {

    }

}
