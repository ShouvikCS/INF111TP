import java.io.Serializable;

public class InfoJeu implements Serializable {
    private Noeud premier, courant, precedent;
    private boolean derniereQuestionPositive;
    private Liste indicesCourrantes;

    public InfoJeu (){
        this.premier = null;
        this.courant = null;
        this.precedent = null;
        this.derniereQuestionPositive = false;
        this.indicesCourrantes = new Liste();
    }

    public InfoJeu(InfoJeu infoJeu){
        this.premier = infoJeu.premier;
        this.courant = infoJeu.courant;
        this.precedent = infoJeu.precedent;
        this.derniereQuestionPositive = infoJeu.derniereQuestionPositive;
        this.indicesCourrantes = infoJeu.indicesCourrantes;
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
    public void addIndiceCourrante(Object noeud) {
        indicesCourrantes.enfiler(noeud);
    }

    public void emptyIndiceCourrante(){
        indicesCourrantes = new Liste();
    }

    public Liste getIndicesCourants() {
        return indicesCourrantes;
    }

}
