package de.ur.mi.bouncer.apps;

import de.ur.mi.oop.colors.Color;

public class AppConfiguration {

    public static final int DEFAULT_WINDOW_SIZE = 750;
    public static final int GRID_SIZE = 15;
    public static final int DEFAULT_LINE_WEIGHT = 2;
	public static final int DEFAULT_LINE_WEIGHT_OVERFLOW = 1;
    public static final int DEFAULT_SQUARE_SIZE = DEFAULT_WINDOW_SIZE / GRID_SIZE;
    public static final int DEFAULT_FRAME_RATE = 5;
    public static final double DEFAULT_BOUNCER_SCALE_FACTOR = 0.8;
    public static final String FIELD_NAME_BOUNCER = "bouncer";
    public static final String FIELD_NAME_OBSTACLE = "obstacle";
    public static final String FIELD_NAME_COLOR = "fieldColor";
	public static final double DEFAULT_BOUNCER_EYE_SCALE_FACTOR = 0.2;
    public static final Color DEFAULT_BOUNCER_COLOR = new Color(220, 220, 20);
    public static final Color DEFAULT_GRID_COLOR = new Color(30, 30, 30);
    public static final Color DEFAULT_BLOCK_COLOR = new Color(30, 30, 30);
    public static final Color DEFAULT_FIELD_COLOR = new Color(255, 255, 255);
    public static final Color RED_FIELD_COLOR = new Color(220, 20, 20);
    public static final Color GREEN_FIELD_COLOR = new Color(20, 220, 20);
    public static final Color BLUE_FIELD_COLOR = new Color(20, 20, 220);

}
