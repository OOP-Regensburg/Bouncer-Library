package de.ur.mi.bouncer.ui;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.Direction;
import de.ur.mi.bouncer.apps.AppConfiguration;
import de.ur.mi.bouncer.world.World;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;

public class WorldScene {
    private World world;
    private final Bouncer bouncer;
    private final int squareSize;
    private final int windowSize;

    public WorldScene(World world, Bouncer bouncer) {
        this.world = world;
        this.bouncer = bouncer;
        this.windowSize = AppConfiguration.DEFAULT_WINDOW_SIZE;
        this.squareSize = AppConfiguration.DEFAULT_SQUARE_SIZE;
    }

    public void draw(GraphicsContext graphics) {
        drawBackground(graphics);
        drawGrid(graphics);
        drawWorld(graphics);
    }

    private void drawBackground(GraphicsContext graphics) {
        graphics.drawBackground(Colors.WHITE);
    }

    private void drawGrid(GraphicsContext graphics) {
        for (int i = 1; i < world.size(); i++) {
            graphics.drawLine(i * squareSize, 0, i * squareSize, windowSize, Colors.BLACK);
            graphics.drawLine(0, i * squareSize, windowSize, i * squareSize, Colors.BLACK);
        }
    }

    private void drawWorld(GraphicsContext graphics) {
        for (int x = 0; x < world.size(); x++) {
            for (int y = 0; y < world.size(); y++) {
                if (world.hasObstacleAt(x, y)) {
                    drawObstacle(graphics, x, y);
                    continue;
                }
                drawColoredField(graphics, x, y);
                if (world.hasBouncerAt(x, y)) {
                    drawBouncer(graphics, x, y);
                }
            }
        }
    }

    private void drawBouncer(GraphicsContext graphics, int x, int y) {
        int xPos = x * squareSize + squareSize / 2;
        int yPos = y * squareSize + squareSize / 2;
        int radius = (int) (squareSize * AppConfiguration.DEFAULT_BOUNCER_SCALE_FACTOR);
        // Assume east facing bouncer as default
        int mouthStartAngle = -35;
        int mouthEndAngle = 70;
        if (bouncer.isFacingSouth()) {
            mouthStartAngle -= 90;
        }
        if (bouncer.isFacingWest()) {
            mouthStartAngle -= 180;
        }
        if (bouncer.isFacingNorth()) {
            mouthStartAngle -= 270;
        }
        graphics.drawCircle(xPos, yPos, radius / 2, Colors.RED);
        graphics.drawArc(xPos, yPos, radius / 2, mouthStartAngle, mouthEndAngle, Colors.RED);
    }

    private void drawColoredField(GraphicsContext graphics, int x, int y) {
        Color color = Colors.TRANSPARENT;
        switch (world.colorAt(x, y)) {
            case RED:
                color = new Color(233, 0, 0);
                break;
            case GREEN:
                color = new Color(0, 255, 0);
                break;
            case BLUE:
                color = new Color(0, 0, 255);
                break;
            case WHITE:
                return;
        }
        graphics.drawRect(x * squareSize, y * squareSize, squareSize, squareSize, color);
    }

    private void drawObstacle(GraphicsContext graphics, int x, int y) {
        graphics.drawRect(x * squareSize, y * squareSize, squareSize, squareSize, Colors.BLACK);
    }
}
