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
        Noeud ques = new Noeud(this.questions.size()-1);
        Noeud reps = new Noeud(nbReponses);
        ques.setGauche(reps);
        if (estVide()) {
            this.infoJeu.setPremier(ques);
            this.infoJeu.setCourant(ques);
            this.infoJeu.setDerniereQuestionPositive(true);
        } else if (!this.infoJeu.isDerniereQuestionPositive() && this.infoJeu.getCourant().getDroite() == null && this.infoJeu.getCourant().getGauche() != null){
            this.infoJeu.getCourant().setDroite(ques);
            this.infoJeu.getCourant().getDroite().setGauche(reps);
        } else if (this.infoJeu.isDerniereQuestionPositive() && !deplacerDansArbre(1)){
            ques.setDroite(this.infoJeu.getCourant());
            this.infoJeu.getPrecedent().setGauche(ques);
        } else {
            ques.setDroite(this.infoJeu.getCourant());
            this.infoJeu.getPrecedent().setDroite(ques);
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
        infoJeu.setDerniereQuestionPositive(false);;
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
        System.out.println(reponse); // Oui = 0; Non = 1
        if (reponse == 0) {
            infoJeu.addIndiceCourrante("O");
            infoJeu.setDerniereQuestionPositive(true);
            infoJeu.setCourant(infoJeu.getCourant().getGauche());
            this.reponses[this.infoJeu.getCourant().getIndex()].addIndices(Constantes.REPONSE_POSITIVE);

            return true;
        } else {
            this.reponses[this.infoJeu.getCourant().getIndex()].addIndices(Constantes.REPONSE_NEGATIVE);

            if(infoJeu.getCourant().getGauche() != null){
                infoJeu.addIndiceCourrante("N");
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
