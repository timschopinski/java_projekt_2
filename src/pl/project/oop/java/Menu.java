//package pl.project.oop.java;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.util.Arrays;
//
//import javax.swing.*;
//
//public class Menu extends WorldGUI{
//    private int BOARD_WIDTH = 10;
//    private int BOARD_HEIGHT = 10;
//    private final JFrame screen;
//    private final JMenuItem start;
//    private final JMenuItem load;
//    private final JMenuItem save;
//    private final JMenuItem exit;
//    private final JMenuItem comments;
//    private final JMenuItem width;
//    private final JMenuItem height;
//    private final JButton newGame;
//    private final JMenuItem organisms;
//    private final JPanel mainMenu;
//    public Menu() {
//
//        screen = new JFrame("Virtual World");
//        screen.setBounds((dimension.width - 750) / 2, (dimension.height - 750) / 2, 750, 750);
//        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//
//        JMenuBar menuBar = new JMenuBar();
//        JMenu menu = new JMenu("Menu");
//        JMenu menuSettings = new JMenu("Settings");
//        JMenu menuColors = new JMenu("Colors");
//        start = new JMenuItem("New Game");
//        load = new JMenuItem("Load");
//        save = new JMenuItem("Save");
//        comments = new JMenuItem("Comments");
//        organisms = new JMenuItem("Organisms");
//        exit = new JMenuItem("Exit");
//        height = new JMenuItem("Height");
//        width = new JMenuItem("Width");
//        start.addActionListener(this);
//        load.addActionListener(this);
//        save.addActionListener(this);
//        comments.addActionListener(this);
//        exit.addActionListener(this);
//        organisms.addActionListener(this);
//        height.addActionListener(this);
//        width.addActionListener(this);
//        menuSettings.add(height);
//        menuSettings.add(width);
//        menuSettings.add(menuColors);
//
//        for (int i = 0; i < listOfOrganisms.length; i++) {
//        colors[i] = new JMenuItem(listOfOrganisms[i]);
//        colors[i].addActionListener(this);
//        menuColors.add(colors[i]);
//        }
//
//        menu.add(start);
//        menu.add(load);
//        menu.add(comments);
//        menu.add(organisms);
//        menu.add(save);
//        menu.add(menuSettings);
//        menu.add(exit);
//        menuBar.add(menu);
//
//
//        mainMenu = new JPanel();
//        mainMenu.setBackground(java.awt.Color.GRAY);
//        mainMenu.setBounds(40,50,200,150);
//        mainMenu.setBackground(Color.lightGray);
//
//        newGame = new JButton("New Game");
//
//        newGame.setBounds(50,100,100,30);
//        newGame.setBackground(Color.WHITE);
//        JButton btn2 = new JButton("Button 2");
//        btn2.setBounds(150,100,100,30);
//        btn2.setBackground(Color.WHITE);
//        newGame.addActionListener(this);
//
//
//        mainMenu.add(newGame);
//        mainMenu.add(btn2);
//        screen.add(mainMenu);
//
//        mainMenu.setLayout(null);
//
//
//        screen.addKeyListener(this);
//        screen.add(mainMenu);
//
//        screen.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent event) {
//        if (Arrays.asList(colors).contains(event.getSource())) {
//            java.awt.Color newColor = JColorChooser.showDialog(null, "Choose Color:", java.awt.Color.WHITE);
//            OrganismColor.setColor(listOfOrganisms[Arrays.asList(colors).indexOf(event.getSource())], newColor);
//        }
//        if (event.getSource() == height) {
//            BOARD_HEIGHT = Integer.parseInt(JOptionPane.showInputDialog(screen,
//                    "Enter world width", "10"));
//        }
//        if (event.getSource() == comments) {
//            String message = Info.getText();
//            JOptionPane.showMessageDialog(null, message);
//        }
//        if (event.getSource() == organisms) {
//            int numberOfOrganisms = 12;
//            JButton[] organisms = new JButton[numberOfOrganisms];
//
//            organisms[0] = new JButton("Borsch");
//            organisms[0].setBackground(OrganismColor.BORSCH);
//
//            organisms[1] = new JButton("Guarana");
//            organisms[1].setBackground(OrganismColor.GUARANA);
//
//            organisms[2] = new JButton("Dandelion");
//            organisms[2].setBackground(OrganismColor.DANDELION);
//            organisms[3] = new JButton("Grass");
//            organisms[3].setBackground(OrganismColor.GRASS);
//
//            organisms[4] = new JButton("Wild Berries");
//            organisms[4].setBackground(OrganismColor.WILD_BERRIES);
//
//            organisms[5] = new JButton("Antelope");
//            organisms[5].setBackground(OrganismColor.ANTELOPE);
//
//            organisms[6] = new JButton("Human");
//            organisms[6].setBackground(OrganismColor.HUMAN);
//
//            organisms[7] = new JButton("Fox");
//            organisms[7].setBackground(OrganismColor.FOX);
//
//            organisms[8] = new JButton("Sheep");
//            organisms[8].setBackground(OrganismColor.SHEEP);
//
//            organisms[9] = new JButton("Wolf");
//            organisms[9].setBackground(OrganismColor.WOLF);
//
//            organisms[10] = new JButton("Turtle");
//            organisms[10].setBackground(OrganismColor.TURTLE);
//
//            organisms[11] = new JButton("Cyber Sheep");
//            organisms[11].setBackground(OrganismColor.CYBER_SHEEP);
//
//
//            for (int i = 0; i < numberOfOrganisms; i++) {
//                organisms[i].setEnabled(false);
//                organisms[i].setOpaque(true);
//                organisms[i].setBorderPainted(false);
//            }
//
//            JOptionPane.showMessageDialog(null, organisms);
//        }
//        if (event.getSource() == width) {
//            BOARD_WIDTH = Integer.parseInt(JOptionPane.showInputDialog(screen,
//                    "Enter world height", "10"));
//        }
//        if (event.getSource() == start || event.getSource() == newGame) {
//            Info.clearComments();
//            world = new World(BOARD_HEIGHT, BOARD_WIDTH, this);
//            world.createWorld();
//            if (BoardGUI != null)
//                mainMenu.remove(BoardGUI);
//
//            start();
//        }
//        if (event.getSource() == load) {
//            Info.clearComments();
//            String nameOfFile = JOptionPane.showInputDialog(screen, "Enter filename:", "test.txt");
//            world = World.loadWorld(nameOfFile);
//            assert getWorld() != null;
//            getWorld().setWorldGUI(this);
//            BoardGUI = new WorldGUI.BoardGUI(world);
//            mainMenu.remove(BoardGUI);
//            start();
//        }
//        if (event.getSource() == save) {
//            String nameOfFile = JOptionPane.showInputDialog(screen, "Enter filename:", "test.txt");
//            getWorld().saveWorld(nameOfFile);
//            Info.addComment("World saved!");
//        }
//        if (event.getSource() == exit) {
//            screen.dispose();
//        }
//    }
//    private void start() {
//        BoardGUI = new BoardGUI(world);
//        mainMenu.add(BoardGUI);
//
//
//        reloadWorld();
//    }
//}
