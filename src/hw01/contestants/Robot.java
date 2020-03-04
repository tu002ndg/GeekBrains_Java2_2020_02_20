package hw01.contestants;

public class Robot implements Contestant {
    private final static int RUN_DISTANCE_DEFAULT = 500;
    private final static int JUMP_HEIGHT_DEFAULT = 250;

    private final String name;
    private final int jumpHeight;
    private final int runDistance;

    public int jump() {

        System.out.printf("The robot '%s' jumps %d%n ",
                name, jumpHeight);
        return jumpHeight;
    }

    public int run() {

        System.out.printf("The robot '%s' runs the distance %d%n ",
                name, runDistance);
        return runDistance;
    }

    public Robot(String name) {
        this.name = name;
        this.runDistance= RUN_DISTANCE_DEFAULT;
        this.jumpHeight = JUMP_HEIGHT_DEFAULT;

    }

    public Robot(String name, int runDistance, int jumpHeight) {
        this.name = name;
        this.runDistance= runDistance;
        this.jumpHeight = jumpHeight;

    }

    @Override
    public String toString() {
        return "The robot '"+name+"'\n"
                +"jump height:"+jumpHeight+"\n"
                +"running distance:"+runDistance;
    }

    public String name() {
        return name;
    }
}
