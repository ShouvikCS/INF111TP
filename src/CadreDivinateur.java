import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CadreDivinateur extends JFrame implements Runnable {
    private BdQuestionsReponses bd;
    private PanneauPrincipal pp;
    private Menu menuBar;

    @Override
    public void run() {
        this.pp = new PanneauPrincipal(this);
        this.menuBar = new Menu(this);
        setContentPane(this.pp);
        configurerFenetrePrincipale();
        setVisible(true);
    }

    private void configurerFenetrePrincipale() {
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Akinator");
    }

    public BdQuestionsReponses getBd(){
        return bd;
    }

    public void setBd(BdQuestionsReponses bd){
        this.bd = bd;
        this.bd.choisirPremiereQuestion();
        this.pp.miseAJour();
    }

    public void setBdName(String nom){
        this.bd.bdName(nom);
    }

    public String getBdName(){
        return this.bd.getNomFic();
    }
}
