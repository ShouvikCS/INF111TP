import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Classe impl?mentant des sous programmes utilitaires
 * qui permettent de faire de la saisie au clavier pour le jeu du divinateur.
 *
 * Toutes les fonctionnalit?s importantes sont effectu?es par la classe 
 * BdQuestionsReponses.
 *
 * @author pbelisle
 * @version octobre 2007
 */

public class UtilitaireES {

    static Scanner clavier = new Scanner(System.in);

    /**********************************
     * AFFICHER PRESENTATION DU JEU
     *
     * Affiche un boîte de message qui explique le jeu.
     *
     **********************************/
    public static void afficherPresentationJeu(){


        String str = "***************************************\n" +
                "***********JEU DU DIVINATEURS*******\n" +
                "***************************************\n" +
                "***************************************\n" +
                "Il s'agit de penser à un animal, un objet ou un \n" +
                "personnage et nous tentons de le trouver \n" +
                "en posant des questions auxquelles \n" +
                "vous devrez répondre par  oui ou par non.\n\n\n" +
                "Si nous ne trouvons pas, vous pourrez nous dire ce à \n" +
                "quoi vous pensiez et ajouter une question qui distingue\n" +
                "votre réponse des autres.\n\n\n" +
                "Des mauvaises questions peuvent d?ranger" +
                " le bon déroulement du jeu." +
                "\n" +
                "************************************";

        JOptionPane.showMessageDialog(null, str);
    }





    /***************************
     * DEMARRER DIVINATEUR
     *************************
     *Permet l'interaction avec l'utilisateur en
     *posant les questions provenant de la base de donn?es des r?ponses et
     *en agissant selon lles indices donn?s par l'utilisateur.
     */
    public static void demarrerDivinateur(BdQuestionsReponses bd){

        // Sert a retenir s'il reste des question.
        boolean resteQuestion = true;

        // Sert ? saisir la r?ponse de l'utilisateur.
        int reponse = JOptionPane.OK_OPTION;

        // Choisir une premi?re question dans la bd.
        bd.choisirPremiereQuestion();

        // Tant  qu'on a pas trouvé la réponse et qu'il reste des questions et
        // que l'utilisateur n'appuie pas sur X.
        while(reponse != JOptionPane.CLOSED_OPTION && // the window isn't closed
                !bd.reponseTrouvee() && // and the 2 references left and right are not null
                resteQuestion){ // s'il reste des question.

            String [] options =  {"Oui", "Non"};

            String str = bd.getLaChaineActuelle();

            // On pose la question courante dans l'arbre de connaissance de la bd.
            reponse  = JOptionPane.showOptionDialog(null,
                    str + ((str.charAt(str.length() -1) == '?')? " " : "?"),
                    "Jeu du divinateur",
                    JOptionPane.CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    "Oui");

            if(reponse != JOptionPane.CLOSED_OPTION){

                // On se prom?ne dans l'arbre de connaissances.
                resteQuestion = bd.deplacerDansArbre(reponse);
            }
        }

        // Si on est sorti de la boucle pr?c?dente c'est qu'il ne reste plus de
        // question ou qu'on a trouv?.  Donc s'il reste des questions c'est
        // qu'on a trouv?
        if (resteQuestion && reponse != JOptionPane.CLOSED_OPTION) {

            reponse = JOptionPane.showConfirmDialog(null,
                    "La réponse est " + bd.getLaChaineActuelle() + "; Est-ce exact ?");

            // Si l'utilisateur n'annule pas.
            if(reponse != JOptionPane.CANCEL_OPTION &&
                    reponse != JOptionPane.CLOSED_OPTION){

                // Si c'est oui, on a trouv?, bravo!
                if (reponse == 0){

                    JOptionPane.showMessageDialog(null,
                            "Bravo nous avons trouvé votre reponse");
                }

                // Autrement, on demande quel est sa r?ponse.
                else{

                    demanderReponseValide(bd);
                }

            }
        }

        // Il ne reste plus de questions alors si l'utilisateur n'annule pas
        else if(reponse != JOptionPane.CANCEL_OPTION &&
                reponse != JOptionPane.CLOSED_OPTION){

            // On demande quel est sa r?ponse.
            demanderReponseValide(bd);
        }
    }

    public static void demanderReponseValide(BdQuestionsReponses bd) {
        String reponse = JOptionPane.showInputDialog(null, "Je ne connais rien. Entrez ce à quoi vous pensiez?");
        if (!bd.estVide()){
            String question = JOptionPane.showInputDialog(null, "Ajouter une question ");
            bd.ajouterQuestionReponse(question, reponse);
            return;
        }
        if (bd.reponseExiste(reponse)){
            JOptionPane.showInputDialog(null, "la reponse existe déjà");
        } else {
            String question = JOptionPane.showInputDialog(null, "Ajouter une question ");
            bd.ajouterQuestionReponse(question, reponse);
        }
        UtilitaireFichier.sauvegarde(bd, "bd.bin");
    }

}