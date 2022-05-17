package pl.project.oop.java;

import javax.swing.*;
import java.awt.*;

public class Description {


    public Description() {
        JOptionPane.showMessageDialog(null, getDescriptionList());
    }

    private JButton[] getDescriptionList() {
        int numberOfOrganisms = 11;
        JButton[] organisms = new JButton[numberOfOrganisms];

        organisms[0] = new JButton("Borsch");
        organisms[0].setBackground(OrganismColor.BORSCH);

        organisms[1] = new JButton("Guarana");
        organisms[1].setBackground(OrganismColor.GUARANA);

        organisms[2] = new JButton("Dandelion");
        organisms[2].setBackground(OrganismColor.DANDELION);
        organisms[3] = new JButton("Grass");
        organisms[3].setBackground(OrganismColor.GRASS);

        organisms[4] = new JButton("Wild Berries");
        organisms[4].setBackground(OrganismColor.WILD_BERRIES);

        organisms[5] = new JButton("Antelope");
        organisms[5].setBackground(OrganismColor.ANTELOPE);

        organisms[6] = new JButton("Human");
        organisms[6].setBackground(OrganismColor.HUMAN);

        organisms[7] = new JButton("Fox");
        organisms[7].setBackground(OrganismColor.FOX);

        organisms[8] = new JButton("Sheep");
        organisms[8].setBackground(OrganismColor.SHEEP);

        organisms[9] = new JButton("Wolf");
        organisms[9].setBackground(OrganismColor.WOLF);

        organisms[10] = new JButton("Turtle");
        organisms[10].setBackground(OrganismColor.TURTLE);

                for (int i = 0; i < numberOfOrganisms; i++) {
            organisms[i].setEnabled(false);
            organisms[i].setOpaque(true);
            organisms[i].setBorderPainted(false);
        }
        return organisms;
    }

}
