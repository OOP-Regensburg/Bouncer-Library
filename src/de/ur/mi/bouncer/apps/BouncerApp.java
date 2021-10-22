package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.ui.GraphicsContext;
import de.ur.mi.bouncer.ui.WorldScene;
import de.ur.mi.bouncer.world.BouncerChangedListener;
import de.ur.mi.bouncer.world.World;
import de.ur.mi.bouncer.world.loader.WorldLoader;
import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.events.KeyPressedEvent;
import de.ur.mi.oop.graphics.*;

public class BouncerApp extends GraphicsApp implements GraphicsContext, BouncerChangedListener {
    private WorldLoader worldLoader;
    protected Bouncer bouncer;
    private World world;
    private WorldScene worldScene;
    private BouncerThread bouncerThread;
    private boolean isPaused = false;

    public Bouncer createBouncer() {
        return new Bouncer();
    }

    @Override
    public void initialize() {
        AppConfiguration.init();
        ensureCorrectWindowSize();
        startBounceThread();
    }


    private void ensureCorrectWindowSize() {
        this.getConfig().setWidth(AppConfiguration.getPreferredWindowSize());
        this.getConfig().setHeight(AppConfiguration.getPreferredWindowSize());
    }

    private void startBounceThread() {
        bouncerThread = new BouncerThread(this);
        bouncerThread.start();
    }

    private void toggleBounceThread() {
        isPaused = !isPaused;
        if (!isPaused) {
            bouncerThread.interrupt();
        }
    }

    private void increaseSpeed() {
        AppConfiguration.increaseFrameRate();
    }

    private void decreaseSpeed() {
        AppConfiguration.decreaseFrameRate();
    }

    public void bounce() {

    }

    public final void loadMap(String mapName) {
        // Ensure that canvas will have correct size when configuration was changed manually
        ensureCorrectWindowSize();
        this.worldLoader = new WorldLoader();
        world = worldLoader.loadLocalMap(mapName);
        if (world == null) {
            this.world = World.emptyWorld();
        }
        bouncer = createBouncer();
        worldScene = new WorldScene(world, bouncer);
        bouncer.setBouncerListener(this);
        bouncer.placeInWorld(world);
    }

    @Override
    public void onBouncerChanged() {
        /*
         * This is a fine example of code that creeps into your system when you want to add
         * additional features to a system that was not designed very well.
         *
         * What I had done: To allow students maximal creativity when
         * writing Bouncer programs, client code and underlying rendering were
         * separated as much as possible. Or at least I thought so. Each bouncer program
         * starts from a single function (bounce), which runs in a separate thread.
         * After each single action, performed by bouncer through the student's commands
         * listed in the bounce method, this separate thread is asked to sleep for a certain time
         * to delay the next action. This is necessary, since each action directly effects
         * world state and rendering of this state (within another thread) is not
         * synchronized with bouncer. The world is rendered as fast as possible. Without the delay,
         * each new state would be rendered immediately, without the possibility to visually
         * distinguish single steps of the student's program.
         *
         * What I should have done: Make Bouncer wait after each action and ONLY
         * render the map when the world's state was actually changed.
         *
         * What I really should have done: Implement a working queue system where each state
         * created by bouncer's action is saved individually, allowing for bidirectional playback
         * with customizable playback speed.
         *
         * But we can now pause bouncer. At least for a maximum of 24 days.
         */
        try {
            if (isPaused) {
                Thread.sleep(Integer.MAX_VALUE);
            } else {
                Thread.sleep(1000 / AppConfiguration.getFrameRate());
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        super.onKeyPressed(event);
        switch (event.getKeyCode()) {
            case KeyPressedEvent.VK_SPACE -> toggleBounceThread();
            case KeyPressedEvent.VK_ADD -> increaseSpeed();
            case KeyPressedEvent.VK_MINUS -> decreaseSpeed();
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
        Background background = new Background((int) AppConfiguration.getWindowSize(), (int) AppConfiguration.getWindowSize());
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
        Rectangle rect = new Rectangle(x + AppConfiguration.getLineWeight(), y + AppConfiguration.getLineWeight(), width, height, color);
        rect.setBorder(color, 0);
        rect.draw();
    }

    @Override
    public void drawCircle(int x, int y, int radius, Color color) {
        Circle circle = new Circle(x + AppConfiguration.getLineWeight(), y + AppConfiguration.getLineWeight(), radius, color);
        circle.setBorder(color, 0);
        circle.draw();
    }

    @Override
    public void drawArc(int x, int y, int radius, int start, int end, Color color) {
        Arc arc = new Arc(x + AppConfiguration.getLineWeight(), y + AppConfiguration.getLineWeight() / 2, radius + AppConfiguration.getLineWeight() / 2, start, end, color, true);
        arc.setBorder(color, 0);
        arc.draw();
    }

    @Override
    public void drawPieArc(int x, int y, int radius, int start, int end, Color color) {
        Arc arc = new Arc(x + AppConfiguration.getLineWeight(), y + AppConfiguration.getLineWeight() / 2, radius + AppConfiguration.getLineWeight() / 2, start, end, color, false);
        arc.setBorder(color, 0);
        arc.draw();
    }
}
