package de.ur.mi.bouncer.demo;

import de.ur.mi.bouncer.apps.BouncerApp;

public class BouncerDemo extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Obstacles");
        moveForward();
        climbOverObstacle();
        moveForward();
    }

    public void climbOverObstacle() {
        climbUp();
        climbUp();
        climbDown();
        climbDown();
    }

    public void climbDown() {
        bouncer.move();
        turnRight();
        bouncer.move();
        bouncer.turnLeft();
    }

    public void climbUp() {
        bouncer.turnLeft();
        bouncer.move();
        turnRight();
        bouncer.move();
    }

    public void moveForward() {
        while (bouncer.canMoveForward()) {
            bouncer.move();
        }
    }

    public void turnRight() {
        bouncer.turnLeft();
        bouncer.turnLeft();
        bouncer.turnLeft();
    }
}
