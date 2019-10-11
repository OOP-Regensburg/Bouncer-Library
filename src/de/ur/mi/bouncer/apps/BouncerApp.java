package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.ui.GraphicsContext;
import de.ur.mi.bouncer.ui.WorldScene;
import de.ur.mi.bouncer.world.BouncerChangedListener;
import de.ur.mi.bouncer.world.World;
import de.ur.mi.bouncer.world.WorldChangedListener;
import de.ur.mi.bouncer.world.loader.WorldLoader;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.*;

public class BouncerApp extends GraphicsApp implements GraphicsContext, BouncerChangedListener {
    private WorldLoader worldLoader;
    protected Bouncer bouncer;
    private World world;
    private WorldScene worldScene;
    public Bouncer createBouncer() {
        return new Bouncer();
    }


    @Override
    public void initialize() {
        this.getConfig().setWidth(AppConfiguration.DEFAULT_WINDOW_SIZE);
        this.getConfig().setHeight(AppConfiguration.DEFAULT_WINDOW_SIZE);
        startBounceThread();
    }


    private void startBounceThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bounce();
            }
        }).start();
    }

    public void bounce() {

    }

    public final void loadMap(String mapName) {
        this.worldLoader = new WorldLoader();
        world = worldLoader.loadLocalMap(mapName);
        if (world == null) {
            System.out.println("Could not find map: " + mapName + ". Loading empty map instead.");
            this.world = World.emptyWorld();
        }
        bouncer = createBouncer();
        worldScene = new WorldScene(world, bouncer);
        bouncer.setBouncerListener(this);
        bouncer.placeInWorld(world);
    }

    @Override
    public void onBouncerChanged() {
        try {
            Thread.sleep(1000 / AppConfiguration.DEFAULT_FRAME_RATE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        if (worldScene == null) {
            return;
        }
        worldScene.draw(this);
    }

    @Override
    public void drawBackground(Color color) {
        Background background = new Background();
        background.setColor(color);
        background.draw();
    }

    @Override
    public void drawLine(int startX, int startY, int endX, int endY, int weight, Color color) {
        Line line = new Line(startX, startY, endX, endY, color);
        line.setLineWidth(weight);
        line.draw();
    }

    @Override
    public void drawRect(int x, int y, int width, int height, Color color) {
        Rectangle rect = new Rectangle(x + AppConfiguration.DEFAULT_LINE_WEIGHT, y + AppConfiguration.DEFAULT_LINE_WEIGHT, width, height, color);
        rect.draw();
    }

    @Override
    public void drawCircle(int x, int y, int radius, Color color) {
        Circle circle = new Circle(x + AppConfiguration.DEFAULT_LINE_WEIGHT, y + AppConfiguration.DEFAULT_LINE_WEIGHT, radius, color);
        circle.draw();
    }

    @Override
    public void drawArc(int x, int y, int radius, int start, int end, Color color) {
        Arc arc = new Arc(x + AppConfiguration.DEFAULT_LINE_WEIGHT, y + AppConfiguration.DEFAULT_LINE_WEIGHT/2, radius + AppConfiguration.DEFAULT_LINE_WEIGHT/2, start, end, color);
        arc.draw();
    }
}
