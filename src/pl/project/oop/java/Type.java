package pl.project.oop.java;

import java.util.Random;

public class Type {
    public enum OrganismType {
        HUMAN,
        WOLF,
        SHEEP,
        FOX,
        TURTLE,
        ANTELOPE,
        GRASS,
        DANDELION,
        GUARANA,
        WILD_BERRIES,
        BORSCH;

    }
    /** This method returns random type */
    public static OrganismType getRandomType() {
        int NUMBER_OF_SPECIES = 11;
        Random rand = new Random();
        int num = rand.nextInt(NUMBER_OF_SPECIES);
        if (num == 0) return OrganismType.ANTELOPE;
        else if (num == 1) return OrganismType.BORSCH;
        else if (num == 2) return OrganismType.GUARANA;
        else if (num == 3) return OrganismType.FOX;
        else if (num == 4) return OrganismType.DANDELION;
        else if (num == 5) return OrganismType.SHEEP;
        else if (num == 6) return OrganismType.GRASS;
        else if (num == 7) return OrganismType.WILD_BERRIES;
        else if (num == 8) return OrganismType.WOLF;
        else return OrganismType.TURTLE;
    }
}
