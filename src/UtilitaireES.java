import java.util.Scanner;

import javax.swing.*;

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
     * Affiche un bo?te de message qui explique le jeu.
     *
     **********************************/
//    public static void afficherPresentationJeu() {
//
//
//        String str = "***************************************\n" +
//                "***********JEU DU DIVINATEURS*******\n" +
//                "***************************************\n" +
//                "***************************************\n" +
//                "Il s'agit de penser a un animal, un objet ou un \n" +
//                "personnage et nous tentons de le trouver \n" +
//                "en posant des questions auxquelles \n" +
//                "vous devrez repondre par  oui ou par non.\n\n\n" +
//                "Si nous ne trouvons pas, vous pourrez nous dire ce ? \n" +
//                "quoi vous pensiez et ajouter une question qui distingue\n" +
//                "votre reponse des autres.\n\n\n" +
//                "Des mauvaises questions peuvent deranger" +
//                " le bon deroulement du jeu." +
//                "\n" +
//                "************************************";
//
//        JOptionPane.showMessageDialog(null, str);
//    }


    /***************************
     * DEMARRER DIVINATEUR
     *************************
     *Permet l'interaction avec l'utilisateur en
     *posant les questions provenant de la base de donn?es des r?ponses et
     *en agissant selon lles indices donn?s par l'utilisateur.
     */
