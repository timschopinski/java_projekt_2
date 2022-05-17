package pl.project.oop.java;

import javax.swing.*;
import java.awt.*;


public class ChoiceList extends JFrame {
    private final pl.project.oop.java.Type.OrganismType[] OrganismTypeList;
    private final JList<String> jList;

    public ChoiceList(int x, int y, pl.project.oop.java.Point point, WorldGUI world) {
        super("All Organisms");
        setBounds(x, y, 100, 300);
        String[] listOfOrganisms = new String[]{"Dandelion", "Guarana", "Borsch", "Grass",
                "Wild Berries", "Antelope", "Fox", "Sheep", "Wolf", "Turtle"};
        OrganismTypeList = new pl.project.oop.java.Type.OrganismType[]{pl.project.oop.java.Type.OrganismType.DANDELION,
                pl.project.oop.java.Type.OrganismType.GUARANA, pl.project.oop.java.Type.OrganismType.BORSCH, pl.project.oop.java.Type.OrganismType.GRASS,
                pl.project.oop.java.Type.OrganismType.WILD_BERRIES, pl.project.oop.java.Type.OrganismType.ANTELOPE,
                pl.project.oop.java.Type.OrganismType.FOX,
                pl.project.oop.java.Type.OrganismType.SHEEP, pl.project.oop.java.Type.OrganismType.WOLF,
                pl.project.oop.java.Type.OrganismType.TURTLE,
        };

        jList = new JList<>(listOfOrganisms);
        jList.setVisibleRowCount(listOfOrganisms.length);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setFont(Font.getFont(Font.SANS_SERIF));
        jList.addListSelectionListener(e -> {

            Organism organism = Organism.createOrganism
                    (OrganismTypeList[jList.getSelectedIndex()], world.getWorld(), point);
            world.getWorld().addOrganism(organism);
            assert organism != null;
            Info.addComment("New Organism was added" + organism.organismToString());
            world.reloadWorld();
            dispose();

        });

        JScrollPane sp = new JScrollPane(jList);
        add(sp);

        setVisible(true);
    }
}

