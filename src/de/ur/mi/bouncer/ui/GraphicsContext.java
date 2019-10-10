package de.ur.mi.bouncer.ui;

import de.ur.mi.oop.colors.Color;

public interface GraphicsContext {
	void drawBackground(Color color);
	void drawLine(int startX, int startY, int endX, int endY, Color color);
	void drawRect(int x, int y, int width, int height, Color color);
	void drawCircle(int x, int y, int radius, Color color);
	void drawArc(int x, int y, int radius, int start, int end, Color color);
}
