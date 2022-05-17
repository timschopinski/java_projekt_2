package pl.project.oop.java;

public class Grass extends Plant {

    public Grass(World world, Point position, int age) {
        super(Type.OrganismType.GRASS, world, position, age);
        setColor(OrganismColor.GRASS);
    }

    @Override
    public String OrganismTypeToString() {
        return "Grass";
    }
}
