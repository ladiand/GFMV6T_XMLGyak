package domparsegfmv6t;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyGFMV6T {

	public static void main(String[] args) {
		//fájl helyének megadása
		String path = "WithData.xml";
		File xml = new File (path);
		//
		DocumentBuilder db;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
            db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            // Módosítás
            updateElement(doc);

            // Törlés
            deleteElement(doc);

            // Element hozzáadása
            addElement(doc);

            // Módosított fájl létrehozása
            writeFile(doc);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void writeFile(Document doc)
    throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource ds = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("WithModifiedData.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(ds, result);
        System.out.println("XML módosítva");
    }

     //új elem hozzáadása a raktárhoz
    private static void addElement(Document doc) {
    	NodeList kolcsonzo = doc.getElementsByTagName("raktar");
        Element raktar = null;
        
        //minden darabra
        for (int i = 0; i < kolcsonzo.getLength(); i++) {
            raktar = (Element) kolcsonzo.item(i); 
            Element id = doc.createElement("kapacitas");
            id.appendChild(doc.createTextNode("500"));
            raktar.appendChild(id);
        }
    }

    //Egy elem törlése
    private static void deleteElement(Document doc) {
    	NodeList kolcsonzo = doc.getElementsByTagName("raktar");
        Element raktar = null;
        //minden elementre
        for (int i = 0; i < kolcsonzo.getLength(); i++) {
            raktar = (Element) kolcsonzo.item(i);
            Node isz = raktar.getElementsByTagName("isz").item(0);
            raktar.removeChild(isz);
        }
    }
    
    
  //Egy ügyfél nevének megváltoztatása (első elem használatával)
    private static void updateElement(Document doc) {
        NodeList kolcsonzo = doc.getElementsByTagName("ugyfel");
        Element ugyfel = null;
        // minden elementre
        for (int i = 0; i < kolcsonzo.getLength(); i++) {
            ugyfel = (Element) kolcsonzo.item(i);
            Node nev = ugyfel.getElementsByTagName("nev").item(0).getFirstChild();
            //megfelelő ügyfél megkeresése és adat megváltoztatása
            if (ugyfel.getAttribute("szigszam").contentEquals("654321CD")) {
            	nev.setNodeValue("Szabó Magda");
            }
            
        }
    }
}