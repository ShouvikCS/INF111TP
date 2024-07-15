import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Reponse implements Serializable {
    private String reponse;
    private Liste indices;

    public Reponse(String reponse) {
        this.reponse = reponse;
        this.indices = new Liste();
    }

    public void addIndices(Character indice) {
        this.indices.enfiler(indice);
    }

    public String toString(){
        return reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public Liste getIndices() {
        return indices;
    }

    public void setIndices(Liste indices) {
        this.indices = indices;
    }
}