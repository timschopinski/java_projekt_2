package pl.project.oop.java;

import java.util.Random;

public class WildBerries extends Plant {
    private static final int STRENGTH = 99;
    private static final double REPRODUCE_CHANCE = 0.01;

    public WildBerries(World world, Point position, int age) {
        super(Type.OrganismType.WILD_BERRIES, world, position, age);
        setStrength(STRENGTH);
        setColor(OrganismColor.WILD_BERRIES);
        setChanceToReproduce(REPRODUCE_CHANCE);
    }


    @Override
    public void action() {
        tryToSpread();
    }

    @Override
    public String OrganismTypeToString() {
        return "Wild Berries";
    }

    @Override
    public boolean specialAction(Organism predator, Organism victim) {
        Info.addComment(predator.organismToString() + " eats " + this.organismToString());
        if (predator.getStrength() >= STRENGTH) {
            getWorld().deleteOrganism(this);
            Info.addComment(predator.organismToString() + " eats Wild Berries");
        }
        if (predator.isAnimal()) {
            getWorld().deleteOrganism(predator);
            Info.addComment(predator.organismToString() + " dies after eating Wild Berries");
        }
        return true;
    }
}
