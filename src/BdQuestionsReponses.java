import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class BdQuestionsReponses implements Serializable {
    private static final long serialVersionUID = 2465668457521406705L;
    private  List<String> questions;
    private  Reponse [] reponses;
    private int nbReponses;
    private String nomFic = Constantes.NOM_FICHIER_BD;
    private InfoJeu infoJeu;
    private ArrayList<InfoJeu> infosPrevious;


    public BdQuestionsReponses() {
        infoJeu = new InfoJeu();
        questions = new ArrayList<>();
        reponses = new Reponse[100];
        this.nbReponses = 0;
    }
    public void bdName(String nomFic) {
        this.nomFic = nomFic;
    }
    public String getNomFic() {
        return nomFic;
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

    public void retourQuestion() {
        if (infoJeu.getPrecedent() != null){
            infoJeu = infosPrevious.getLast();
            infosPrevious.removeLast();
        }
    }

//    verifie si la reponse Existe et retourne la reponse (copi� dans BDquestionReponse fournie par le prof)
    public Reponse getReponse(String reponse){
        /*
         * Strat�gie : Fouille lin�aire.  On r�cup�re une Reponse � la fois et
         * on compare l'attribut reponse � la r�ponse re�ue.  La boucle se
         * termine si la collection est parcourue au complet ou que la r�ponse
         * a �t� trouv�e.
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

        // Autrement dit :: Si la taille du tableau de r�ponse est de 0 ou que i
        // == taile, c'est que la r�ponse n'existe pas.
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
        infosPrevious = new ArrayList<>();
    }

    public boolean reponseTrouvee() {

        return infoJeu.getCourant().getDroite() == null && infoJeu.getCourant().getGauche() == null;
        // return true if the 2 references in current noeud are null
    }
// Modification de la methode getLachaineActuelle
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

        infosPrevious.add(new InfoJeu(infoJeu));
        if (reponse == 0) { // Oui = 0; Non = 1

            infoJeu.addIndiceCourrante(Constantes.REPONSE_POSITIVE); // add indice to list
            infoJeu.setDerniereQuestionPositive(true); // set last answer to be true
            infoJeu.setCourant(infoJeu.getCourant().getGauche()); // set noeud courant to be the left ref on current one
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
