package pl.project.oop.java;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class WorldGUI implements ActionListener, KeyListener {
    public String[] listOfOrganisms = new String[]{"Human", "Dandelion", "Guarana", "Borsch", "Grass",
            "Wild Berries", "Antelope", "Fox", "Sheep", "Wolf", "Turtle"};
    private int BOARD_WIDTH = 10;
    private int BOARD_HEIGHT = 10;
    private boolean isHexagonal = false;
    private final JFrame screen;
    private final JMenuItem restart;
    private final JMenuItem save1;
    private final JMenuItem exit;
    private final JMenuItem comments;
    private final JMenuItem width;
    private final JMenuItem height;
    private final JButton start2;
    private final JButton load2;
    private final JMenuItem organisms;
    private final JLabel mainText;
    private final JRadioButton mode;
    private final JMenu menu;
    private BoardGUI BoardGUI = null;
    Save save = new Save(getWorld());


    JMenuItem[] colors = new JMenuItem[12];

    private final JPanel mainMenu;
    private World world;

    public WorldGUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        screen = new JFrame("Virtual World");
        screen.setBounds((dimension.width - 750) / 2, (dimension.height - 750) / 2, 750, 750);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        JMenu menuSettings = new JMenu("Settings");
        JMenu menuColors = new JMenu("Colors");
        restart = new JMenuItem("Restart");
        save1 = new JMenuItem("Save");
        comments = new JMenuItem("Comments");
        organisms = new JMenuItem("Organisms");
        exit = new JMenuItem("Exit");
        height = new JMenuItem("Height");
        width = new JMenuItem("Width");
        mode = new JRadioButton("Hexagonal");
        mainText = new JLabel();
        restart.addActionListener(this);
        save1.addActionListener(this);
        comments.addActionListener(this);
        exit.addActionListener(this);
        organisms.addActionListener(this);
        height.addActionListener(this);
        width.addActionListener(this);
        mode.addActionListener(this);
        menuSettings.add(mode);
        menuSettings.add(height);
        menuSettings.add(width);
        menuSettings.add(menuColors);


        for (int i = 0; i < listOfOrganisms.length; i++) {
            colors[i] = new JMenuItem(listOfOrganisms[i]);
            colors[i].addActionListener(this);
            menuColors.add(colors[i]);
        }


        menu.add(menuSettings);
        menuBar.add(menu);
        screen.setJMenuBar(menuBar);
        screen.setLayout(new CardLayout());


        mainMenu = new JPanel();
        mainMenu.setBackground(java.awt.Color.GRAY);
        mainMenu.setBounds(200,400,200,150);
        mainText.setText("Press 'New Game' to start the simulation");
        mainText.setBounds(230, 50, 400, 200);

        mainMenu.add(mainText);


        start2 = new JButton("New Game");
        start2.setBounds(250,200,200,50);
        start2.setBackground(Color.WHITE);
        start2.setFocusPainted(false);
        start2.addActionListener(this);


        load2 = new JButton("Load");
        load2.setBounds(250,300,200,50);
        load2.setDefaultCapable(false);
        load2.setBackground(Color.WHITE);
        load2.addActionListener(this);

        mainMenu.add(start2);
        mainMenu.add(load2);
        mainMenu.setLayout(null);

        screen.add(mainMenu);
        screen.addKeyListener(this);
        screen.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (Arrays.asList(colors).contains(event.getSource())) {
            java.awt.Color newColor = JColorChooser.showDialog(null, "Choose Color:", java.awt.Color.WHITE);
            OrganismColor.setColor(listOfOrganisms[Arrays.asList(colors).indexOf(event.getSource())], newColor);
        }
        if (event.getSource() == height) {
            BOARD_HEIGHT = Integer.parseInt(JOptionPane.showInputDialog(screen,
                    "Enter world width", "10"));
        }
        if (event.getSource() == comments) {
            Messages comments = new Messages();
        }
        if (event.getSource() == mode) {
            isHexagonal = !isHexagonal;
        }
        if (event.getSource() == organisms) {
            Description description = new Description();
        }
        if (event.getSource() == width) {
            BOARD_WIDTH = Integer.parseInt(JOptionPane.showInputDialog(screen,
                    "Enter world height", "10"));
        }
        if (event.getSource() == restart || event.getSource() == start2) {
            Info.clearComments();
            deleteButtons();
            Info.RoundLabel label = new Info.RoundLabel(mainMenu);
            Info.ProgressBar pb = new Info.ProgressBar(mainMenu);
            addMenuButtons();
            if (isHexagonal) {
                world = new World(BOARD_WIDTH, BOARD_HEIGHT, this, isHexagonal);
                world.createWorld();
                HexBoardGUI hex = new HexBoardGUI();
                hex.main();
            }
            else {
                world = new World(BOARD_WIDTH, BOARD_HEIGHT, this, isHexagonal);
                world.createWorld();
                if (BoardGUI != null)
                    mainMenu.remove(BoardGUI);
                start();
            }
        }
        if (event.getSource() == load2) {
            Info.clearComments();
            deleteButtons();
            String nameOfFile = JOptionPane.showInputDialog(screen, "Enter filename:", "test.txt");
            world = save.loadWorld(nameOfFile);
            assert world != null;
            world.setWorldGUI(this);
            BoardGUI = new BoardGUI(world);
            mainMenu.remove(BoardGUI);
            Info.RoundLabel label = new Info.RoundLabel(mainMenu, getWorld().getRound());
            addMenuButtons();
            start();
        }
        if (event.getSource() == save1 ) {
            String nameOfFile = JOptionPane.showInputDialog(screen, "Enter filename:", "test.txt");
            save.saveWorld(getWorld(), nameOfFile);
            Info.addComment("World saved!");
        }
        if (event.getSource() == exit) {
            screen.dispose();
        }
    }

    public void deleteButtons() {
        Container parent = start2.getParent();
        if (parent != null) {
            parent.remove(start2);
            parent.remove(load2);
            parent.remove(mainText);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (world != null && world.isStop()) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_ENTER) {}
            else if (world.getIsHumanAlive()) {
                if (keyCode == KeyEvent.VK_UP) {
                    world.getHuman().setDirection(Direction.destination.UP);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    world.getHuman().setDirection(Direction.destination.DOWN);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    world.getHuman().setDirection(Direction.destination.LEFT);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    world.getHuman().setDirection(Direction.destination.RIGHT);
                } else if (keyCode == KeyEvent.VK_P) {
                    Skill skill = world.getHuman().getBurning();
                    if (skill.getIsReadyToActivate()) {
                        skill.activate();
                        Info.addComment("The skill 'burning' has been activated" +
                                " active time is " + skill.getDuration() + " rounds)");

                    } else if (skill.getIsActive()) {
                        Info.addComment("The skill cannot be activated ");
                        return;
                    } else {
                        Info.addComment("The skill is ready to use once again after "
                                + skill.getCooldown() + " rounds");

                        return;
                    }
                } else {
                    return;
                }
            } else if (!world.getIsHumanAlive() && (keyCode == KeyEvent.VK_UP ||
                    keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT ||
                    keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_P)) {
                Info.addComment("Human is dead\n");

                return;
            } else {

                return;
            }
            Info.clearComments();
            world.setStop(false);
            world.simulate();
            reloadWorld();
            world.setStop(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private class MenuGUI extends JPanel {
        public MenuGUI() {
            super();

        }

    }

    private class HexBoardGUI extends JPanel {
        private static final long serialVersionUID = 1L;
        private final int WIDTH = 1200;
        private final int HEIGHT = 800;

        private final Font font = new Font("Arial", Font.BOLD, 18);
        FontMetrics metrics;

        public HexBoardGUI() {
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            java.awt.Point origin = new java.awt.Point(WIDTH / 2, HEIGHT / 2);

            g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            g2d.setFont(font);
            metrics = g.getFontMetrics();

//            drawCircle(g2d, origin, 380, true, true, 0x4488FF, 0);
            drawHexGridLoop(g2d, origin, 7, 50, 8);
        }

        private void drawHexGridLoop(Graphics g, java.awt.Point origin, int size, int radius, int padding) {
            double ang30 = Math.toRadians(30);
            double xOff = Math.cos(ang30) * (radius + padding);
            double yOff = Math.sin(ang30) * (radius + padding);
            int half = size / 2;

            for (int row = 0; row < size; row++) {
                int cols = size - java.lang.Math.abs(row - half);

                for (int col = 0; col < cols; col++) {
                    int xLbl = row < half ? col - row : col - half;
                    int yLbl = row - half;
                    int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                    int y = (int) (origin.y + yOff * (row - half) * 3);
                    drawHex(g, xLbl, yLbl, x, y, radius, Color.BLUE);
                }
            }
        }

        private void drawHex(Graphics g, int posX, int posY, int x, int y, int r, Color hexColor) {
            Graphics2D g2d = (Graphics2D) g;

            Hexagon hex = new Hexagon(x, y, r);
            String text = String.format("%s : %s", coord(posX), coord(posY));
            int w = metrics.stringWidth(text);
            int h = metrics.getHeight();

            hex.draw(g2d, x, y, 0, hexColor, true);
            hex.draw(g2d, x, y, 4, hexColor, false);

            g.setColor(new java.awt.Color(0xFFFFFF));
            g.drawString(text, x - w/2, y + h/2);
        }

        private String coord(int value) {
            return (value > 0 ? "+" : "") + Integer.toString(value);
        }

        public void drawCircle(Graphics2D g, java.awt.Point origin, int radius,
                               boolean centered, boolean filled, int colorValue, int lineThickness) {
            // Store before changing.
            Stroke tmpS = g.getStroke();
            java.awt.Color tmpC = g.getColor();

            g.setColor(new java.awt.Color(colorValue));
            g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));

            int diameter = radius * 2;
            int x2 = centered ? origin.x - radius : origin.x;
            int y2 = centered ? origin.y - radius : origin.y;

            if (filled)
                g.fillOval(x2, y2, diameter, diameter);
            else
                g.drawOval(x2, y2, diameter, diameter);

            // Set values to previous when done.
            g.setColor(tmpC);
            g.setStroke(tmpS);
        }

        public void main() {
            JFrame f = new JFrame();
            HexBoardGUI p = new HexBoardGUI();

            f.setContentPane(p);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        }
    }

    private class BoardGUI extends JPanel {
        private final int height;
        private final int width;
        private final BoardField[][] fields;

        public BoardGUI(World world) {
            super();

            setBounds(mainMenu.getX(), mainMenu.getY() ,
                    mainMenu.getWidth(), mainMenu.getHeight());
            this.width = world.getWidth();
            this.height = world.getHeight();

            fields = new BoardField[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    fields[i][j] = new BoardField(j, i);
                    addActionListenerToField(fields, i, j);
                }
            }


            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    this.add(fields[i][j]);
                }
            }
            this.setLayout(new GridLayout(height, width));
        }


        private void addActionListenerToField(BoardField[][] fields, int i, int j) {
            fields[i][j].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof BoardField tmpPole) {
                        if (tmpPole.isEmpty) {
                            ChoiceList listOfOrganisms = new ChoiceList
                                    (tmpPole.getX() + screen.getX(),
                                    tmpPole.getY() + screen.getY(),
                                    new pl.project.oop.java.Point(tmpPole.getPositionX(),
                                    tmpPole.getPositionY()), world.getWorldGUI());
                        }
                    }
                }
            });
        }


        private class BoardField extends JButton {
            private boolean isEmpty;
            private java.awt.Color color;
            private final int positionX;
            private final int positionY;

            public BoardField(int X, int Y) {
                super();
                color = java.awt.Color.WHITE;
                setBackground(color);
                isEmpty = true;
                positionX = X;
                positionY = Y;
            }

            public boolean isEmpty() {
                return isEmpty;
            }

            public void setEmpty(boolean empty) {
                isEmpty = empty;
            }


            public java.awt.Color getColor() {
                return color;
            }

            public void setColor(java.awt.Color color) {
                this.color = color;
                setBackground(color);
            }

            public int getPositionX() {
                return positionX;
            }

            public int getPositionY() {
                return positionY;
            }
        }

        public void reloadBoard() {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Organism organism = world.getBoard()[i][j];
                    if (organism != null) {
                        fields[i][j].setEmpty(false);
                        fields[i][j].setEnabled(false);
                        fields[i][j].setOpaque(true);
                        fields[i][j].setColor(organism.getColor());
                    } else {
                        fields[i][j].setEmpty(true);
                        fields[i][j].setEnabled(true);
                        fields[i][j].setOpaque(true);
                        fields[i][j].setForeground(Color.WHITE);
                        fields[i][j].setColor(java.awt.Color.WHITE);
                    }
                }
            }
        }

        public int getBoardWidth() {
            return width;
        }

        public int getBoardHeight() {
            return height;
        }

        public BoardField[][] getFields() {
            return fields;
        }
    }

    private void start() {
        BoardGUI = new BoardGUI(world);
        mainMenu.add(BoardGUI);


        reloadWorld();
    }

    public void reloadWorld() {
        BoardGUI.reloadBoard();

        SwingUtilities.updateComponentTreeUI(screen);
        screen.requestFocusInWindow();
    }

    public void addMenuButtons() {
        menu.add(restart);
        menu.add(comments);
        menu.add(organisms);
        menu.add(save1);
        menu.add(exit);
    }

    public World getWorld() {
        return world;
    }

    public BoardGUI getBoardGUI() {
        return BoardGUI;
    }


}