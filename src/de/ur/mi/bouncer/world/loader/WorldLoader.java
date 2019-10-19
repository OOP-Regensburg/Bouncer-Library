package de.ur.mi.bouncer.world.loader;
import java.io.File;

import de.ur.mi.bouncer.world.World;

public class WorldLoader {
	private static final String ASSET_FOLDER = "data/assets/";
	
	public World loadLocalMap(String mapName) {
		try {
			File mapFile = new File(ASSET_FOLDER + mapName + ".xml");
			XMLDocument xmlDoc = new XMLDocument(mapFile);
			return XmlWorldBuilder.fromXmlDocument(xmlDoc);
		} catch (Exception e) {
			return null;
		}
	}
}
