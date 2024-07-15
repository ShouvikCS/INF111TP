import java.io.Serializable;

public class Liste implements Serializable {

    private ListeNoeud premier;
    private ListeNoeud dernier;
    private int nbElement;

    public Liste() {
        this.premier = this.dernier = null;
        this.nbElement = 0;
    }

    public boolean enfiler(Object element) {
        ListeNoeud nouveau = new ListeNoeud(null, element);
        if (this.dernier == null) {
            this.premier = this.dernier = nouveau;
            this.nbElement = 1;
            return true;
        }
        this.dernier.suivant = nouveau;   //  1
        this.dernier = nouveau; //  2
        nbElement++;
        return true;
    }

    public Object defiler() {
        if (estVide()) {
            return null;
        }
        Object premierElement = this.premier.donnee;
        this.premier = this.premier.suivant;
        nbElement--;
        return premierElement;
    }


    public boolean estVide() {

        return nbElement == 0;
    }


    public void vider() {
        while (!estVide())
            defiler();
    }


    public int taille() {

        return nbElement;
    }

    private class ListeNoeud {
        public Object donnee;
        public ListeNoeud suivant;

        public ListeNoeud(ListeNoeud next, Object data) {
            this.donnee = data;
            this.suivant = next;
        }

        public String toString() {
            return "Noeud{" + "donnee=" + donnee;
        }
    }
}
