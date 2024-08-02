import javax.swing.*;
import java.awt.*;

public class CadreDivinateur extends JFrame {
    BdQuestionsReponses bd;
    PanneauPrincipal pp;

    public CadreDivinateur(BdQuestionsReponses bd, PanneauPrincipal pp) {
        this.bd = bd;
        this.pp = pp;
        configurerFenetrePrincipale();
        initialiserComposant();
    }


    private void configurerFenetrePrincipale() {
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Akinator");
    }
    private void initialiserComposant() {
    }
}
