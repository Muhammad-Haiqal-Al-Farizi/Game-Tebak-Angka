import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game {
    private Player player;
    private int targetNumber;
    private int maxGuesses;
    private int currentGuesses;
    private JFrame frame;
    private JTextField guessField;
    private JLabel feedbackLabel;
    private JLabel guessesLabel;

    public Game() {
        this.player = new Player();
        this.targetNumber = generateTargetNumber();
        this.maxGuesses = 10;
        this.currentGuesses = 0;
        setupGUI();
    }

    public void start() {
        frame.setVisible(true);
    }

    private void setupGUI() {
        frame = new JFrame("Tebak Angka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 1));

        JLabel welcomeLabel = new JLabel("Selamat datang di game tebak angka, " + player.getName() + "!");
        frame.add(welcomeLabel);

        guessField = new JTextField();
        frame.add(guessField);

        JButton guessButton = new JButton("Tebak");
        frame.add(guessButton);

        feedbackLabel = new JLabel("Masukkan angka antara 1 dan 100");
        frame.add(feedbackLabel);

        guessesLabel = new JLabel("Tebakan tersisa: " + (maxGuesses - currentGuesses));
        frame.add(guessesLabel);

        guessButton.addActionListener(new GuessButtonListener());
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String guessText = guessField.getText();
            try {
                int guess = Integer.parseInt(guessText);
                currentGuesses++;

                if (guess == targetNumber) {
                    feedbackLabel.setText("Selamat, " + player.getName() + "! Anda berhasil menebak angka!");
                    endGame();
                } else if (guess < targetNumber) {
                    feedbackLabel.setText("Tebakan Anda terlalu rendah.");
                } else {
                    feedbackLabel.setText("Tebakan Anda terlalu tinggi.");
                }

                if (currentGuesses >= maxGuesses) {
                    feedbackLabel.setText("Maaf, " + player.getName() + ". Anda kehabisan tebakan. Angka yang benar adalah " + targetNumber);
                    endGame();
                }

                guessesLabel.setText("Tebakan tersisa: " + (maxGuesses - currentGuesses));
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Masukkan angka yang valid.");
            }
        }
    }

    private void endGame() {
        guessField.setEnabled(false);
    }

    private int generateTargetNumber() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }
}
