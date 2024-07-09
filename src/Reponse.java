import java.util.ArrayList;
import java.util.List;

public class Reponse {
    private String reponse;
    private List<Integer> indices;

    public Reponse(String reponse) {
        this.reponse = reponse;
        this.indices = new ArrayList<>();
    }

    public void addIndices(Integer indice) {
        this.indices.add(indice);
    }

    public String toString(){
        return reponse;
    }
}