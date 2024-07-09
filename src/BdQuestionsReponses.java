import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class BdQuestionsReponses implements Serializable {
    public static List<String> questions;
    public static List<Reponse> reponses;
    public static InfoJeu infoJeu;
    private Noeud noeudPremier;

    public BdQuestionsReponses() {
        noeudPremier = null;
        infoJeu = new InfoJeu(noeudPremier);
        questions = new ArrayList<String>();
        reponses = new ArrayList<Reponse>();
    }

    public int ajouterQuestionReponse(String question, String reponse) {
        this.questions.add(question);
        this.reponses.add(new Reponse(reponse));
        Reponse rep = new Reponse(reponse);
        rep.addIndices(this.reponses.size()-1);
        Noeud ques = new Noeud(this.questions.size()-1);
        Noeud reps = new Noeud(this.reponses.size()-1);
        ques.setGauche(reps);
        //this.infoJeu.getCourant()= ques;
        return questions.size()-1;
    }
    public int ajouterReponse(Reponse reponse) {
        reponses.add(reponse);
        return reponses.size()-1;
    }


    public boolean estVide() {
        return true;
    }

    public void choisirPremiereQuestion() {
      //  noeudPremier = questions.getFirst();
    }

    public boolean reponseTrouvee() {
        return true;
        // return true if the 2 references in the noeud are null
    }

    public String getLaChaineActuelle() {
        return String.valueOf(reponses);
    }

    public boolean deplacerDansArbre(int reponse) {
        return true;
    }
}
