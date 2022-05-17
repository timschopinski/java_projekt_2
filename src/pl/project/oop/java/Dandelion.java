package pl.project.oop.java;

import java.util.Random;

public class Dandelion extends Plant {

    public Dandelion(World world, Point position, int age) {
        super(Type.OrganismType.DANDELION, world, position, age);
        setColor(OrganismColor.DANDELION);
    }

    @Override
    public void action() {
        Random rand = new Random();
        int reps = 3;
        for (int i = 0; i < reps; i++) {
            double chance = rand.nextDouble();
            if (chance < getChanceToReproduce()) spreading();
        }
    }


    @Override
    public String OrganismTypeToString() {
        return "Dandelion";
    }
}
