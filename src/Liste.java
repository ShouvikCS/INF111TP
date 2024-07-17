import java.io.Serializable;

public class Liste implements Serializable {

    private ListeNoeud premier;
    private ListeNoeud dernier;
    private int nbElement;

    public Liste() {
        this.premier = this.dernier = null;
        this.nbElement = 0;
    }

    public Liste(Liste other) {
        this();
        ListeNoeud current = other.premier;
        while (current != null) {
            this.enfiler(current.donnee);
            current = current.suivant;
        }
    }

    public void enfiler(Object element) {
        ListeNoeud nouveau = new ListeNoeud(null, element);
        if (this.dernier == null) {
            this.premier = this.dernier = nouveau;
            this.nbElement = 1;
            return;
        }
        this.dernier.suivant = nouveau;   //  1
        this.dernier = nouveau; //  2
        nbElement++;
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

    public Object get(int index) {
        if (index < 0 || index >= nbElement) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + nbElement);
        }
        ListeNoeud current = premier;
        for (int i = 0; i < index; i++) {
            current = current.suivant;
        }
        return current.donnee;
    }


    public void vider() {
        while (!estVide())
            defiler();
    }


    public int taille() {

        return nbElement;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Liste: ");
        ListeNoeud current = premier;
        while (current != null) {
            result.append(current.donnee).append(", ");
            current = current.suivant;
        }
        if (result.length() > 7) {
            result.setLength(result.length() - 2); // Remove the last comma and space
        } else {
            result.append("empty");
        }
        return result.toString();
    }



    private class ListeNoeud implements Serializable{
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
