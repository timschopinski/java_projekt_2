package pl.project.oop.java;


import java.util.Random;

public class Fox extends Animal {
    private static final int STRENGTH = 3;
    private static final int INITIATIVE = 7;

    public Fox(World world, pl.project.oop.java.Point position, int age) {
        super(Type.OrganismType.FOX, world, position, STRENGTH, INITIATIVE, age);
        setColor(OrganismColor.FOX);
    }

    @Override
    public String OrganismTypeToString() {
        return "Fox";
    }

    @Override
    public pl.project.oop.java.Point getRandomField(pl.project.oop.java.Point position) {
        ResetDirections();
        int positionX = position.getX();
        int positionY = position.getY();
        int sizeX = getWorld().getWidth();
        int sizeY = getWorld().getHeight();
        int numberOfDirections = 0;
        Organism organism;

        if (positionX == 0) BlockDirection(Direction.destination.LEFT);
        else {
            organism = getWorld().getBoard()[positionY][positionX - 1];
            if (organism != null && organism.getStrength() > this.getStrength()) {
                BlockDirection(Direction.destination.LEFT);
            } else numberOfDirections++;
        }

        if (positionX == sizeX - 1) BlockDirection(Direction.destination.RIGHT);
        else {
            organism = getWorld().getBoard()[positionY][positionX + 1];
            if (organism != null && organism.getStrength() > this.getStrength()) {
                BlockDirection(Direction.destination.RIGHT);
            } else numberOfDirections++;
        }

        if (positionY == 0) BlockDirection(Direction.destination.UP);
        else {
            organism = getWorld().getBoard()[positionY - 1][positionX];
            if (organism != null && organism.getStrength() > this.getStrength()) {
                BlockDirection(Direction.destination.UP);
            } else numberOfDirections++;
        }

        if (positionY == sizeY - 1) BlockDirection(Direction.destination.DOWN);
        else {
            organism = getWorld().getBoard()[positionY + 1][positionX];
            if (organism != null && organism.getStrength() > this.getStrength()) {
                BlockDirection(Direction.destination.DOWN);
            } else numberOfDirections++;
        }

        if (numberOfDirections == 0) return new pl.project.oop.java.Point(positionX, positionY);
        while (true) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance < 0.25 && !isDirectionLocked(Direction.destination.LEFT))
                return new pl.project.oop.java.Point(positionX - 1, positionY);
            else if (chance >= 0.25 && chance < 0.5 && !isDirectionLocked(Direction.destination.RIGHT))
                return new pl.project.oop.java.Point(positionX + 1, positionY);
            else if (chance >= 0.50 && chance < 0.75 && !isDirectionLocked(Direction.destination.UP))
                return new pl.project.oop.java.Point(positionX, positionY - 1);
            else if (chance >= 0.75 && !isDirectionLocked(Direction.destination.DOWN))
                return new Point(positionX, positionY + 1);
        }
    }
}
