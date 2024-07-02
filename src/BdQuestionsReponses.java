import java.util.ArrayList;
import java.util.List;
public class BdQuestionsReponses {
    public static List<String> questions = new ArrayList<>();
    public static List<String> reponses = new ArrayList<>();
    public static InfoJeu infoJeu;

    public static int ajouterQuestion(String question) {
        questions.add(question);
        return questions.size()-1;
    }
    public static int ajouterReponse(String reponse) {
        reponses.add(reponse);
        return reponses.size()-1;
    }


    public boolean estVide() {
        return questions.equals("");
    }

    public void choisirPremiereQuestion() {
    }

    public boolean reponseTrouvee() {
        return true;
    }

    public String getLaChaineActuelle() {
        return reponses;
    }

    public boolean deplacerDansArbre(int reponse) {
        return true;
    }
}
