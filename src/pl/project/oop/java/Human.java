package pl.project.oop.java;

public class Human extends Animal {
    private static final int STRENGTH = 5;
    private static final int INITIATIVE = 4;
    private Direction.destination destination;
    private final Skill burning;



    public Human(World world, Point position, int age) {
        super(Type.OrganismType.HUMAN, world, position, STRENGTH, INITIATIVE, age);
        destination = Direction.destination.NONE;
        setColor(OrganismColor.HUMAN);
        burning = new Skill();
    }

    private void burning() {
        getRandomField(getPosition());
        int positionX = getPosition().getX();
        int positionY = getPosition().getY();

        for (int i = 0; i < NUMBER_OF_DIRECTIONS; i++) {
            Organism organism = getNextToOrganism(positionX, positionY, i);
            if (organism != null) {
                getWorld().deleteOrganism(organism);
                Info.addComment(organismToString() + " kills using 'burning'"
                        + organism.organismToString());
            }
        }
    }

    @Override
    protected Point getMove() {
        int x = getPosition().getX();
        int y = getPosition().getY();
        getRandomField(getPosition());
        if (destination == Direction.destination.NONE ||
                isDirectionLocked(destination)) return getPosition();
        else {
            if (destination == Direction.destination.DOWN) return new Point(x, y + 1);
            if (destination == Direction.destination.UP) return new Point(x, y - 1);
            if (destination == Direction.destination.LEFT) return new Point(x - 1, y);
            if (destination == Direction.destination.RIGHT) return new Point(x + 1, y);
            else return new Point(x, y);
        }
    }

    @Override
    public void action() {

        if (burning.getIsActive()) {
            Info.addComment(organismToString() + " 'Burning' is active ");
            burning();
        }

        Point nextPosition = getMove();

        if (getWorld().isFieldOccupied(nextPosition)
                && getWorld().getOrganimOn(nextPosition) != this) {
            collision(getWorld().getOrganimOn(nextPosition));
        } else if (getWorld().getOrganimOn(nextPosition) != this) makeMove(nextPosition);
        if (burning.getIsActive()) burning();

        destination = Direction.destination.NONE;
        burning.checkConditions();
    }


    @Override
    public String OrganismTypeToString() {
        return "Human";
    }

    public Skill getBurning() {
        return burning;
    }

    public void setDirection(Direction.destination direction) {
        this.destination = direction;
    }
}
