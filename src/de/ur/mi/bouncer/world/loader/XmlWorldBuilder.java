package de.ur.mi.bouncer.world.loader;

import de.ur.mi.bouncer.apps.AppConfiguration;
import de.ur.mi.bouncer.world.fields.FieldColor;

import de.ur.mi.bouncer.world.World;
import org.w3c.dom.Element;

import java.util.ArrayList;

public class XmlWorldBuilder {

	public static World fromXmlDocument(XMLDocument doc) {
		World world = World.emptyWorld();
		setupWorld(world, doc);
		return world;
	}

	private static void setupWorld(World world, XMLDocument doc) {
		setupObstacles(world, doc);
		setupColors(world, doc);
		setupBouncer(world, doc);
	}

	private static void setupBouncer(World world, XMLDocument doc) {
		Element bouncerEl = doc.getElement(AppConfiguration.FIELD_NAME_BOUNCER);
		if (bouncerEl == null) {
			return;
		}
		int x = Integer.valueOf(bouncerEl.getAttribute("x"));
		int y = Integer.valueOf(bouncerEl.getAttribute("y"));
		world.placeBouncerAt(x, y);
	}

	private static void setupColors(World world, XMLDocument doc) {
		ArrayList<Element> colorElements = doc.getElements(AppConfiguration.FIELD_NAME_COLOR);
		for (int i = 0; i < colorElements.size(); i++) {
			Element colorEl = colorElements.get(i);
			int x = Integer.valueOf(colorEl.getAttribute("x"));
			int y = Integer.valueOf(colorEl.getAttribute("y"));
			String value = colorEl.getAttribute("value");
			if ("RED".equals(value)) {
				world.paintFieldAt(x, y, FieldColor.RED);
			} else if ("GREEN".equals(value)) {
				world.paintFieldAt(x, y, FieldColor.GREEN);
			} else if ("BLUE".equals(value)) {
				world.paintFieldAt(x, y, FieldColor.BLUE);
			}
		}
	}

	private static void setupObstacles(World world, XMLDocument doc) {
		ArrayList<Element> obstacleElements = doc.getElements(AppConfiguration.FIELD_NAME_OBSTACLE);
		for (int i = 0; i < obstacleElements.size(); i++) {
			Element obstacleEl = obstacleElements.get(i);
			int x = Integer.valueOf(obstacleEl.getAttribute("x"));
			int y = Integer.valueOf(obstacleEl.getAttribute("y"));
			world.placeObstacleAt(x, y);
		}
	}
}
