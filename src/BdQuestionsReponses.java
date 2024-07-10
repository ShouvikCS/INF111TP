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


        //Cette procédure place la référence nœud courant
        //du type-enregistrement InfoJeu sur le premier nœud. La dernière réponse de
        //l’utilisateur est mise à faux et la référence sur le nœud précédent est mise à nulle.
        //Nous ajoutons à cela une liste instanciée mais vide pour recueillir les indices donnés
        //par l’utilisateur. Elle est utilisée au moment de trouver une erreur de l’utilisateur. Il
        //faut retenir les ‘O’ et ‘N’ de l’utilisateur. Vous pouvez utiliser les constantes définies
        //et prévues à cet effet.
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
        // Retourne la chaîne associée au nœud courant. Il faut
        // prendre l’indice et l’utiliser dans la bonne collection selon que c’est une question ou
        // une réponse.
    }

    public boolean deplacerDansArbre(int reponse) {

        return false; // temporary test value

        //Cette procédure reçoit l’indice de l’utilisateur (O ou
        //N), déplace les références de l’attribut de type InfoJeu, ajuste l’attribut booléen
        //selon l’indice reçu et ajoute le bon caractère à la liste des indices donnés (elle est
        //utilisée par UtilitaireES.demarrerDivinateur).
    }
}
