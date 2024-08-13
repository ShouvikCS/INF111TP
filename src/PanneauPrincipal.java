import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanneauPrincipal extends JPanel {

    private CadreDivinateur cadreDivinateur;
    private BdQuestionsReponses bd;
    private PanneauOuiNon panneauOuiNon;

    public PanneauPrincipal(CadreDivinateur cadre) {
        this.cadreDivinateur = cadre;
        this.bd = cadre.getBd();
        initComposants();
    }

    public void initComposants() {
        this.setLayout(new BorderLayout());

//        Ajout de l'image du divinateur à gauche
        JPanel panneauGauche = new JPanel();
        panneauGauche.setLayout(new GridLayout());
        ImageIcon image = new ImageIcon(getClass().getResource("Akinator.png"));
//        Présentation du jeu
        String presentationJeu = "<html><p>***********JEU DU DIVINATEURS*******<p>" +
                "<p>Il s'agit de penser a un animal, un objet ou un<p> " +
                "personnage et nous tentons de le trouver <p>" +
                "en posant des questions auxquelles <p>" +
                "vous devrez repondre par  oui ou par non.<p>" +
                "<p>Si nous ne trouvons pas, vous pourrez nous dire ce ?<p>" +
                "quoi vous pensiez et ajouter une question qui distingue<p>" +
                "votre reponse des autres.<p>" +
                "<p>Des mauvaises questions peuvent deranger<p>" +
                " le bon deroulement du jeu.<p>" +
                "**************************<p>" +
                "<html>";
        JLabel messagePresentation = new JLabel(presentationJeu, JLabel.CENTER);
        messagePresentation.setHorizontalAlignment(JLabel.CENTER);
        messagePresentation.setFont(new Font("Arial", Font.PLAIN, 12));
// Titre de l'image
        JLabel imageGauche = new JLabel("    Le Divinateur", image, JLabel.CENTER);
        imageGauche.setFont(new Font("Arial", Font.BOLD, 12));
        imageGauche.setHorizontalTextPosition(JLabel.CENTER);
        imageGauche.setVerticalTextPosition(JLabel.TOP);

        panneauGauche.add(messagePresentation);
        panneauGauche.add(imageGauche);
        panneauGauche.setLayout(new GridLayout(2, 1, 5, 3));
        this.add(panneauGauche, BorderLayout.WEST);

//        Ajout des deux boutons du bas
        JPanel panneauBas = new JPanel();
        panneauBas.setLayout(new BorderLayout());

        JButton retourButton = new JButton("<< Retour");
        JButton consulterReponsesBDButton = new JButton("Consulter les réponses");

        panneauBas.add(retourButton, BorderLayout.WEST);
        panneauBas.add(consulterReponsesBDButton, BorderLayout.EAST);
        this.add(panneauBas, BorderLayout.SOUTH);

        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bd.retourQuestion();
                miseAJour();
            }
        });
        consulterReponsesBDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConsultationReponses consultationReponses = new ConsultationReponses(bd);
                consultationReponses.openConsultation();
            }
        });
    }

    public void miseAJour() {
        this.bd = cadreDivinateur.getBd();
        if (!bd.estVide()) {
            setVisible(true);
            creerPanCentral();
        }
    }

    public void creerPanCentral() {
        if (this.panneauOuiNon != null) {
            this.remove(this.panneauOuiNon);
        }
        if (this.bd.reponseTrouvee()) {
            this.panneauOuiNon = new PanneauReponse(this.bd, this);
        } else {
            this.panneauOuiNon = new PanneauQuestion(this.bd, this);
        }
        this.add(this.panneauOuiNon, BorderLayout.CENTER);
        validate();
    }

}
