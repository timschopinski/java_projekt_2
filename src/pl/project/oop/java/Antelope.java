package pl.project.oop.java;

import java.util.Random;

public class Antelope extends Animal {
    private static final int STRENGTH = 4;
    private static final int INITIATIVE = 4;


    public Antelope(World world, Point position, int age) {
        super(Type.OrganismType.ANTELOPE, world, position, STRENGTH, INITIATIVE, age);
        setColor(OrganismColor.ANTELOPE);
    }

    /** This avoids collision with other organisms */
    private void runAway(Organism victim) {
        Info.addComment(organismToString() + " run away from" + victim.organismToString());
        Point pos = getRandomEmptyField(victim.getPosition());
        if (!pos.equals(victim.getPosition()))
            makeMove(pos);
    }

    private void runAwayFromPredator(Organism predator) {
        Info.addComment(organismToString() + " run away from " + predator.organismToString());
        Point pos = this.getPosition();
        makeMove(getRandomEmptyField(this.getPosition()));
        if (getPosition().equals(pos)) {
            getWorld().deleteOrganism(this);
            Info.addComment(predator.organismToString() + " kills " + organismToString());
        }
        predator.makeMove(pos);
    }

    @Override
    public void action() {
        int range = 2;
        for (int i = 0; i < range; i++) {
            Point nextPosition = getMove();

            if (getWorld().isFieldOccupied(nextPosition)
                    && getWorld().getOrganimOn(nextPosition) != this) {
                collision(getWorld().getOrganimOn(nextPosition));
                break;
            } else if (getWorld().getOrganimOn(nextPosition) != this) makeMove(nextPosition);
        }
    }

    @Override
    public String OrganismTypeToString() {
        return "Antelope";
    }

    @Override
    public boolean specialAction(Organism predator, Organism victim) {
        Random rand = new Random();
        double chance = rand.nextDouble();
        if (chance < 0.5) {
            if (this == predator) {
                runAway(victim);
            } else if (this == victim) {
                runAwayFromPredator(predator);
            }
            return true;
        } else {
            return false;
        }
    }
}
