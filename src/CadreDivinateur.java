import javax.swing.*;
import java.awt.*;

public class CadreDivinateur extends JFrame implements Runnable {
    private BdQuestionsReponses bd;
    private PanneauPrincipal pp;

    @Override
    public void run() {
        this.bd = new BdQuestionsReponses();
        this.pp = new PanneauPrincipal(this);
        setContentPane(this.pp);
        configurerFenetrePrincipale();
        initialiserComposant();
        setVisible(true);
        UtilitaireES.demanderReponseValide(this.bd);
    }

    private void configurerFenetrePrincipale() {
        this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Akinator");
    }
    private void initialiserComposant() {
        //        Creation du menu
        JMenuBar mb = new JMenuBar();
        JMenu mnFile = new JMenu("Fichier");
        JMenuItem mntmNew = new JMenuItem("Nouveau");
        JMenuItem mntmOpen = new JMenuItem("Ouvrir");
        JMenuItem mntmSave = new JMenuItem("Sauvegarder");
        JMenuItem mntmExit = new JMenuItem("Quitter");
        mnFile.add(mntmNew);
        mnFile.add(mntmOpen);
        mnFile.add(mntmSave);
        mnFile.add(mntmExit);
        mb.add(mnFile);
        this.setJMenuBar(mb);

    }
    public BdQuestionsReponses getBd(){
        return bd;
    }
    public void setBd(BdQuestionsReponses bd){
        this.bd = bd;
        this.bd.choisirPremiereQuestion();
        this.pp.miseAJour();
    }
}
