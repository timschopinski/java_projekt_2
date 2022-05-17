package pl.project.oop.java;

import javax.swing.*;
import java.awt.*;

public class Info {
    public static int roundNumber;

    public static class ProgressBar extends JProgressBar {
        private static JProgressBar progressBar = null;
        public ProgressBar(Container parent) {
            super();
//            parent.removeAll();
            progressBar = new JProgressBar();
            progressBar.setFont(new Font("Serif", Font.PLAIN, 20));
            progressBar.setMaximum(5);
            progressBar.setMinimum(0);
            progressBar.setOpaque(true);
            progressBar.setBorderPainted(false);
            progressBar.setBounds(200, 580, 200, 200);
            parent.add(progressBar);

            setVisible(true);
        }

        public static JProgressBar getProgressBar() {
            return progressBar;
        }

    }

    public static class RoundLabel extends JLabel {
        private static JLabel roundLabel = null;

        public RoundLabel(Container parent) {
            super();
            parent.removeAll();
            roundLabel = new JLabel();
            roundLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            roundLabel.setText("Round: 0");
            roundLabel.setBounds(10, 580, 400, 200);
            roundLabel.setForeground(Color.BLACK);

            parent.add(roundLabel);

            setVisible(true);
        }
        public RoundLabel(Container parent, int round) {
            super();
            parent.removeAll();
            roundLabel = new JLabel();
            roundLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            roundLabel.setText("Round: " + String.valueOf(round));
            roundLabel.setBounds(10, 580, 400, 200);
            roundLabel.setForeground(Color.BLACK);

            parent.add(roundLabel);

            setVisible(true);
        }

        public static JLabel getRoundLabel() {
            return roundLabel;
        }
    }
    public static void updateLabel() {
        RoundLabel.getRoundLabel().setText("Round: " + String.valueOf(roundNumber));
    }
    public static void updateProgressBar(int value) {
        ProgressBar.getProgressBar().setValue(value);
    }

    private static String text = "";

    public static void addComment(String comment) {
        text += comment + "\n";
    }

    public static String getText() {
        return text;
    }

    public static void clearComments() {
        text = "";
    }
}
