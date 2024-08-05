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


    public boolean estVide() {
        return nbReponses == 0;
    }

    public void choisirPremiereQuestion() {
        infoJeu.setCourant(infoJeu.getPremier());
        infoJeu.setDerniereQuestionPositive(false);
        infoJeu.setPrecedent(null);
        infoJeu.emptyIndiceCourrante();

        //Cette proc�dure place la r�f�rence n�ud courant
        //du type-enregistrement InfoJeu sur le premier n�ud. La derni�re r�ponse de
        //l�utilisateur est mise � faux et la r�f�rence sur le n�ud pr�c�dent est mise � nulle.
        //Nous ajoutons � cela une liste instanci�e mais vide pour recueillir les indices donn�s
        //par l�utilisateur. Elle est utilis�e au moment de trouver une erreur de l�utilisateur. Il
        //faut retenir les �O� et �N� de l�utilisateur. Vous pouvez utiliser les constantes d�finies
        //et pr�vues � cet effet.
    }

    public boolean reponseTrouvee() {

        return infoJeu.getCourant().getDroite() == null && infoJeu.getCourant().getGauche() == null;
        // return true if the 2 references in current noeud are null
    }

//    public String getLaChaineActuelle() {
//        if (infoJeu.getCourant().getDroite() == null && infoJeu.getCourant().getGauche() == null) {
//
//            return reponses[infoJeu.getCourant().getIndex()].getReponse();
//
//        } else {
//            return questions.get(infoJeu.getCourant().getIndex());
//        }
//        // Retourne la cha�ne associ�e au n�ud courant. Il faut
//        // prendre l�indice et l�utiliser dans la bonne collection selon que c�est une question ou
//        // une r�ponse.
//    }
    public Object getLaChaineActuelle(){

        /*
         * Strat�gie : On utilise l'op�rateur ternaire pour retourner la bonne
         * cha�ne qui d�pend si c'est une r�ponse ou une question.
         */
        return (reponseTrouvee())

                // Si c'est l'indice d'une r�ponse, il faut la description.
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

        //Cette proc�dure re�oit l�indice de l�utilisateur (O ou
        //N), d�place les r�f�rences de l�attribut de type InfoJeu, ajuste l�attribut bool�en
        //selon l�indice re�u et ajoute le bon caract�re � la liste des indices donn�s (elle est
        //utilis�e par UtilitaireES.demarrerDivinateur).
    }

}
