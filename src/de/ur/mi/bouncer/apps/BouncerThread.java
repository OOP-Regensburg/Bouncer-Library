package de.ur.mi.bouncer.apps;

public class BouncerThread extends Thread {

    private final BouncerApp app;

    public BouncerThread(BouncerApp app) {
        this.app = app;
    }

    @Override
    public void run() {
        app.bounce();
    }

    public void kill() {
        this.interrupt();
    }
}
