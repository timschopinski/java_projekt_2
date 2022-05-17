package pl.project.oop.java;


import java.awt.*;
import java.util.Random;
import pl.project.oop.java.Direction.destination;
import pl.project.oop.java.Type.OrganismType;
public abstract class Organism {

    protected static final int NUMBER_OF_DIRECTIONS = 4;
    private int strength;
    private int initiative;
    private int age;
    private boolean isDead;
    private final boolean[] direction;
    private boolean isReproduced;
    private boolean isReadyToGo;
    private World world;
    private pl.project.oop.java.Point position;
    private OrganismType organismType;
    private double chanceToReproduce;
    private Color color;

    public abstract String OrganismTypeToString();

    public abstract void action();

    public abstract void collision(Organism other);

    public abstract boolean isAnimal();

    public Organism(Type.OrganismType type, World world,
                    pl.project.oop.java.Point position, int strength, int initiative, int age) {
        this.organismType = type;
        this.world = world;
        this.position = position;
        this.age = age;
        this.strength = strength;
        this.initiative = initiative;
        isDead = false;
        direction = new boolean[]{true, true, true, true};
    }


    public Organism getNextToOrganism(int positionX, int positionY, int it) {
        Organism organism = null;
        if (it == 0 && !isDirectionLocked(destination.DOWN))
            organism = getWorld().getOrganimOn(new Point(positionX, positionY + 1));
        else if (it == 1 && !isDirectionLocked(destination.UP))
            organism = getWorld().getOrganimOn(new Point(positionX, positionY - 1));
        else if (it == 2 && !isDirectionLocked(destination.LEFT))
            organism = getWorld().getOrganimOn(new Point(positionX - 1, positionY));
        else if (it == 3 && !isDirectionLocked(destination.RIGHT))
            organism = getWorld().getOrganimOn(new Point(positionX + 1, positionY));
        return organism;
    }

    public boolean specialAction(Organism predator, Organism victim) {
        return false;
    }

    public String organismToString() {
        return (OrganismTypeToString() + " x[" + position.getX() + "] y["
                + position.getY() + "] strength[" + strength + "]");
    }

    public void makeMove(pl.project.oop.java.Point nextPosition) {
        int x = nextPosition.getX();
        int y = nextPosition.getY();
        world.getBoard()[position.getY()][position.getX()] = null;
        world.getBoard()[y][x] = this;
        position.setX(x);
        position.setY(y);
    }

    private int getPossibleMoves() {
        int possibleMoves = 0;
        if (position.getX() == 0) BlockDirection(destination.LEFT);
        else possibleMoves++;
        if (position.getX() == world.getWidth() - 1) BlockDirection(destination.RIGHT);
        else possibleMoves++;
        if (position.getY() == 0) BlockDirection(destination.UP);
        else possibleMoves++;
        if (position.getY() == world.getHeight() - 1) BlockDirection(destination.DOWN);
        else possibleMoves++;
        return possibleMoves;
    }

    public pl.project.oop.java.Point getRandomField(pl.project.oop.java.Point position) {
        ResetDirections();
        int positionX = position.getX();
        int positionY = position.getY();
        int possibleMoves = getPossibleMoves();

        if (possibleMoves == 0) return position;
        return getField(positionX, positionY);
    }

    private Point getField(int positionX, int positionY) {
        while (true) {
            Random rand = new Random();
            double resultRandom = rand.nextDouble();
            if (resultRandom < 0.25 && !isDirectionLocked(destination.LEFT))
                return new Point(positionX - 1, positionY);
            else if (resultRandom >= 0.50 && resultRandom < 0.75 && !isDirectionLocked(destination.UP))
                return new Point(positionX, positionY - 1);
            else if (resultRandom >= 0.75 && !isDirectionLocked(destination.DOWN))
                return new Point(positionX, positionY + 1);
            else if (resultRandom >= 0.25 && resultRandom < 0.50 && !isDirectionLocked(destination.RIGHT))
                return new Point(positionX + 1, positionY);
        }
    }

    public pl.project.oop.java.Point getRandomEmptyField(pl.project.oop.java.Point position) {
        ResetDirections();
        int positionX = position.getX();
        int positionY = position.getY();
        int width = world.getWidth();
        int height = world.getHeight();
        int possibleMoves = 0;

        if (positionX == 0) BlockDirection(destination.LEFT);
        else {
            if (!world.isFieldOccupied(new pl.project.oop.java.Point(positionX - 1, positionY))) possibleMoves++;
            else BlockDirection(destination.LEFT);
        }

        if (positionX == width - 1) BlockDirection(destination.RIGHT);
        else {
            if (!world.isFieldOccupied(new pl.project.oop.java.Point(positionX + 1, positionY))) possibleMoves++;
            else BlockDirection(destination.RIGHT);
        }

        if (positionY == 0) BlockDirection(destination.UP);
        else {
            if (!world.isFieldOccupied(new pl.project.oop.java.Point(positionX, positionY - 1))) possibleMoves++;
            else BlockDirection(destination.UP);
        }

        if (positionY == height - 1) BlockDirection(destination.DOWN);
        else {
            if (!world.isFieldOccupied(new pl.project.oop.java.Point(positionX, positionY + 1))) possibleMoves++;
            else BlockDirection(destination.DOWN);
        }

        if (possibleMoves == 0) return new pl.project.oop.java.Point(positionX, positionY);
        return getField(positionX, positionY);
    }


    public static Organism createOrganism (Type.OrganismType OrganismType, World world, Point position) {
        return switch (OrganismType) {
            case WOLF -> new Wolf(world, position, world.getRound());
            case SHEEP -> new Sheep(world, position, world.getRound());
            case FOX -> new Fox(world, position, world.getRound());
            case TURTLE -> new Turtle(world, position, world.getRound());
            case ANTELOPE -> new Antelope(world, position, world.getRound());
            case HUMAN -> new Human(world, position, world.getRound());
            case GRASS -> new Grass(world, position, world.getRound());
            case DANDELION -> new Dandelion(world, position, world.getRound());
            case GUARANA -> new Guarana(world, position, world.getRound());
            case WILD_BERRIES -> new WildBerries(world, position, world.getRound());
            case BORSCH -> new Borsch(world, position, world.getRound());
        };
    }

    protected void BlockDirection(destination dest) {
        this.direction[dest.getValue()] = false;
    }

    protected void UnlockDirection(destination dest) {
        this.direction[dest.getValue()] = true;
    }

    protected void ResetDirections() {
        UnlockDirection(destination.LEFT);
        UnlockDirection(destination.RIGHT);
        UnlockDirection(destination.UP);
        UnlockDirection(destination.DOWN);
    }

    protected boolean isDirectionLocked(destination direction) {
        return !(this.direction[direction.getValue()]);
    }

    public int getStrength() {
        return strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getAge() {
        return age;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public boolean getIsReproduced() {
        return isReproduced;
    }

    public World getWorld() {
        return world;
    }

    public pl.project.oop.java.Point getPosition() {
        return position;
    }

    public OrganismType getOrganismType() {
        return organismType;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void setIsReproduced(boolean isReproduced) {
        this.isReproduced = isReproduced;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setOrganismType(OrganismType organismType) {
        this.organismType = organismType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getChanceToReproduce() {
        return chanceToReproduce;
    }

    public void setChanceToReproduce(double chanceToReproduce) {
        this.chanceToReproduce = chanceToReproduce;
    }
}