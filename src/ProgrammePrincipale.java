import javax.swing.*;

public class ProgrammePrincipale {
    public static void main(String[] args) {
        Thread guiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new CadreDivinateur());
            }
        });
        guiThread.start();
    }
}