package de.ur.mi.bouncer.demo;

import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.world.fields.FieldColor;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;


public class BouncerDemo extends BouncerApp {

    @Override
    public void bounce() {
        loadMap("Empty");
        bouncer.move();
        bouncer.paintField(FieldColor.BLUE);
        bouncer.move();
        bouncer.paintField(FieldColor.RED);
        bouncer.move();
        bouncer.paintField(FieldColor.GREEN);
        bouncer.move();
        bouncer.turnLeft();
        bouncer.turnLeft();
        while(bouncer.canMoveForward()) {
            bouncer.move();
            bouncer.clearFieldColor();
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch("BouncerDemo");
    }
}
