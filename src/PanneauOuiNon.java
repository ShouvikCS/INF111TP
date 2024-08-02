import javax.swing.*;

public abstract class PanneauOuiNon extends JPanel {
    BdQuestionsReponses bd;
    PanneauPrincipal pp;

    public PanneauOuiNon(BdQuestionsReponses bd, PanneauPrincipal pp) {
        this.bd = bd;
        this.pp = pp;
    }

    public abstract void ajouteComposant();
    public abstract void ajouterEcouteur();
}

