package hw01;

import hw01.contestants.Cat;
import hw01.contestants.Contestant;
import hw01.contestants.Human;
import hw01.contestants.Robot;
import hw01.obstacles.Obstacle;
import hw01.obstacles.Track;
import hw01.obstacles.Wall;

import java.util.Random;

public class HW1 {


    public static void main(String[] args) {
        Competition competition;

        // init
        competition =
                new HW1().init("Spring game");

        // action
        competition.start();

        // result
        competition.finish();
    }


    // new game
    private Competition init(String name) {

        Random rnd = new Random();

        Obstacle[] obstacles = {
                new Wall(),
                new Track(),
                new Wall(rnd.nextInt(200)),
                new Track(rnd.nextInt(500)),
                new Wall(rnd.nextInt(300)),
                new Track(rnd.nextInt(700))

        };


        Contestant[] contestants = {
                new Human("Boris"),
                new Robot("R2D2"),
                new Cat("Barsik"),
                new Human("Alena",rnd.nextInt(600),
                        rnd.nextInt(300)),
                new Human("Maxim",rnd.nextInt(1000),
                        rnd.nextInt(450)),
                new Robot("Anroid",rnd.nextInt(900),
                        rnd.nextInt(600)),
                new Cat("Keks",rnd.nextInt(800),
                        rnd.nextInt(900))

        };

        return new Competition(name, contestants, obstacles);
    }
}
