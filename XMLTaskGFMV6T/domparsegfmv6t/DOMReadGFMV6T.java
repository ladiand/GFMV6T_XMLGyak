package domparsegfmv6t;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



public class DOMReadGFMV6T {
	public static void main (String [] args) {
		
		File xml = new File("WithData.xml");
		//
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	
		try {
			//Dokumentum fa felépítése
			DocumentBuilder db = dbf.newDocumentBuilder();
			//xml beolvasás
			Document doc = db.parse(xml);
			//Gyökér beolvasása próbaként
			System.out.println(doc.getDocumentElement().getNodeName());
			
			//Minden adat kiírása
			//összeszedi az elemeket a fából
			NodeList nlUgyfel = doc.getElementsByTagName("ugyfel");
			NodeList nlRaktar = doc.getElementsByTagName("raktar");
			System.out.println("Ügyfelek száma: " + nlUgyfel.getLength());
			System.out.println("Raktárak száma: " + nlRaktar.getLength());
			
			//lista elemeire ciklus
			for (int i = 0; i < nlUgyfel.getLength(); i++) {
				Node n1 = nlUgyfel.item(i);
				
				if(n1.getNodeType() == Node.ELEMENT_NODE) {
					Element el1 = (Element) n1;
					
					//ügyfél adatainak kiírása
					System.out.println((i+1) + ". ügyfél neve: " + el1.getElementsByTagName("nev").item(0).getTextContent());
					System.out.println((i+1) + ". ügyfél születési ideje: " + el1.getElementsByTagName("szulido").item(0).getTextContent());
					System.out.println((i+1) + ". ügyfél telefonszáma: " + el1.getElementsByTagName("telefon").item(0).getTextContent());
					System.out.println((i+1) + ". ügyfél lakcíme: " + el1.getElementsByTagName("iranyitoszam").item(0).getTextContent() + ". "
							+ el1.getElementsByTagName("varos").item(0).getTextContent() 
								+ ", " + el1.getElementsByTagName("utca").item(0).getTextContent() + 
									" " + el1.getElementsByTagName("hazszam").item(0).getTextContent());
					
				}
			}
			
			//raktár adatainak kiírása
			for (int i = 0; i < nlRaktar.getLength(); i++) {
				Node n2 = nlRaktar.item(i);
				
				if(n2.getNodeType() == Node.ELEMENT_NODE) {
					Element el2 = (Element) n2;
					System.out.println((i+1) + ". raktár városa: " + el2.getElementsByTagName("telepules").item(0).getTextContent()
							+ " és irányítószáma: " + el2.getElementsByTagName("isz").item(0).getTextContent());
				}
			}
			//kivételkezelés
		} catch (Exception e) {
			System.out.println("Hiba történt: " +e.getMessage());
		}
	}
}