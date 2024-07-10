import java.io.Serializable;

public class Liste implements Serializable {

    private Noeud premier;
    private Noeud dernier;
    private int nbElement;

    public Liste() {
        this.premier = this.dernier = null;
        this.nbElement = 0;
    }

    public boolean enfiler(Object element) {
        Noeud nouveau = new Noeud(null, element);
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

    private class Noeud {
        public Object donnee;
        public Noeud suivant;

        public Noeud(Noeud next, Object data) {
            this.donnee = data;
            this.suivant = next;
        }

        public String toString() {
            return "Noeud{" + "donnee=" + donnee;
        }
    }
}
