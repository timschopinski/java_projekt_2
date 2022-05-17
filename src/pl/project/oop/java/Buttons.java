package pl.project.oop.java;

import java.awt.*;

import javax.swing.*;

public class Buttons {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton btn = new JButton("Click to close!");
        frame.setContentPane(btn);
        btn.addActionListener(e -> {
            frame.dispose();
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.pack();
        frame.setVisible(true);
    }
}