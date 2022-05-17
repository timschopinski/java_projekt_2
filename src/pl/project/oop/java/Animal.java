package pl.project.oop.java;

import java.util.Random;

public abstract class Animal extends Organism {

    public Animal(Type.OrganismType type, World world,
                  Point position, int strength, int initiative, int age) {
        super(type, world, position, strength, initiative, age);
        setIsReproduced(false);
        setChanceToReproduce(0.5);
    }

    @Override
    public void action() {
        Point nextPosition = getMove();

        if (getWorld().isFieldOccupied(nextPosition)
                && getWorld().getOrganimOn(nextPosition) != this) {
            collision(getWorld().getOrganimOn(nextPosition));
        } else if (getWorld().getOrganimOn(nextPosition) != this) makeMove(nextPosition);

    }

    @Override
    public void collision(Organism other) {
        if (getOrganismType() == other.getOrganismType()) {
            Random rand = new Random();
            double chance = rand.nextDouble();
            if (chance < getChanceToReproduce()) reproduce(other);
        } else {
            if (other.specialAction(this, other)) return;
            if (specialAction(this, other)) return;

            if (getStrength() >= other.getStrength()) {
                getWorld().deleteOrganism(other);
                makeMove(other.getPosition());
                Info.addComment(organismToString() + " kills " + other.organismToString());
            } else {
                getWorld().deleteOrganism(this);
                Info.addComment(other.organismToString() + " kills " + organismToString());
            }
        }
    }

    @Override
    public boolean isAnimal() {
        return true;
    }

    protected Point getMove() {
        return getRandomField(getPosition());
    }

    private void reproduce(Organism other) {
        if (this.getIsReproduced() || other.getIsReproduced()) return;
        Point point1 = this.getRandomEmptyField(getPosition());
        if (point1.equals(getPosition())) {
            Point point2 = other.getRandomEmptyField(other.getPosition());
            if (!point2.equals(other.getPosition())) {
                Organism organism = createOrganism(getOrganismType(), this.getWorld(), point2);
                Info.addComment(organism.organismToString() + " is born");
                getWorld().addOrganism(organism);
                setIsReproduced(true);
                other.setIsReproduced(true);
            }
        } else {
            Organism organism = createOrganism(getOrganismType(), this.getWorld(), point1);
            Info.addComment(organism.organismToString() + " is born");
            getWorld().addOrganism(organism);
            setIsReproduced(true);
            other.setIsReproduced(true);
        }
    }

}
