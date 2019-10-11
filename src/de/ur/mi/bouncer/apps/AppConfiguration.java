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
    public static final String FIELD_NAME_COLOR = "color";
	public static final double DEFAULT_BOUNCER_EYE_SCALE_FACTOR = 0.2;
    public static final Color DEFAULT_BOUNCER_COLOR = new Color(251, 208, 0);
    public static final Color DEFAULT_GRID_COLOR = new Color(0,0,0);
    public static final Color DEFAULT_BLOCK_COLOR = new Color(0,0,0);
    public static final Color DEFAULT_FIELD_COLOR = new Color(250, 250, 250);
    public static final Color RED_FIELD_COLOR = new Color(229, 37, 33);
    public static final Color GREEN_FIELD_COLOR = new Color(67, 176, 71);
    public static final Color BLUE_FIELD_COLOR = new Color(4, 156, 216);

}
