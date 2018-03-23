package com.tibia.helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tibia.model.Waypoint;

public class XMLHelper {
	private ConstantsHelper constants;
	
	public XMLHelper() {
		constants = new ConstantsHelper();
	}

	public List<Waypoint> getWaypointsList() {
		List<Waypoint> waypoints = new ArrayList<Waypoint>();
		
        File xmlFile = new File(constants.WAYPOINTS_LIST_XML_PATH);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Waypoint");

            for (int i = 0; i < nodeList.getLength(); i++) {
            	waypoints.add(getWaypoint(nodeList.item(i)));
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        
		return waypoints;
	}
	
	private Waypoint getWaypoint(Node node) {
		Waypoint waypoint = new Waypoint();
        
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            
            waypoint.setMarker(getTagValue("marker", element));
            waypoint.setDirection(getTagValue("direction", element));
            waypoint.setCheckpoint(getTagValue("checkpoint", element));
        }

        return waypoint;
    }
	
	private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        
        return node.getNodeValue();
    }
}
