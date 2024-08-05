//import java.awt.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintStream;
//
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//public class DemarrerJeuDivinateur {
//
//    public static void main(String[] args) {
//
//        /*
//         * Strategie : Une classe BdQuestionsReponse retient les questions,
//         * les reponses et le chemin pour les retrouver.  La classe JOptionPane
//         * est utilisee pour les ES ainsi que le module UtilitaireES.
//         *
//         * La boucle "principale" du jeu est d�finie localement, les autres sont
//         * dans l'utilitaireES.
//         */
//
//        // Pour avoir une fen?tre en fond d'?cran.
////        EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new PanneauPrincipal().setVisible(true);
////            }
////        });
//        JFrame cadre = new JFrame();
//        cadre.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        cadre.setVisible(true);
////        EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new CadreDivinateur().setVisible(true);
////            }
////        });
//
//        // La bd qui contient les reponses, leur question
//        // et les liens entre elles.
//        BdQuestionsReponses bd = UtilitaireFichier.obtenirBd();
//
//        // Sert a saisir la reponse de l'utilisateur aux questions.
//        int reponse = 0;
//
//        // Lorsque le jeu fonctionne, enlever le commentaire pour
//        // dans la version officielle.
//         UtilitaireES.afficherPresentationJeu();
//
//        //  Si la bd est vide, on demande simplement un objet ou un animal et
//        // au moins une question qui le distingue.  Autrement dit, on s'assure
//        // d'avoir au moins une reponse dans la bd avant de jouer.
//        if (bd.estVide()) {
//
//            UtilitaireES.demanderReponseValide(bd);
//
//            // On demande si l'utilisateur veut jouer imm?diatement.
//            reponse =
//                    JOptionPane.showConfirmDialog(null,
//                            "Voulez-vous jouez maintenant ?");
//        }
//
//        // Tant que l'utilisateur veut jouer (il n'a pas annul?)
//        while(reponse == JOptionPane.OK_OPTION){
//
//            // On d?marre la partie.
////            UtilitaireES.demarrerDivinateur(bd);
//
//            // On demande s'il veut rejouer.
//            reponse =
//                    JOptionPane.showConfirmDialog(null,
//                            "Voulez-vous rejouez  ?");
//        }
//
//        // On sort de la boucle c'est que la partie est terminee.
//
//
//        // Lorsque le jeu fonctionne, enlever le commentaire pour
//        // dans la version officielle.
//        JOptionPane.showMessageDialog(null, "Merci d'avoir jouer avec nous!");
//
//        // Fermeture de la fenetre en arriere.
//        cadre.dispose();
//    }
//
//}