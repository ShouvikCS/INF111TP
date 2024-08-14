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

    // methode  DemanderReponseValide écrite pas nous même a été mis en Commentaire

//    public static void demanderReponseValide1(BdQuestionsReponses bd) {
//        String reponse = null;
//        String question = null;
//
//        while (true) { // force une reponse si la bd est vide
//            reponse = JOptionPane.showInputDialog(
//                    null,
//                    bd.estVide() ? "Je ne connais rien. Entrez ce a quoi vous pensiez?" : "Je n'ai pas trouve votre reponse, Entrez a quoi vous pensiez");
//            if (reponse != null && !reponse.trim().isEmpty()) { // si une r?ponse n'est pas une chaine vide ou null
//                reponse = reponse.toLowerCase();
//                if(!bd.estVide() && reponse.equals(bd.getLaChaineActuelle())){
//
//                    JOptionPane.showMessageDialog(null,
//                            "C'est ça que j'ai dit, non?");
//                    return;
//                } else if (bd.reponseExiste(reponse)) { // si la reponse existe deja dans la bd
//
//                    JOptionPane.showMessageDialog(
//                            null,
//                            messageErreur(bd, reponse));
//
//                    return; // exit method, which restarts game
//                }
//
//                // demande pour une question
//                while (true) { // force une question si la bd est vide
//                    question = JOptionPane.showInputDialog(
//                            null,
//                            "Entrez une question concernant votre objet ou votre animal qui le distingue: ");
//
//                    if (question != null && !question.trim().isEmpty()) { // si la question n'est pas une chaine vide ou null
//                        question = question.toLowerCase();
//                        String nomFicImage = UtilitaireFichier.nomFichierValide("", UtilitaireFichier.OUVRE, "jpg");
//                        bd.ajouterQuestionReponse(question, reponse, new ImageIcon(nomFicImage));
//                        UtilitaireFichier.sauvegarde(bd, Constantes.NOM_FICHIER_BD);
//                        break;                    } else if (question == null) {
//                        if (!bd.estVide()) {
//                            break;
//                        } else {
//                            System.exit(0);
//                        }
//                    }
//                }
//                break;
//            } else if (reponse == null) { // if cancel is pressed, reponse returns null
//                if (!bd.estVide()) { // if the bd had answers, simply exit loop
//                    break;
//                } else { // if the bd was empty, exit program, forces the user to put initial data into the db next launch
//                    System.exit(0);
//                }
//            }
//        }
//    }

//    Celle fourni par le prof dans UtilitaireES TP2
    public static void demanderReponseValide(BdQuestionsReponses bd) {

        // Pour obtenir la réponse de l'utilisateur.
        String reponse;

        // Mettre un message distinctif si la bd est vide ou non.
        if(bd.estVide()){

            //JOptionPane.showMessageDialog(null, "La base de données est vide");
            // On demande une réponse.
            reponse =
                    JOptionPane.showInputDialog("Je ne connais rien, " +
                            " Entrez ce à quoi vous pensiez ? : ");
        }

        else{

            // On demande une réponse parce qu'on n'a pas trouvé la réponse.
            reponse =
                    JOptionPane.showInputDialog("Je n'ai pas trouvé" +
                            " votre réponse,  Entrez ce à quoi vous pensiez ? : ");
        }

        // L'utilisateur n'a pas annulé.
        if(reponse != null && !reponse.equals("")){

            reponse = reponse.toLowerCase();
//            boolean isReponse = bd.getLaChaineActuelle() instanceof Reponse;

            // S'il nous répéte ce qu'on vient de lui montrer.
            if(!bd.estVide() && bd.getLaChaineActuelle() instanceof Reponse && reponse.equals(((Reponse) bd.getLaChaineActuelle()).getReponse())) {
                JOptionPane.showMessageDialog(null,
                        "C'est ça que j'ai dit, non ????");

            }

            else{

                // Si la réponse  existe,  repUtilisateur sera différent de null.
                Reponse repUtilisateur = bd.getReponse(reponse);

                if (repUtilisateur != null) {

                    // On affiche la réponse et le message qui indique l'erreur.
                    JOptionPane.showMessageDialog(null, messageErreur(bd,reponse));

                }
                else {

                    // Il faut une question pour accompagner la réponse.
                    String question =
                            JOptionPane.showInputDialog("Entrez une question " +
                                    " concernant votre objet ou votre animal" +
                                    " qui le distingue  : ");

                    // Si l'utilisateur n'a pas annulé, on ajoute la réponse et sa
                    // question à la bd.
                    if(question != null && !question.equals("")){

                        // On veut un standard pour les questions
                        // avec une majuscule en commençant et en minusule pour
                        // le reste.  Les étudiants sauvegardent le tout en minuscule.
                        String chaine = question.toString().toLowerCase();
                        StringBuffer str = new StringBuffer(chaine);
                        str.setCharAt(0, Character.toUpperCase(str.charAt(0)));


                        String nomFicImage =
                                UtilitaireFichier.nomFichierValide("",
                                        UtilitaireFichier.OUVRE, "jpg");

                        // ne pas sauvegarder s'il n'y a pas d'image selectionnee
                        if (nomFicImage == null) {
                            return;
                        }

                        bd.ajouterQuestionReponse(str.toString(), reponse,
                                new ImageIcon(nomFicImage));
                    }
                }
            }
        }
    }

    public static String messageErreur(BdQuestionsReponses bd, String reponse) {

        Noeud chercheQuestion = bd.getInfoJeu().getPremier();
        boolean indiceIncorrectEstPositif = false;

        for (int i = 0; i < bd.getReponses().length; i++) {
            if (bd.getReponses()[i] != null && bd.getReponses()[i].getReponse().equals(reponse) ) {
                Liste listReponse = bd.getReponses()[i].getIndices();
                int minSize = Math.min(bd.getInfoJeu().getIndicesCourants().taille(), listReponse.taille());
                for (int j = 0; j < minSize; j++) {
                    if (!bd.getInfoJeu().getIndicesCourants().get(j).equals(listReponse.get(j))) {
                        for (int k = 0; k < j ; k++) {
                            if (listReponse.get(k).equals(Constantes.REPONSE_POSITIVE)){
                                chercheQuestion = chercheQuestion.getGauche();
                            } else if (listReponse.get(k).equals(Constantes.REPONSE_NEGATIVE)) { // if the indice is N, go right
                                chercheQuestion = chercheQuestion.getDroite();
                            }
                        }
                        indiceIncorrectEstPositif = listReponse.get(j).equals(Constantes.REPONSE_POSITIVE);
                        break;
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