public class Noeud {
    private int index;
    private Noeud gauche, droite;
    public Noeud(int index) {
        this.index = index;
        this.gauche = null;
        this.droite = null;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public Noeud getGauche() {
        return gauche;
    }
    public void setGauche(Noeud gauche) {
        this.gauche = gauche;
    }
    public Noeud getDroite() {
        return droite;
    }
    public void setDroite(Noeud droite) {
        this.droite = droite;
    }


}
