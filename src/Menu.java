import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Menu extends JMenuBar {
    private CadreDivinateur cadre;
    public Menu(CadreDivinateur cadre) {
        this.cadre = cadre;
        initialiserComposant();
    }
    private void initialiserComposant() {
        //        Creation du menu
        JMenuBar mb = new JMenuBar();
        JMenu mnFile = new JMenu("Fichier");
        JMenuItem mntmNew = new JMenuItem("Nouveau");
        JMenuItem mntmOpen = new JMenuItem("Ouvrir");
        JMenuItem mntmSave = new JMenuItem("Sauver sous");
        JMenuItem mntmExit = new JMenuItem("Quitter");

//        Ajout des écouteurs d'options pour les Item
        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadre.setBd(new BdQuestionsReponses());
                UtilitaireES.demanderReponseValide(cadre.getBd());
                JOptionPane.showMessageDialog(cadre, "Nom de la BD: ");
                String nomFichier = UtilitaireFichier.nomFichierValide(Constantes.NOM_FICHIER_BD, UtilitaireFichier.SAUVE, "bin");
                if(nomFichier!=null){
                    cadre.setBdName(nomFichier);
                    UtilitaireFichier.sauvegarde(cadre.getBd(), nomFichier);
                    cadre.setBd(cadre.getBd());
                }
            }
        });
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               File fichier = UtilitaireFichier.obtenirFic(Constantes.NOM_FICHIER_BD, "bin");
               if (fichier!=null){
                   try (FileInputStream file = new FileInputStream(fichier)) {
                       BdQuestionsReponses bd = UtilitaireFichier.obtenirBd(file);
                       if(bd!=null){
                           cadre.setBdName(fichier.getAbsolutePath());
                           cadre.setBd(bd);
                       }
                       else {
                           JOptionPane.showMessageDialog(null, "Le format de fichier est invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                       }
                   } catch (IOException e1){
                       JOptionPane.showMessageDialog(null, "Erreur lors de l'ouverture du fichier : " + e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                   }
               }
            }
        });
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(cadre, "Merci d'Avoir joueur à notre Jeu! ", "fin", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        mnFile.add(mntmNew);
        mnFile.add(mntmOpen);
        mnFile.add(mntmSave);
        mnFile.add(mntmExit);
        mb.add(mnFile);
        this.cadre.setJMenuBar(mb);
    }

}
