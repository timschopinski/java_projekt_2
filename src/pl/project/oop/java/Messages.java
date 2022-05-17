package pl.project.oop.java;

import javax.swing.*;
import java.awt.*;

public class Messages extends JFrame {
    private final JList jList;

    public Messages() {
        super("Comments");
        String[] messages = Info.getText().split("\n");
        setBounds(300, 300, 500, 300);

        jList = new JList(messages);
        jList.setVisibleRowCount(20);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setFont(Font.getFont(Font.SANS_SERIF));

        JScrollPane sp = new JScrollPane(jList);
        add(sp);

        setVisible(true);
    }
}