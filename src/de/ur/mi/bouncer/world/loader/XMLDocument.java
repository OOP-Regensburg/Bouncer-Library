package de.ur.mi.bouncer.world.loader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XMLDocument {

    private final File file;
    private Document doc;

    public XMLDocument(File file){
        this.file = file;
        try {
            load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        this.doc = dBuilder.parse(file);
    }

    public Element getElement(String tagName) {
        ArrayList<Element> elements = getElements(tagName);
        if(elements.size() > 0) {
            return elements.get(0);
        }
        return null;
    }

    public ArrayList<Element> getElements(String tagName) {
        NodeList list = doc.getElementsByTagName(tagName);
        ArrayList<Element> elements = new ArrayList<Element>();
        for(int i = 0; i < list.getLength(); i++) {
            elements.add((Element) list.item(i));
        }
        return elements;
    }
}
