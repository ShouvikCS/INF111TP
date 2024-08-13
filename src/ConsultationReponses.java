import javax.swing.*;
import java.awt.*;

public class ConsultationReponses extends JDialog {

    BdQuestionsReponses bd;

    public ConsultationReponses(BdQuestionsReponses bd) {
        this.bd = bd;
        setLayout(new BorderLayout());
        JPanel layoutPanel = new JPanel();
        JLabel title = new JLabel("Consultation Reponses");
        layoutPanel.setLayout(new BoxLayout(layoutPanel, BoxLayout.PAGE_AXIS));
        JPanel gridPanel = new JPanel(new GridLayout( (int) Math.ceil(bd.getNbReponses() / 2.0), 2, 15, 15));

        for (Reponse reponse : bd.getReponses()) {
            if (reponse != null) {
                ImageIcon imageIcon = reponse.getImage();
                JLabel reponseLabel = new JLabel(reponse.getReponse(), imageIcon, JLabel.CENTER);
                reponseLabel.setAlignmentX(CENTER_ALIGNMENT);
                reponseLabel.setVerticalTextPosition(JLabel.TOP);
                reponseLabel.setHorizontalTextPosition(JLabel.CENTER);
                gridPanel.add(reponseLabel);
            }
        }
        layoutPanel.add(title);
        layoutPanel.add(gridPanel);

        JButton closeButton = new JButton("Fermer");
        layoutPanel.add(closeButton);
        closeButton.setSize(500, 150);
        closeButton.addActionListener(e -> this.dispose());
        closeButton.setAlignmentX(CENTER_ALIGNMENT);

        JScrollPane scrollPane = new JScrollPane(layoutPanel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void openConsultation() {
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
//        this.

        this.setVisible(true);
    }

}
