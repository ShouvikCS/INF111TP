import java.io.Serializable;
public class InfoJeu implements Serializable {
    Noeud premier, courant, precedent;
    boolean dernierQuestionPositive;
    public InfoJeu (){
        this.premier = null;
        this.courant = null;
        this.precedent = null;
        this.dernierQuestionPositive = false;
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
        this.courant = courant;
    }
    public Noeud getPrecedent() {
        return precedent;
    }
    public void setPrecedent(Noeud precedent) {
        this.precedent = precedent;
    }

    public boolean isDernierQuestionPositive() {
        return dernierQuestionPositive;
    }
    public void setDernierQuestionPositive(boolean dernierQuestionPositive) {
        this.dernierQuestionPositive = dernierQuestionPositive;
    }

}
