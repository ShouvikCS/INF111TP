import java.util.ArrayList;
import java.util.List;

public class Reponse {
    private String reponse;
    private List<String> indices;

    public Reponse(String reponse) {
        this.reponse = reponse;
        this.indices = new ArrayList<>();
    }

    public void addIndices(String indice) {
        this.indices.add(indice);
    }

}