package hw01.obstacles;

import hw01.contestants.Contestant;

public class Wall implements Obstacle {
    private final static int HEIGHT_DEFAULT = 150;

    private int height;

    public Wall() {
        this.height = HEIGHT_DEFAULT;
    }

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public Boolean isPassed(Contestant contestant) {
        System.out.println("\nThe wall height is "+height);
        if (contestant.jump()>height) {
            System.out.println("The contestant '"+contestant.name()
                    +"' jump over the wall");
            return true;
        }
        else {
            System.out.println("The contestant '"+contestant.name()
                    +"' couldn't jump over the wall");
            return false;
        }
    }
}
