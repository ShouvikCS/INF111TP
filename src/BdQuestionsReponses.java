import java.util.ArrayList;
import java.util.List;
public class BdQuestionsReponses {
    public static List<String> questions;
    public static List<Reponse> reponses;
    public static InfoJeu infoJeu;
    private Noeud noeudPremier;

    public BdQuestionsReponses() {
        noeudPremier = null;
        infoJeu = new InfoJeu(noeudPremier);
        questions = new ArrayList<>();
        reponses = new ArrayList<>();
    }

    public static int ajouterQuestion(String question) {
        questions.add(question);
        return questions.size()-1;
    }
    public static int ajouterReponse(Reponse reponse) {
        reponses.add(reponse);
        return reponses.size()-1;
    }


    public boolean estVide() {
        return questions.equals("test");
    }

    public void choisirPremiereQuestion() {

    }

    public boolean reponseTrouvee() {
        return true;
    }

    public String getLaChaineActuelle() {
        return String.valueOf(reponses);
    }

    public boolean deplacerDansArbre(int reponse) {
        return true;
    }
}
