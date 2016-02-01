package application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Classe utilis�e pour cr�er une zone a partir d'un fichier XML
 * 
 * @author H4102
 *
 */
public class PlanManager {
	/**
	 * @param nomFichier
	 *            nom du fichier XML source de la demande de livraison
	 * @return la zone correspondante au fichier XML
	 */
	public static Zone chargerZone(String nomFichier) {

		HashMap<Integer, Noeud> noeuds = new HashMap<>();
		File xml = new File(nomFichier);

		if (xml != null) {
			try {
				// creation d'un constructeur de documents a l'aide d'une
				// fabrique
				DocumentBuilder constructeur = DocumentBuilderFactory
						.newInstance().newDocumentBuilder();
				// lecture du contenu d'un fichier XML avec DOM
				Document document = constructeur.parse(xml);
				Element racine = document.getDocumentElement();
				if (racine.getNodeName().equals("Reseau")) {
					String tag = "Noeud";
					NodeList nliste = racine.getElementsByTagName(tag);
					for (int i = 0; i < nliste.getLength(); i++) {
						Element noeudElement = (Element) nliste.item(i);
						int id = Integer.parseInt(noeudElement
								.getAttribute("id"));
						int x = Integer
								.parseInt(noeudElement.getAttribute("x"));
						int y = Integer
								.parseInt(noeudElement.getAttribute("y"));

						Noeud nouveauNoeud = new Noeud(id, x, y);
						noeuds.put(nouveauNoeud.getId(), nouveauNoeud);

					}
					for (int i = 0; i < nliste.getLength(); i++) {
						Element noeudElement = (Element) nliste.item(i);
						NodeList tliste = noeudElement
								.getElementsByTagName("LeTronconSortant");
						Noeud noeudDep = noeuds.get(Integer
								.parseInt(noeudElement.getAttribute("id")));
						for (int j = 0; j < tliste.getLength(); j++) {
							Element tronconElement = (Element) tliste.item(j);
							String nomRue = tronconElement
									.getAttribute("nomRue");
							double vitesse = Double.parseDouble(tronconElement
									.getAttribute("vitesse").replace(",", "."));
							double longueur = Double
									.parseDouble(tronconElement.getAttribute(
											"longueur").replace(",", "."));

							int idNoeudDest = Integer.parseInt(tronconElement
									.getAttribute("idNoeudDestination"));
							Noeud noeudDest = noeuds.get(idNoeudDest);
							new Troncon(nomRue, vitesse, longueur, noeudDep,
									noeudDest);

						}

					}
					return new Zone(nomFichier, noeuds);
				}
				// todo : traiter les erreurs
			} catch (ParserConfigurationException pce) {
				System.out.println("Erreur de configuration du parseur DOM");
				System.out
						.println("lors de l'appel a fabrique.newDocumentBuilder();");
			} catch (SAXException se) {
				System.out.println("Erreur lors du parsing du document");
				System.out.println("lors de l'appel a construteur.parse(xml)");
			} catch (IOException ioe) {
				System.out.println("Erreur d'entree/sortie");
				System.out.println("lors de l'appel a construteur.parse(xml)");
			}
		}

		return null;
	}

}