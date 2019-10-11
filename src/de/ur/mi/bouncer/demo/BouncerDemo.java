package de.ur.mi.bouncer.demo;

import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;


public class BouncerDemo extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Test");
        while(bouncer.canMoveForward()) {
            bouncer.move();
            bouncer.turnLeft();
        }
    }
}
