package de.ur.mi.bouncer.world.loader;
import java.io.File;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.ur.mi.bouncer.world.World;

public class WorldLoader {
	private static final String ASSET_FOLDER = "data/assets/";
	
	public World loadLocalMap(String mapName) {
		try {
			File mapFile = new File(ASSET_FOLDER + mapName + ".xml");
			Document doc = Jsoup.parse(mapFile, "UTF-8");
			return XmlWorldBuilder.fromXmlDocument(doc);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
