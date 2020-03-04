package hw01;

import hw01.contestants.Contestant;
import hw01.obstacles.Obstacle;

public class Competition {

    private String name;
    private Contestant[] contestants;
    private Obstacle[] obstacles;

    public Competition(String name,
                       Contestant[] contestants, Obstacle[] obstacles)
    {
        this.name = name;
        this.contestants = contestants;
        this.obstacles = obstacles;
    }

    public void start(){

        System.out.printf("Our competition '%s' is starting now!\n",
                name);
        greeting();
        for (Contestant contestant: contestants) {
            if (passAllObstacles(contestant)) {
                System.out.println("The contestant '"
                        +contestant.name()
                        +"' has successfully completed the competition!");
            } else {
                System.out.println("The contestant '"
                        +contestant.name()
                        +"' has failed to complete the competition.");
            }
        }
    }

    public void finish() {

        System.out.printf("\nThe competition '%s' is over!\n",
                name);
    }

    private void greeting() {
        System.out.println("\nLet's greet our contestants:");
        for (Contestant contestant: contestants
             ) {
            System.out.println("\n"+contestant);
        }
    }

    private boolean passAllObstacles(Contestant contestant) {
        for (Obstacle obstacle: obstacles) {
            if (!obstacle.isPassed(contestant)) {
             return false;
            }
        }
        return true;
    }

}
