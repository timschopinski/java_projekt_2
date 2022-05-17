package pl.project.oop.java;

public class Wolf extends Animal {
    private static final int STRENGTH = 9;
    private static final int INITIATIVE = 5;


    public Wolf(World world, Point position, int age) {
        super(Type.OrganismType.WILD_BERRIES, world, position, STRENGTH, INITIATIVE, age);
        setColor(OrganismColor.WOLF);
    }

    @Override
    public String OrganismTypeToString() {
        return "Wolf";
    }
}
