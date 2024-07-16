import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class BdQuestionsReponses implements Serializable {
    private  List<String> questions;
    private  Reponse [] reponses;
    private int nbReponses;
    private InfoJeu infoJeu;


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

    public void ajouterQuestionReponse(String question, String reponse) {

        int questionIndex = 0;

        if (this.questions.contains(question)) { // if the question already exists in the arraylist, we simply save its index
            questionIndex = this.questions.indexOf(question);
        } else { //otherwise, we add the unique question and the index is (the size of the array - 1)
            this.questions.add(question);
            questionIndex = this.questions.size() - 1;
        }

        this.reponses[this.nbReponses] = new Reponse(reponse);
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
            this.reponses[this.nbReponses].setIndices(listTemp);
        } else if (this.infoJeu.isDerniereQuestionPositive() && !deplacerDansArbre(1)){
            reponses[infoJeu.getCourant().getIndex()].getIndices().enfiler(Constantes.REPONSE_NEGATIVE);
            ques.setDroite(this.infoJeu.getCourant());
            this.infoJeu.getPrecedent().setGauche(ques);
            Liste listTemp = new Liste(infoJeu.getIndicesCourants());
            listTemp.enfiler(Constantes.REPONSE_POSITIVE);
            this.reponses[this.nbReponses].setIndices(listTemp);
        } else {
            ques.setDroite(this.infoJeu.getCourant());
            reponses[infoJeu.getCourant().getIndex()].getIndices().enfiler(Constantes.REPONSE_NEGATIVE);
            this.infoJeu.getPrecedent().setDroite(ques);
            Liste listTemp = new Liste(infoJeu.getIndicesCourants());
            listTemp.enfiler(Constantes.REPONSE_POSITIVE);
            this.reponses[this.nbReponses].setIndices(listTemp);
        }

        nbReponses++;

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

        //Cette procédure place la référence nœud courant
        //du type-enregistrement InfoJeu sur le premier nœud. La dernière réponse de
        //l’utilisateur est mise à faux et la référence sur le nœud précédent est mise à nulle.
        //Nous ajoutons à cela une liste instanciée mais vide pour recueillir les indices donnés
        //par l’utilisateur. Elle est utilisée au moment de trouver une erreur de l’utilisateur. Il
        //faut retenir les ‘O’ et ‘N’ de l’utilisateur. Vous pouvez utiliser les constantes définies
        //et prévues à cet effet.
    }

    public boolean reponseTrouvee() {

        return infoJeu.getCourant().getDroite() == null && infoJeu.getCourant().getGauche() == null;
        // return true if the 2 references in current noeud are null
    }

    public String getLaChaineActuelle() {
        if (infoJeu.getCourant().getDroite() == null && infoJeu.getCourant().getGauche() == null) {

            return reponses[infoJeu.getCourant().getIndex()].getReponse();

        } else {
            return questions.get(infoJeu.getCourant().getIndex());
        }
        // Retourne la chaîne associée au nœud courant. Il faut
        // prendre l’indice et l’utiliser dans la bonne collection selon que c’est une question ou
        // une réponse.
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
