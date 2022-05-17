package pl.project.oop.java;

import java.util.Random;

public abstract class Plant extends Organism {
    private static final int STRENGTH = 0;
    private static final int INITIATIVE = 0;

    protected Plant(Type.OrganismType type, World world,
                    Point position, int age) {
        super(type, world, position, STRENGTH, INITIATIVE, age);
        double chanceToReproduce = 0.3;
        setChanceToReproduce(chanceToReproduce);
    }

    @Override
    public void action() {
        tryToSpread();
    }

    @Override
    public boolean isAnimal() {
        return false;
    }

    protected void tryToSpread() {
        Random rand = new Random();
        double chance = rand.nextDouble();
        if (chance < getChanceToReproduce()) {
            spreading();
        }
    }

    protected void spreading() {
        Point p = this.getRandomEmptyField(getPosition());
        if (!p.equals(getPosition())) {
            Organism organism = Organism.createOrganism(getOrganismType(), this.getWorld(), p);
            Info.addComment("New " + organism.organismToString() + " grows up");
            getWorld().addOrganism(organism);
        }
    }

    @Override
    public void collision(Organism other) {
    }
}
