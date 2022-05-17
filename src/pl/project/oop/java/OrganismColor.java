package pl.project.oop.java;

import java.awt.*;
import java.util.Objects;

public class OrganismColor {
    public static Color HUMAN = Color.CYAN;
    public static Color GUARANA = Color.RED;
    public static Color ANTELOPE = new Color(124, 109, 6);
    public static Color WILD_BERRIES = new Color(4, 33, 141);
    public static Color GRASS = Color.GREEN;
    public static Color DANDELION = Color.YELLOW;
    public static Color BORSCH = new Color(103, 7, 7);
    public static Color FOX = new Color(255, 128, 0);
    public static Color SHEEP = new Color(212, 162, 218);
    public static Color WOLF = new Color(32, 147, 128);
    public static Color TURTLE = new Color(0, 102, 0);

    public static Color ORGANISM_COLOR;

    public OrganismColor(int r, int g, int b) {
        ORGANISM_COLOR = new Color(r, g, b);
    }
    public OrganismColor(){
    };


    public static void setHuman(Color human) {
        HUMAN = human;
    }
    public static void setAntelope(Color antelope) {
        ANTELOPE = antelope;
    }
    public static void setBorsch(Color borsch) {
        BORSCH = borsch;
    }
    public static void setGuarana(Color guarana) {
        GUARANA = guarana;
    }
    public static void setFox(Color fox) {
        FOX = fox;
    }
    public static void setDandelion(Color dandelion) {
        DANDELION = dandelion;
    }
    public void setOrganismColor(Color organismColor) {
        ORGANISM_COLOR = organismColor;
    }
    public static void setSheep(Color sheep) {
        SHEEP = sheep;
    }
    public static void setWolf(Color wolf) {
        WOLF = wolf;
    }
    public static void setWildBerries(Color wildBerries) {
        WILD_BERRIES = wildBerries;
    }
    public static void setGrass(Color grass) {
        GRASS = grass;
    }
    public static void setTurtle(Color turtle) {
        TURTLE = turtle;
    }

    public static void setColor(String organizm, Color nowyOrghanismColor) {
        if (Objects.equals(organizm, "Human")) {
            setHuman(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Gurana")) {
            setGuarana(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Antelope")) {
            setAntelope(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Wild Berries")) {
            setWildBerries(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Grass")) {
            setGrass(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Dandelion")) {
            setDandelion(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Borsch")) {
            setBorsch(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Fox")) {
            setFox(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Sheep")) {
            setFox(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Wolf")) {
            setWolf(nowyOrghanismColor);
        }
        else if (Objects.equals(organizm, "Turtle")) {
            setTurtle(nowyOrghanismColor);
        }
    }

}
