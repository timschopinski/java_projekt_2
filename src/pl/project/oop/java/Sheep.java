package pl.project.oop.java;

public class Sheep extends Animal {
    private static final int STRENGTH = 4;
    private static final int INITIATIVE = 4;

    public Sheep(World world, Point position, int age) {
        super(Type.OrganismType.SHEEP, world, position, STRENGTH, INITIATIVE, age);
        setColor(OrganismColor.SHEEP);
    }

    @Override
    public String OrganismTypeToString() {
        return "Sheep";
    }
}