//    public static void demarrerDivinateur(BdQuestionsReponses bd) {
//
//        // Sert a retenir s'il reste des question.
//        boolean resteQuestion = true;
//
//        // Sert ? saisir la r?ponse de l'utilisateur.
//        int reponse = JOptionPane.OK_OPTION;
//
//        // Choisir une premi?re question dans la bd.
//        bd.choisirPremiereQuestion();
//
//        // Tant  qu'on a pas trouv? la r?ponse et qu'il reste des questions et
//        // que l'utilisateur n'appuie pas sur X.
//        while (reponse != JOptionPane.CLOSED_OPTION && // the window isn't closed
//                !bd.reponseTrouvee() && // and the 2 references left and right are not null
//                resteQuestion) { // s'il reste des question.
//
//            String[] options = {"Oui", "Non"};
//
//            String str = bd.getLaChaineActuelle();
//
//            // On pose la question courante dans l'arbre de connaissance de la bd.
//            reponse = JOptionPane.showOptionDialog(null,
//                    str + ((str.charAt(str.length() - 1) == '?') ? " " : "?"),
//                    "Jeu du divinateur",
//                    JOptionPane.CANCEL_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,
//                    null,
//                    options,
//                    "Oui");
//
//            if (reponse != JOptionPane.CLOSED_OPTION) {
//
//                // On se prom?ne dans l'arbre de connaissances.
//                resteQuestion = bd.deplacerDansArbre(reponse);
//            }
//        }
//
//        // Si on est sorti de la boucle pr?c?dente c'est qu'il ne reste plus de
//        // question ou qu'on a trouv?.  Donc s'il reste des questions c'est
//        // qu'on a trouv?
//        if (resteQuestion && reponse != JOptionPane.CLOSED_OPTION) {
//
//            reponse = JOptionPane.showConfirmDialog(null,
//                    "La reponse est " + bd.getLaChaineActuelle() + "; Est-ce exact ?");
//
//            // Si l'utilisateur n'annule pas.
//            if (reponse != JOptionPane.CANCEL_OPTION &&
//                    reponse != JOptionPane.CLOSED_OPTION) {
//
//                // Si c'est oui, on a trouv?, bravo!
//                if (reponse == 0) {
//
//                    JOptionPane.showMessageDialog(null,
//                            "Bravo nous avons trouve votre reponse");
//                }
//
//                // Autrement, on demande quel est sa r?ponse.
//                else {
//
//                    demanderReponseValide(bd);
//                }
//
//            }
//        }
//
//        // Il ne reste plus de questions alors si l'utilisateur n'annule pas
//        else if (reponse != JOptionPane.CANCEL_OPTION &&
//                reponse != JOptionPane.CLOSED_OPTION) {
//
//            // On demande quel est sa r?ponse.
//            demanderReponseValide(bd);
//        }
//    }

    public static void demanderReponseValide(BdQuestionsReponses bd) {
        String reponse = null;
        String question = null;

        while (true) { // force une reponse si la bd est vide
            reponse = JOptionPane.showInputDialog(
                    null,
                    bd.estVide() ? "Je ne connais rien. Entrez ce a quoi vous pensiez?" : "Je n'ai pas trouve votre reponse, Entrez a quoi vous pensiez");
            if (reponse != null && !reponse.trim().isEmpty()) { // si une r?ponse n'est pas une chaine vide ou null
                reponse = reponse.toLowerCase();
                if(!bd.estVide() && reponse.equals(bd.getLaChaineActuelle())){

                    JOptionPane.showMessageDialog(null,
                            "C'est ça que j'ai dit, non?");
                    return;
                } else if (bd.reponseExiste(reponse)) { // si la reponse existe deja dans la bd

                    JOptionPane.showMessageDialog(
                            null,
                            messageErreur(bd, reponse));

                    return; // exit method, which restarts game
                }

                // demande pour une question
                while (true) { // force une question si la bd est vide
                    question = JOptionPane.showInputDialog(
                            null,
                            "Entrez une question concernant votre objet ou votre animal qui le distingue: ");

                    if (question != null && !question.trim().isEmpty()) { // si la question n'est pas une chaine vide ou null
                        question = question.toLowerCase();
                        String nomFicImage = UtilitaireFichier.nomFichierValide("", UtilitaireFichier.OUVRE, "jpg");
                        bd.ajouterQuestionReponse(question, reponse, new ImageIcon(nomFicImage));
                        UtilitaireFichier.sauvegarde(bd, Constantes.NOM_FICHIER_BD);
                        break;                    } else if (question == null) {
                        if (!bd.estVide()) {
                            break;
                        } else {
                            System.exit(0);
                        }
                    }
                }
                break;
            } else if (reponse == null) { // if cancel is pressed, reponse returns null
                if (!bd.estVide()) { // if the bd had answers, simply exit loop
                    break;
                } else { // if the bd was empty, exit program, forces the user to put initial data into the db next launch
                    System.exit(0);
                }
            }
        }
    }

    public static String messageErreur(BdQuestionsReponses bd, String reponse) {

        Noeud chercheQuestion = bd.getInfoJeu().getPremier();
        boolean indiceIncorrectEstPositif = false;

        for (int i = 0; i < bd.reponses.length; i++) {
            if (bd.reponses[i] != null && bd.reponses[i].getReponse().equals(reponse) ) {
                Liste listReponse = bd.reponses[i].getIndices();
                int minSize = Math.min(bd.infoJeu.getIndicesCourants().taille(), listReponse.taille());
                for (int j = 0; j < minSize; j++) {
                    if (!bd.infoJeu.getIndicesCourants().get(j).equals(listReponse.get(j))) {
                        for (int k = 1; k <= j ; k++) {
                            if (listReponse.get(k).equals(Constantes.REPONSE_POSITIVE)){
//                                indiceMalRepondu = true;
                                chercheQuestion = chercheQuestion.getGauche();
                            } else if (listReponse.get(k).equals(Constantes.REPONSE_NEGATIVE)) { // if the indice is N, go right
                                chercheQuestion = chercheQuestion.getDroite();
                            }
                        }
                        indiceIncorrectEstPositif = listReponse.get(j).equals(Constantes.REPONSE_POSITIVE);
                        break; // we stop at the first difference in the list
                    }
                }
            }
        }

        String indiceIncorrectDonne = indiceIncorrectEstPositif ? "Non" : "Oui";
        String indiceCorrectADonner = indiceIncorrectEstPositif ? "Oui" : "Non";
        String questionMalRepondue = bd.getQuestions().get(chercheQuestion.getIndex());

        String messageErreur = reponse + " existe deja dans notre base de donnee,\n" +
                "Vous auriez du repondre " + indiceCorrectADonner + " a la question: \n" +
                questionMalRepondue + ((questionMalRepondue.charAt(questionMalRepondue.length() - 1) == '?') ? " " : "?") + "\n" + ///
                "mais vous avez repondu " + indiceIncorrectDonne + "\n";

        return messageErreur;
    }
}