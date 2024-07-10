import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
public class BdQuestionsReponses implements Serializable {
    public  List<String> questions;
    public  Reponse [] reponses;
    public int nbReponses;
    public InfoJeu infoJeu;


    public BdQuestionsReponses() {
        infoJeu = new InfoJeu();
        questions = new ArrayList<String>();
        reponses = new Reponse[100];
        this.nbReponses = 0;
    }

    public void ajouterQuestionReponse(String question, String reponse) {
        this.questions.add(question);
        this.reponses[this.nbReponses] = new Reponse(reponse);
//        Reponse rep = new Reponse(reponse);
        Noeud ques = new Noeud(this.questions.size()-1);
        Noeud reps = new Noeud(nbReponses);
        nbReponses++;
        ques.setGauche(reps); // ? uncertain , when do you set the right reference then?
//        this.infoJeu.courant = this.infoJeu.premier = ques;
    }
    // Verifie si la reponse existe dans tableau de reponse
    public boolean reponseExiste(String reponse) {
        for (int i = 0; i < this.reponses.length; i++) {
            if (this.reponses[i] == null) {
                break;
            } else if (this.reponses[i].equals(reponse)) {
                return true;
            }
        }
        return false;
    }


    public boolean estVide() {
        return nbReponses == 0;
    }

    public void choisirPremiereQuestion() {
//        Noeud ques = new Noeud(this.questions.size()-1);
//        Noeud reps = new Noeud(nbReponses);

        infoJeu.courant = infoJeu.premier;
        infoJeu.dernierQuestionPositive = false;
        infoJeu.precedent = null;


        //Cette proc�dure place la r�f�rence n�ud courant
        //du type-enregistrement InfoJeu sur le premier n�ud. La derni�re r�ponse de
        //l�utilisateur est mise � faux et la r�f�rence sur le n�ud pr�c�dent est mise � nulle.
        //Nous ajoutons � cela une liste instanci�e mais vide pour recueillir les indices donn�s
        //par l�utilisateur. Elle est utilis�e au moment de trouver une erreur de l�utilisateur. Il
        //faut retenir les �O� et �N� de l�utilisateur. Vous pouvez utiliser les constantes d�finies
        //et pr�vues � cet effet.
    }

    public boolean reponseTrouvee() {

        return infoJeu.courant.getDroite() == null && infoJeu.courant.getGauche() == null;
        // return true if the 2 references in current noeud are null
    }

    public String getLaChaineActuelle() {
        if (infoJeu.courant.getDroite() == null && infoJeu.courant.getGauche() == null) {

            return reponses[infoJeu.courant.getIndex()].getReponse();

        } else {
            return questions.get(infoJeu.courant.getIndex());
        }
        // Retourne la cha�ne associ�e au n�ud courant. Il faut
        // prendre l�indice et l�utiliser dans la bonne collection selon que c�est une question ou
        // une r�ponse.
    }

    public boolean deplacerDansArbre(int reponse) {

        return false; // temporary test value

        //Cette proc�dure re�oit l�indice de l�utilisateur (O ou
        //N), d�place les r�f�rences de l�attribut de type InfoJeu, ajuste l�attribut bool�en
        //selon l�indice re�u et ajoute le bon caract�re � la liste des indices donn�s (elle est
        //utilis�e par UtilitaireES.demarrerDivinateur).
    }
}
