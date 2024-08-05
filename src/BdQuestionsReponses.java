import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class BdQuestionsReponses implements Serializable {
    private  List<String> questions;
    public  Reponse [] reponses;
    private int nbReponses;
    private String nomFic = Constantes.NOM_FICHIER_BD;
    public InfoJeu infoJeu;


    public BdQuestionsReponses() {
        infoJeu = new InfoJeu();
        questions = new ArrayList<>();
        reponses = new Reponse[100];
        this.nbReponses = 0;
    }


    public List<String> getQuestions() {
        return questions;
    }

    public Reponse[] getReponses() {
        return reponses;
    }

    public InfoJeu getInfoJeu() {
        return infoJeu;
    }

    public int getNbReponses() {
        return nbReponses;
    }

    public void ajouterQuestionReponse(String question, String reponse, ImageIcon image) {

        int questionIndex = 0;

        if (this.questions.contains(question)) { // if the question already exists in the arraylist, we simply save its index
            questionIndex = this.questions.indexOf(question);
        } else { //otherwise, we add the unique question and the index is (the size of the array - 1)
            this.questions.add(question);
            questionIndex = this.questions.size() - 1;
        }

        this.reponses[this.nbReponses] = new Reponse(reponse, image);
        Noeud ques = new Noeud(questionIndex);
        Noeud reps = new Noeud(nbReponses);
        ques.setGauche(reps);
        
        if (estVide()) {
            this.infoJeu.setPremier(ques);
            this.infoJeu.setCourant(ques);
            this.infoJeu.setDerniereQuestionPositive(true);
            this.reponses[this.nbReponses].getIndices().enfiler(Constantes.REPONSE_POSITIVE);
        } else if (!this.infoJeu.isDerniereQuestionPositive() && this.infoJeu.getCourant().getDroite() == null && this.infoJeu.getCourant().getGauche() != null){
            this.infoJeu.getCourant().setDroite(ques);
            this.infoJeu.getCourant().getDroite().setGauche(reps);
            Liste listTemp = new Liste(infoJeu.getIndicesCourants());
            listTemp.enfiler(Constantes.REPONSE_POSITIVE);
            reponses[infoJeu.getCourant().getIndex()].getIndices().enfiler("N");
            listTemp.enfiler("O");
            System.out.println(listTemp);
            this.reponses[this.nbReponses].setIndices(listTemp);
        } else if (this.infoJeu.isDerniereQuestionPositive() && !deplacerDansArbre(1)){
            reponses[infoJeu.getCourant().getIndex()].getIndices().enfiler(Constantes.REPONSE_NEGATIVE);
            ques.setDroite(this.infoJeu.getCourant());
            this.infoJeu.getPrecedent().setGauche(ques);
            Liste listTemp = new Liste(infoJeu.getIndicesCourants());
            listTemp.enfiler(Constantes.REPONSE_POSITIVE);
            listTemp.enfiler("O");
            this.reponses[this.nbReponses].setIndices(listTemp);
        } else {
            ques.setDroite(this.infoJeu.getCourant());
            reponses[infoJeu.getCourant().getIndex()].getIndices().enfiler(Constantes.REPONSE_NEGATIVE);
            this.infoJeu.getPrecedent().setDroite(ques);
            Liste listTemp = new Liste(infoJeu.getIndicesCourants());
            listTemp.enfiler(Constantes.REPONSE_POSITIVE);
            listTemp.enfiler("O");
            this.reponses[this.nbReponses].setIndices(listTemp);
        }

        nbReponses++;
        UtilitaireFichier.sauvegarde(this, nomFic);

    }
    // Verifie si la reponse existe dans tableau de reponse
    public boolean reponseExiste(String reponse) {
        for (int i = 0; i < this.reponses.length; i++) {
            if (estVide()){
                return false;
            }
            if (this.reponses[i] != null && this.reponses[i].getReponse().equals(reponse) ) {
                return true;
            }
        }
        return false;
    }
//    verifie si la reponse Existe et retourne la reponse (copié dans BDquestionReponse fournie par le prof)
    public Reponse getReponse(String reponse){
        /*
         * Stratégie : Fouille linéaire.  On récupère une Reponse à la fois et
         * on compare l'attribut reponse à la réponse reçue.  La boucle se
         * termine si la collection est parcourue au complet ou que la réponse
         * a été trouvée.
         */

        int i = 0;

        int taille = nbReponses;
        Reponse r = null;

        if(taille != 0){

            r = reponses[i];

            while(i < taille && !r.getReponse().equals(reponse)){
                i++;
                if(i < taille){
                    r = reponses[i];
                }

                else {
                    r = null;
                }
            }

        }

        // Autrement dit :: Si la taille du tableau de réponse est de 0 ou que i
        // == taile, c'est que la réponse n'existe pas.
        return r;
    }


    public boolean estVide() {
        return nbReponses == 0;
    }

    public void choisirPremiereQuestion() {
        infoJeu.setCourant(infoJeu.getPremier());
        infoJeu.setDerniereQuestionPositive(false);
        infoJeu.setPrecedent(null);
        infoJeu.emptyIndiceCourrante();

    }

    public boolean reponseTrouvee() {

        return infoJeu.getCourant().getDroite() == null && infoJeu.getCourant().getGauche() == null;
        // return true if the 2 references in current noeud are null
    }
// Modification de la methode getLachaineActuelle
    public Object getLaChaineActuelle(){

        /*
         * Stratégie : On utilise l'opérateur ternaire pour retourner la bonne
         * chaîne qui dépend si c'est une réponse ou une question.
         */
        return (reponseTrouvee())

                // Si c'est l'indice d'une réponse, il faut la description.
                ? reponses[infoJeu.getCourant().getIndex()]

                //Sinon, c'est l'indice d'une question.
                : questions.get(infoJeu.getCourant()
                .getIndex());
    }

    public boolean deplacerDansArbre(int reponse) {
        if (reponse == 0) { // Oui = 0; Non = 1

            infoJeu.addIndiceCourrante(Constantes.REPONSE_POSITIVE);
            infoJeu.setDerniereQuestionPositive(true);
            infoJeu.setCourant(infoJeu.getCourant().getGauche());
            return true;

        } else {

            if(infoJeu.getCourant().getGauche() != null){
                infoJeu.addIndiceCourrante(Constantes.REPONSE_NEGATIVE);
                infoJeu.setDerniereQuestionPositive(false);
            }

            if (infoJeu.getCourant().getDroite() == null) {
                return false;
            } else {
                infoJeu.setCourant(infoJeu.getCourant().getDroite());
                return true;
            }
        }

        //Cette procédure reçoit l’indice de l’utilisateur (O ou
        //N), déplace les références de l’attribut de type InfoJeu, ajuste l’attribut booléen
        //selon l’indice reçu et ajoute le bon caractère à la liste des indices donnés (elle est
        //utilisée par UtilitaireES.demarrerDivinateur).
    }

}
