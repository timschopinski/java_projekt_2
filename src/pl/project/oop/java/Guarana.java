package pl.project.oop.java;


public class Guarana extends Plant {

    public Guarana(World world, Point position, int age) {
        super(Type.OrganismType.GUARANA, world, position, age);
        setColor(OrganismColor.GUARANA);
    }

    @Override
    public String OrganismTypeToString() {
        return "Guarana";
    }

    @Override
    public boolean specialAction(Organism predator, Organism victim) {
        int boost = 3;
        Point position = this.getPosition();
        getWorld().deleteOrganism(this);
        predator.makeMove(position);
        Info.addComment(predator.organismToString() + " eats " + this.organismToString());

        predator.setStrength(predator.getStrength() + boost);
        return true;
    }
}

