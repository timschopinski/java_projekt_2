package pl.project.oop.java;

import java.util.Random;

public class Turtle extends Animal {
    private static final int STRENGTH = 2;
    private static final int INITIATIVE = 1;

    public Turtle(World world, Point position, int age) {
        super(Type.OrganismType.TURTLE, world, position, STRENGTH, INITIATIVE, age);
        setColor(OrganismColor.TURTLE);
    }

    @Override
    protected Point getMove() {
        double chanceToMakeMove = 0.25;
        Random rand = new Random();
        double chance = rand.nextDouble();
        if (chance >= chanceToMakeMove) return getPosition();
        else return getRandomField(getPosition());
    }

    @Override
    public String OrganismTypeToString() {
        return "Turtle";
    }

    @Override
    public boolean specialAction(Organism predator, Organism victim) {
        int strongestAnimalStrength = 5;
        if (this == victim) {
            if (predator.getStrength() < strongestAnimalStrength && predator.isAnimal()) {
                Info.addComment(organismToString() + " defends " + predator.organismToString());
                return true;
            } else return false;
        } else {
            if (predator.getStrength() >= victim.getStrength()) return false;
            else {
                if (victim.getStrength() < strongestAnimalStrength && victim.isAnimal()) {
                    Info.addComment(organismToString() + " defends " + victim.organismToString());
                    return true;
                } else return false;
            }
        }
    }

}
