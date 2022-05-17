package pl.project.oop.java;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import pl.project.oop.java.Type.OrganismType;

public class World {
    private final int width;
    private final int height;
    private  boolean isHexagonal = false;
    private int round;
    private Organism[][] board;
    private boolean isHumanAlive;
    private boolean isGameOver;
    private boolean stop;
    private final ArrayList<Organism> organisms;
    private Human human;
    private pl.project.oop.java.WorldGUI WorldGUI;

    public World(pl.project.oop.java.WorldGUI WorldGUI) {
        this.width = 0;
        this.height = 0;
        round = 0;
        isHumanAlive = true;
        isGameOver = false;
        stop = true;
        organisms = new ArrayList<>();
        this.WorldGUI = WorldGUI;
    }

    public World(int width, int height, pl.project.oop.java.WorldGUI WorldGUI, boolean isHexagonal) {
        this.isHexagonal = isHexagonal;
        this.width = width;
        this.height = height;
        round = 0;
        isHumanAlive = true;
        isGameOver = false;
        stop = true;
        if (isHexagonal) {
            board = new Organism[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i][j] = null;
                }
            }
        }
        else {
            board = new Organism[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    board[i][j] = null;
                }
            }
        }
        organisms = new ArrayList<>();
        this.WorldGUI = WorldGUI;
    }

    private void sortOrganisms() {
        organisms.sort((o1, o2) -> {
            if (o1.getInitiative() != o2.getInitiative())
                return Integer.compare(o2.getInitiative(), o1.getInitiative());
            else
                return Integer.compare(o1.getAge(), o2.getAge());
        });
    }

    public void createHexagonalWorld() {
        int numberOfOrganisms = 37;
        create(numberOfOrganisms);
    }

    public void createWorld() {
        int numberOfOrganisms = width * height;
        create(numberOfOrganisms);
    }

    private void create(int numberOfOrganisms) {

        Point position = getRandomField();
        Organism organism = Organism.createOrganism(OrganismType.HUMAN, this, position);
        addOrganism(organism);
        human = (Human) organism;

        for (int i = 0; i < numberOfOrganisms - 1; i++) {
            position = getRandomField();
            if (!Objects.equals(position, new Point(-1, -1))) {
                addOrganism(Organism.createOrganism(Type.getRandomType(), this, position));
            } else return;
        }
    }


    public void simulate() {
        if (isGameOver) return;
        round++;
        Info.roundNumber = round;
        Info.updateLabel();
        if (isHumanAlive) {
            Info.updateProgressBar(getHuman().getBurning().getDuration());
        }
        Info.addComment("\nRound " + round);

        sortOrganisms();
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).getAge() != round
                    && !organisms.get(i).getIsDead()) {
                organisms.get(i).action();
            }
        }
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).getIsDead()) {
                organisms.remove(i);
                i--;
            }
        }
        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).setIsReproduced(false);
        }
    }


    public Point getRandomField() {
        Random rand = new Random();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == null) {
                    while (true) {
                        int x = rand.nextInt(width);
                        int y = rand.nextInt(height);
                        if (board[y][x] == null) return new Point(x, y);
                    }
                }
            }
        }
        return new Point(-1, -1);
    }

    public boolean isFieldOccupied(Point pole) {
        return board[pole.getY()][pole.getX()] != null;
    }

    public Organism getOrganimOn(Point pole) {
        return board[pole.getY()][pole.getX()];
    }

    public void addOrganism(Organism organism) {
        organisms.add(organism);
        board[organism.getPosition().getY()][organism.getPosition().getX()] = organism;
    }

    public void deleteOrganism(Organism organism) {
        board[organism.getPosition().getY()][organism.getPosition().getX()] = null;
        organism.setIsDead(true);
        if (organism.getOrganismType() == OrganismType.HUMAN) {
            isHumanAlive = false;
            human = null;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRound() {
        return round;
    }

    public Organism[][] getBoard() {
        return board;
    }

    public boolean getIsHumanAlive() {
        return isHumanAlive;
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public void setIsHumanAlive(boolean isHumanAlive) {
        this.isHumanAlive = isHumanAlive;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setHexagonal(boolean hexagonal) {
        isHexagonal = hexagonal;
    }

    public boolean getIsHexagonal() {
        return isHexagonal;
    }

    public boolean isStop() {
        return stop;
    }

    public void setRound(int r) {
        round = r;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public pl.project.oop.java.WorldGUI getWorldGUI() {
        return WorldGUI;
    }

    public void setWorldGUI(pl.project.oop.java.WorldGUI WorldGUI) {
        this.WorldGUI = WorldGUI;
    }

    public boolean isBorschOnBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != null &&
                        board[i][j].getOrganismType() == OrganismType.BORSCH) {
                    return true;
                }
            }
        }
        return false;
    }
}
