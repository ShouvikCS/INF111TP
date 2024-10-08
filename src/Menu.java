import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static java.io.File.*;

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

//        Ajout des �couteurs d'options pour les Item
        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cadre.setBd(new BdQuestionsReponses());
                UtilitaireES.demanderReponseValide(cadre.getBd());
                if (cadre.getBd().estVide() == false) {
                    JOptionPane.showMessageDialog(cadre, "Rentrez un nom pour la bd.");
                    String nomFichier = UtilitaireFichier.nomFichierValide(Constantes.NOM_FICHIER_BD, UtilitaireFichier.SAUVE, "bin");
                    if (nomFichier != null) {
                        cadre.setBdName(nomFichier);
                        UtilitaireFichier.sauvegarde(cadre.getBd(), nomFichier);
                        cadre.setBd(cadre.getBd());
                    }
                }
            }
        });
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File fichier = UtilitaireFichier.obtenirFic("", "bin");
                if (fichier != null) {
                    try (FileInputStream file = new FileInputStream(fichier)) {
                        BdQuestionsReponses bd = UtilitaireFichier.obtenirBd(file);
                        if (bd != null) {
                            cadre.setBd(bd);
                            cadre.setBdName(fichier.getName());
                            cadre.setBd(bd);
                        }
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(null, ":" + e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        mntmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BdQuestionsReponses bd = cadre.getBd();
                if (bd == null) {
                    JOptionPane.showMessageDialog(cadre, "La BD doit exister !", "Bd vide", JOptionPane.ERROR_MESSAGE);
                } else {
                    String temp = cadre.getBdName();
                    if (temp.length() > 4) {
                        temp = temp.substring(0, temp.length() - 4);
                    }
                    String fileName = UtilitaireFichier.nomFichierValide(temp, UtilitaireFichier.SAUVE, "bin");

                    if (fileName != null) {
                        UtilitaireFichier.sauvegarde(bd, fileName);
                    }

                }
            }
        });

        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(cadre, "Merci d'Avoir joueur � notre Jeu! ", "Fin", JOptionPane.INFORMATION_MESSAGE);
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
