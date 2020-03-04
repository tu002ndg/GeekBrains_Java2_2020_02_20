package hw01.contestants;

public class Human implements Contestant {

    private final static int RUN_DISTANCE_DEFAULT = 200;
    private final static int JUMP_HEIGHT_DEFAULT = 100;

    private final String name;
    private final int jumpHeight;
    private final int runDistance;

    public int jump() {

        System.out.printf("The human '%s' jumps %d%n ",
                name, jumpHeight);
        return jumpHeight;
    }

    public int run() {

        System.out.printf("The human '%s' runs the distance %d%n ",
                name, runDistance);
        return runDistance;
    }

    public Human(String name) {
        this.name = name;
        this.runDistance=RUN_DISTANCE_DEFAULT;
        this.jumpHeight = JUMP_HEIGHT_DEFAULT;
    }

    public Human(String name, int runDistance, int jumpHeight) {
        this.name = name;
        this.runDistance=runDistance;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public String toString() {
        return "The human '"+name+"'\n"
                +"jump height:"+jumpHeight+"\n"
                +"running distance:"+runDistance;
    }

    public String name() {
        return name;
    }

}
