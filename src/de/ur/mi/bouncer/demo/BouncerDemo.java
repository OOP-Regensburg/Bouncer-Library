package de.ur.mi.bouncer.demo;

import de.ur.mi.bouncer.apps.AppConfiguration;
import de.ur.mi.bouncer.apps.BouncerApp;

public class BouncerDemo extends BouncerApp {

    @Override
    public void bounce() {
        AppConfiguration.setWindowSize(300);
        loadMap("Hurdles");
    }
}
