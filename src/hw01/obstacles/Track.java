package hw01.obstacles;

import hw01.contestants.Contestant;

public class Track implements Obstacle {

    private final static int LENGTH_DEFAULT = 100;

    private int length;

    public Track() {
        this.length = LENGTH_DEFAULT;
    }

    public Track(int length) {
        this.length = length;
    }

    @Override
    public Boolean isPassed(Contestant contestant) {
        System.out.println("\nThe track distance is "+length);
        if (contestant.run()>length) {
            System.out.println("The contestant '"+contestant.name()
                    +"' cover the distance");
            return true;
        }
        else {
            System.out.println("The contestant '"+contestant.name()
                    +"' couldn't cover the distance");
            return false;
        }
    }
}
