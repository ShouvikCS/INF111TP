import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Reponse implements Serializable {
    private String reponse;
    private Liste indices;
    private ImageIcon image;

    public Reponse(String reponse, ImageIcon image) {
        this.reponse = reponse;
        this.indices = new Liste();
        this.image = image;
    }
//    to convert getLachaineActuelle(String) to Classe Reponse
    public static Reponse convertToString(String reponse, String cheminImage) {
        String rep = reponse.trim();
        ImageIcon image = new ImageIcon(cheminImage);
        return new Reponse(rep, image);
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
    public ImageIcon getImage() {
        return image;
    }
    public void setImage(ImageIcon image) {
        this.image = image;
    }
}