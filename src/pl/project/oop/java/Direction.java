package pl.project.oop.java;

public class Direction {
    public enum destination {
        LEFT(0),
        RIGHT(1),
        UP(2),
        DOWN(3),
        NONE(4);

        private int value;

        private destination(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
