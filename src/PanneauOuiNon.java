import javax.swing.*;

public abstract class PanneauOuiNon extends JPanel {
    BdQuestionsReponses bd;
    PanneauPrincipal pp;

    public PanneauOuiNon(BdQuestionsReponses bd, PanneauPrincipal pp) {
        this.bd = bd;
        this.pp = pp;
    }

    public abstract void ajouteComposant();
}

public class PanneauReponse extends PanneauOuiNon {

    public PanneauReponse(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
    }
    @Override
    public void ajouteComposant() {

    }
}

public class PanneauQuestion extends PanneauOuiNon {

    public PanneauQuestion(BdQuestionsReponses bd, PanneauPrincipal pp) {
        super(bd, pp);
    }

    @Override
    public void ajouteComposant() {

    }
}