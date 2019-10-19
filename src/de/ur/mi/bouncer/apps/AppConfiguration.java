package de.ur.mi.bouncer.apps;

import de.ur.mi.oop.colors.Color;

import java.awt.*;

public class AppConfiguration {

    private static final int DEFAULT_WINDOW_SIZE = 750;
    private static final double DEFAULT_SCREEN_TO_WINDOW_RATIO = 0.75;
    private static final int DEFAULT_GRID_SIZE = 15;
    private static final int DEFAULT_LINE_WEIGHT = 2;
    private static final int DEFAULT_LINE_WEIGHT_OVERFLOW = 1;

    public static final double DEFAULT_BOUNCER_SCALE_FACTOR = 0.8;
    public static final int DEFAULT_FRAME_RATE = 5;
    public static final String FIELD_NAME_BOUNCER = "bouncer";
    public static final String FIELD_NAME_OBSTACLE = "obstacle";
    public static final String FIELD_NAME_COLOR = "color";
	public static final double DEFAULT_BOUNCER_EYE_SCALE_FACTOR = 0.2;
    public static final Color DEFAULT_BOUNCER_COLOR = new Color(251, 208, 0);
    public static final Color DEFAULT_GRID_COLOR = new Color(0,0,0);
    public static final Color DEFAULT_BLOCK_COLOR = new Color(0,0,0);
    public static final Color DEFAULT_FIELD_COLOR = new Color(250, 250, 250);
    public static final Color RED_FIELD_COLOR = new Color(229, 37, 33);
    public static final Color GREEN_FIELD_COLOR = new Color(67, 176, 71);
    public static final Color BLUE_FIELD_COLOR = new Color(4, 156, 216);


    private static int windowSize = DEFAULT_WINDOW_SIZE;
    private static int gridSize = DEFAULT_GRID_SIZE;
    private static int lineWeight = DEFAULT_LINE_WEIGHT;
    private static int lineWeightOverflow = DEFAULT_LINE_WEIGHT_OVERFLOW;
    private static int squareSize = windowSize/gridSize;

    public static void setWindowSize(int newWindowSize) {
        windowSize = newWindowSize;
        squareSize = windowSize/gridSize;
    }

    public static void setGridSize(int newGridSize) {
        gridSize = newGridSize;
        squareSize = windowSize/gridSize;
    }

    public static int getWindowSize() {
        return windowSize;
    }

    public static int getGridSize() {
        return gridSize;
    }

    public static int getLineWeight() {
        return lineWeight;
    }

    public static int getLineWeightOverflow() {
        return lineWeightOverflow;
    }

    public static int getSquareSize() {
        return squareSize;
    }


    public static void init() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        AppConfiguration.setWindowSize((int) (screenSize.height * DEFAULT_SCREEN_TO_WINDOW_RATIO));

    }
}
