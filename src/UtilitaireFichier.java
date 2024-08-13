import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * Classe utilitaire pour la r�cup�ration et la sauvegarde de la bd
 * dans le jeu dedinateur (voir �nonc� tp3 partie 3).
 *
 * @author Pierre B�lisle
 * @version (Copyright 2017)
 *
 * Tout le code a �t� �crit par l'auteur pour l'�cole de technologie sup�rieure
 * de montr�al (�ts).  Toute utilisation ou reproduction, en tout ou en partie,
 * doit mentionner l'�cole et l'auteur.
 *
 */
public class UtilitaireFichier {

    // Mode d'ouverture possible du fileChooser.
    // A un effet sur le titre de la bo�te.
    public static final int OUVRE = 0;
    public static final int SAUVE = 1;

    // String utilis�e par d�faut.
    public static final String NOM_EXTENSION = "bin";
    public static final String NOM_FIC_DEFAULT = "aucune bd";

    /**
     * SAISIR NOM FICHIER VALIDE
     *
     * Permet � l'utilisateur de s�lectionner un nom de fichier pour
     * ouvrir une bd ant�rieurement sauvegard�e.
     *
     * @param filtre Utilise le nom comme filtre.
     * @param mode OUVRE ou SAUVE selon ce � quoi sert le nom de fichier.
     * @param extension L'extension du filtre.
     *
     * @return La cha�ne saisie � l'aide du FileChooser ou null.
     */
    public static String nomFichierValide(String filtre, int mode, String extension){

        /*
         *STRATEGIE : On utilise le FileChooser de Java qui permet de
         *s�lectionner un nom de fichier  parmis un filtre donn�.  Le filtre utilis�
         * est le nom de fichier finissant par l'extension fournie.
         */
        //Cr�ation du s�lectionneur de fichier
        JFileChooser fc = new JFileChooser(".");

        //On filtre seulement les fichiers avec l'extension
//        FileNameExtensionFilter filter =
//                new FileNameExtensionFilter(filtre, extension);

        // Au lieu d'utiliser le nom du fichier comme description pour le filtre, on utilise une description appropriee selon le cas
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(mode == 1 ? "Binary Files" : "Image Files", extension);

//        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);

        // On laissera la fen�tre de dialogue tant que ce ne sera pas valide.
        boolean valide = false;

        // Tant que ce n'est pas valide (avec la bonne extension) ou
        // que l'utilisateur annule.
        while(!valide){

            //On affiche la fen�tre de dialogue et on attend la r�ponse.
            int returnVal;

            // En mode ouverture.
            if(mode == OUVRE)
                returnVal = fc.showOpenDialog(null);

                // Ou en mode sauvegarde (SAUVE).
            else{

                fc.setSelectedFile(new File(filtre+ "." + extension));

                returnVal = fc.showSaveDialog(null);
            }


            //Si l'utilisateur a choisi un fichier, on valide l'extension.
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                filtre = fc.getSelectedFile().getAbsolutePath();

                valide = extensionValide(filtre,"."+extension);

                if(valide){

                    //On change le r�pertoire courant par celui du fichier qui vient d'�tre lu
                    System.setProperty("user.dir",fc.getSelectedFile().getParent());
                }
            }

            //Si pas de fichier s�lectionn�, on ar�te
            else{

                filtre = null;
                valide = true;
            }

        }

        return filtre;

    }

    /**
     *Permet de v�rifier que l'extension du nom de fichier est valide.
     *
     *Un message est affich� si l'extension est invalide.
     *
     * @param nomFic Le nom du fichier contenant l'extension
     * @param  extension L'extension qui doit terminer le nom de fichier
     */
    public static boolean extensionValide(String nomFic, String extension){

        boolean valide = false;

        // V�rification des dernier caract�re du nom de fichier avec l'extension.
        if(!(nomFic.endsWith(extension)))
            JOptionPane.showMessageDialog(null,"Extension invalide");

        else{
            valide = true;
        }
        return valide;
    }


    /**
     * M�thode qui retourne le fichier
     * s�lectionn� pas l'utilisateur ou null.
     *
     * @param nomFiltre Permet de montrer juste les extensions d�sir�es.
     * @param filtre
     * @return null si le nom n'est pas valide ou annuler.
     */
    public static File obtenirFic(String nomFiltre, String filtre){

        /*
         * Strat�gie : On utilise JFileChooser pour obtenir le fichier..
         */

        //Cr�ation du s�lectionneur de fichier
        JFileChooser fc = new JFileChooser(".");

        // Retourne null si le fichier n'est pas s�lectionn�,
        File fic = null;

        // On filtre seulement les fichiers avec l'extension fournie.
        // Comme en haut, nous utilisons une description approprie pour le filtre au lieu d'avoir le nom du fichier
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("Binary Files", filtre);

        fc.addChoosableFileFilter(filter);
        fc.setFileFilter(filter);


        // On obtient le nom du fichier � ouvrir.
        int nb = fc.showOpenDialog(null);

        // Seulement si le fichier est choisi.
        if(nb == JFileChooser.APPROVE_OPTION)
            fic = fc.getSelectedFile();

        return fic;
    }

    /**
     * Tente d'ouvrir le fichier contenu dans le stream re�u.  S'il n'existe pas,
     * la bd est vide.
     */
    public static BdQuestionsReponses obtenirBd(FileInputStream fic){

        /*
         * Strat�gie : On utilise  un FileInputStream qui permet de lire
         * la bd d'un coup, (comme elle a �t� sauvegard�e).
         */
        BdQuestionsReponses bd = null;

        try {

            // Ouverture du tampon logique.
            ObjectInputStream tampon = null;
            tampon = new ObjectInputStream(fic);

            bd =  (BdQuestionsReponses)tampon.readObject();

            tampon.close();

        }

        // Si le fichier n'existe pas, on s'assure que tout est initialis�.
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        // Probl�me lors de la lecture.  On arr�te.
        catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Format de fichier invalide");
            e.printStackTrace();
        }


        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Format de fichier invalide");
            e.printStackTrace();
        }

        return bd;
    }

    /**
     * Sauvegarde la bd dans le fichier nomm� par NOM_FICHIER_BD.
     *
     */
    public static void sauvegarde(BdQuestionsReponses bd, String nomFic){

        /*
         * Strat�gie : On utilise  un FileOutputStream qui permet de lire
         * la bd d'un coup.
         */
        FileOutputStream fic;
        ObjectOutputStream tampon = null;

        try {

            //Cr�e le fichier
            fic = new FileOutputStream(nomFic);

            //Ouverture du tampon d'�criture
            tampon = new ObjectOutputStream(fic);
            tampon.writeObject(bd);
            tampon.close();

        } catch (FileNotFoundException e1) {

            e1.printStackTrace();

            // Une erreur de lecture, on d�truit le fichier.
        } catch (IOException e) {

            // On obtient le chemin du fichier pour le d�truire.
            Path path =
                    FileSystems.getDefault().getPath(nomFic);

            try {

                tampon.close();
                Files.delete(path);

            } catch (IOException e1) {

                e1.printStackTrace();
            }

            e.printStackTrace();
        }

    }
}
