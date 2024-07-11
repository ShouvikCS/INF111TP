import java.io.Serializable;
public class InfoJeu implements Serializable {
    private Noeud premier, courant, precedent;
    boolean derniereQuestionPositive;
    public InfoJeu (){
        this.premier = null;
        this.courant = null;
        this.precedent = null;
        this.derniereQuestionPositive = false;
    }
    public Noeud getPremier() {
        return premier;
    }
    public void setPremier(Noeud premier) {
        this.premier = premier;
    }
    public Noeud getCourant() {
        return courant;
    }
    public void setCourant(Noeud courant) {
        this.precedent = this.courant;
        this.courant = courant;
    }
    public Noeud getPrecedent() {
        return precedent;
    }
    public void setPrecedent(Noeud precedent) {
        this.precedent = precedent;
    }

    public boolean isDerniereQuestionPositive() {
        return derniereQuestionPositive;
    }
    public void setDerniereQuestionPositive(boolean derniereQuestionPositive) {
        this.derniereQuestionPositive = derniereQuestionPositive;
    }

}
