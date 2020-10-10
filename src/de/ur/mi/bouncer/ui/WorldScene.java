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
        this.windowSize = AppConfiguration.getWindowSize();
        this.squareSize = AppConfiguration.getSquareSize();
    }

    public void draw(GraphicsContext graphics) {
        drawBackground(graphics);
        drawWorld(graphics);
        drawGrid(graphics);
    }

    private void drawBackground(GraphicsContext graphics) {
        graphics.drawBackground(Colors.WHITE);
    }

    private void drawGrid(GraphicsContext graphics) {
        for (int i = 1; i <= world.size(); i++) {
            graphics.drawLine(i * squareSize, 0, i * squareSize, windowSize, AppConfiguration.getLineWeight()+ AppConfiguration.getLineWeightOverflow(), AppConfiguration.DEFAULT_GRID_COLOR);
            graphics.drawLine(0, i * squareSize, windowSize, i * squareSize, AppConfiguration.getLineWeight() + AppConfiguration.getLineWeightOverflow(), AppConfiguration.DEFAULT_GRID_COLOR);
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
        int radiusEye = (int) (radius * AppConfiguration.DEFAULT_BOUNCER_EYE_SCALE_FACTOR);
        // Default eye position for east facing bouncer
        int xPosEye = xPos - radiusEye;
        int yPosEye = yPos - radiusEye;
        switch(bouncer.currentOrientation()) {
            case SOUTH:
            case WEST:
                xPosEye = xPos + radiusEye;
                break;
            case NORTH:
                xPosEye = xPos + radiusEye;
                yPosEye = yPos + radiusEye;
                break;
            default:
                break;
        }
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
        // Draw body
        graphics.drawCircle(xPos, yPos, radius / 2, AppConfiguration.DEFAULT_BOUNCER_COLOR);
        // Draw eye and mouth
        Color fieldColor = getColorAt(x, y);
        graphics.drawCircle(xPosEye, yPosEye, radiusEye / 2, fieldColor);
        graphics.drawPieArc(xPos, yPos, radius / 2, mouthStartAngle, mouthEndAngle, fieldColor);
    }

    private void drawColoredField(GraphicsContext graphics, int x, int y) {
        Color color = getColorAt(x, y);
        graphics.drawRect(x * squareSize - AppConfiguration.getLineWeight()/2, y * squareSize - AppConfiguration.getLineWeight()/2, squareSize-AppConfiguration.getLineWeight(), squareSize-AppConfiguration.getLineWeight(), color);
    }

    private void drawObstacle(GraphicsContext graphics, int x, int y) {
        graphics.drawRect(x * squareSize - AppConfiguration.getLineWeight()/2, y * squareSize - AppConfiguration.getLineWeight()/2, squareSize-AppConfiguration.getLineWeight(), squareSize-AppConfiguration.getLineWeight(), AppConfiguration.DEFAULT_BLOCK_COLOR);
    }

    private Color getColorAt(int x, int y) {
        Color color = AppConfiguration.DEFAULT_BLOCK_COLOR;
        switch (world.colorAt(x, y)) {
            case RED:
                color = AppConfiguration.RED_FIELD_COLOR;
                break;
            case GREEN:
                color = AppConfiguration.GREEN_FIELD_COLOR;
                break;
            case BLUE:
                color = AppConfiguration.BLUE_FIELD_COLOR;
                break;
            case WHITE:
                color = AppConfiguration.DEFAULT_FIELD_COLOR;
                break;
        }
        return color;
    }
}
