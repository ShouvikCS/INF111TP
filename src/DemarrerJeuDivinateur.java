import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DemarrerJeuDivinateur {

    /**
     * Il s'agit pour ce travail de la simulation d' un jeu d'intelligence
     * artificielle . Ce jeu consiste � demander � son utilisateur de penser �
     * un animal ou � un objet ou un personnage, et le programme tente de le
     *trouver en lui posant des questions.  L'utilisateur ne doit  r�pondre que 
     *par :  oui, non.
     *
     * � noter : Le constructeur de la classe BdQuestionsReponses tente
     * d'ouvrir un fichier au nom d�fini dans Constantes.NOM_FICHIER_BD.
     * S'il existe, il contient toutes les donn�es du jeu.  Dans le cas contraire,
     * la bd est vide alors le programme principal saisit une r�ponse et au
     * moins une question pour  l'ajouter � la bd.  C'est alors que ce fichier est
     * cr�� et maintenu � jour � chaque nouvelle r�ponse qui s'ajoute dans le
     * jeu.
     *
     * @author Pierre Belisle
     * @version janvier 2017
     */


    public static void main(String[] args) {

        /*
         * Strategie : Une classe BdQuestionsReponse retient les questions,
         * les reponses et le chemin pour les retrouver.  La classe JOptionPane
         * est utilisee pour les ES ainsi que le module UtilitaireES.
         *
         * La boucle "principale" du jeu est d�finie localement, les autres sont
         * dans l'utilitaireES.
         */

        // Pour avoir une fen�tre en fond d'�cran.
        JFrame cadre = new JFrame();
        cadre.setExtendedState(JFrame.MAXIMIZED_BOTH);
        cadre.setVisible(true);

        // La bd qui contient les reponses, leur question
        // et les liens entre elles.
        BdQuestionsReponses bd = UtilitaireFichier.obtenirBd();

        // Sert a saisir la reponse de l'utilisateur aux questions.
        int reponse = 0;

        // Lorsquel le jeu fonctionne, enlever le commentaire pour
        // dans la version officielle.
        //UtilitaireES.afficherPresentationJeu();

        //  Si la bd est vide, on demande simplement un objet ou un animal et
        // au moins une question qui le distingue.  Autrement dit, on s'assure
        // d'avoir au moins une reponse dans la bd avant de jouer.
        if (bd.estVide()) {

            UtilitaireES.demanderReponseValide(bd);

            // On demande si l'utilisateur veut jouer imm�diatement.
            reponse =
                    JOptionPane.showConfirmDialog(null,
                            "Voulez-vous jouez maintenant ?");
        }

        // Tant que l'utilisateur veut jouer (il n'a pas annul�)
        while(reponse == JOptionPane.OK_OPTION){

            // On d�marre la partie.
            UtilitaireES.demarrerDivinateur(bd);

            // On demande s'il veut rejouer.
            reponse =
                    JOptionPane.showConfirmDialog(null,
                            "Voulez-vous rejouez  ?");
        }

        // On sort de la boucle c'est que la partie est terminee.


        // Lorsquel le jeu fonctionne, enlever le commentaire pour
        // dans la version officielle.
        //JOptionPane.showMessageDialog(null, "Merci d'avoir jouer avec nous!");

        // Fermeture de la fenetre en arriere.
        cadre.dispose();
    }

}