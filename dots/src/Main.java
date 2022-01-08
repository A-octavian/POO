import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private int n;
    private String redName, blueName;

    private JFrame frame;
    private JLabel sizeError;

    private JRadioButton[] sizeButton;

    ButtonGroup sizeGroup;

    public Main() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        sizeButton = new JRadioButton[8];
        sizeGroup = new ButtonGroup();
        for(int i=0; i<8; i++) {
            String size = String.valueOf(i+3);
            sizeButton[i] = new JRadioButton(size + " x " + size);
            sizeGroup.add(sizeButton[i]);
        }
    }

    private JLabel getEmptyLabel(Dimension d) {
        JLabel label = new JLabel();
        label.setPreferredSize(d);
        return label;
    }

    private boolean startGame;


    private ActionListener submitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for(int i=0; i<8; i++) {
                if(sizeButton[i].isSelected()) {
                    n = i+3;
                    startGame = true;
                    return;
                }
            }
            sizeError.setText("Trebuie sa selectati dimensiunea tablei inainte de a continua");
        }
    };

    public void initGUI() {



        JPanel grid = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;

        grid.add(getEmptyLabel(new Dimension(500,25)), constraints);

        JPanel modePanel = new JPanel(new GridLayout(2, 2));
        modePanel.setPreferredSize(new Dimension(400, 50));
        grid.add(modePanel, constraints);
        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500,25)), constraints);
        sizeError = new JLabel("", SwingConstants.CENTER);
        sizeError.setForeground(Color.RED);
        sizeError.setPreferredSize(new Dimension(500, 30));
        grid.add(sizeError, constraints);
        ++constraints.gridy;
        JLabel messageLabel = new JLabel("Selectati dimensiunea tablei:");
        messageLabel.setPreferredSize(new Dimension(400, 50));
        grid.add(messageLabel, constraints);
        ++constraints.gridy;

        JPanel sizePanel = new JPanel(new GridLayout(4, 2));
        sizePanel.setPreferredSize(new Dimension(400, 100));
        for(int i=0; i<8; i++)
            sizePanel.add(sizeButton[i]);
        sizeGroup.clearSelection();
        grid.add(sizePanel, constraints);
        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);
        JButton submitButton = new JButton("Start Game");
        submitButton.addActionListener(submitListener);
        ++constraints.gridy;
        grid.add(submitButton, constraints);
        ++constraints.gridy;
        grid.add(getEmptyLabel(new Dimension(500, 25)), constraints);
        ++constraints.gridy;
        frame.setContentPane(grid);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Dots and Boxes");

        startGame = false;
        while(!startGame) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new GamePlay(this, frame, n, redName, blueName);
    }

    public static void main(String[] args) {
        new Main().initGUI();
    }

}
