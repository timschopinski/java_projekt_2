package pl.project.oop.java;

import java.util.concurrent.Callable;

public class Borsch extends Plant {

    public Borsch(World world, Point position, int age) {
        super(Type.OrganismType.BORSCH, world, position, age);
        setColor(OrganismColor.BORSCH);
        setChanceToReproduce(0.1);
    }

    private Callable checkNextToOrganisms(Organism organism) {
        if (organism != null && organism.isAnimal()) {
            getWorld().deleteOrganism(organism);
            Info.addComment(organismToString() + " kills " + organism.organismToString());
        }
        return null;
    }

    @Override
    public void action() {
        int positionX = getPosition().getX();
        int positionY = getPosition().getY();
        getRandomField(getPosition());
        for (int i = 0; i < NUMBER_OF_DIRECTIONS; i++) {
            Organism organism = getNextToOrganism(positionX, positionY, i);
            if (organism == null) break;
            if (organism.isAnimal()) {
                getWorld().deleteOrganism(organism);
                Info.addComment(organismToString() + " kills " + organism.organismToString());
            }

        }
        tryToSpread();
    }

    @Override
    public String OrganismTypeToString() {
        return "Borsch";
    }

    @Override
    public boolean specialAction(Organism predator, Organism victim) {
        int maxStrength = 10;
        if (predator.getStrength() >= maxStrength) {
            getWorld().deleteOrganism(this);
            Info.addComment(predator.organismToString() + " eats " + this.organismToString());
            predator.makeMove(victim.getPosition());
        }
        if ((predator.isAnimal())
                || predator.getStrength() < maxStrength) {
            getWorld().deleteOrganism(predator);
            Info.addComment(this.organismToString() + " kills " + predator.organismToString());
        }
        return true;
    }
}
