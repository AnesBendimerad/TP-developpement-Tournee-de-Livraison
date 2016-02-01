package application;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FeuilleDeRouteTest {
	
	static Zone zone;
	static FeuilleDeRoute feuilleDeRoute;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Noeud noeud1 = new Noeud(1, 100, 100);
		Noeud noeud2 = new Noeud(2, 50, 150);
		Noeud noeud3 = new Noeud(3, 150, 150);
		Noeud noeud4 = new Noeud(4, 150, 200);
		Noeud noeud5 = new Noeud(5, 50, 200);
		Noeud noeud6 = new Noeud(6, 100, 300);

		ArrayList<Noeud> noeuds = new ArrayList<>();
		noeuds.add(noeud1);
		noeuds.add(noeud2);
		noeuds.add(noeud3);
		noeuds.add(noeud4);
		noeuds.add(noeud5);
		noeuds.add(noeud6);

		new Troncon("r1", 30, 40, noeud1, noeud2);
		new Troncon("r1", 30, 300, noeud1, noeud3);
		new Troncon("r1", 30, 30, noeud2, noeud3);
		new Troncon("r1", 30, 40, noeud3, noeud4);
		new Troncon("r1", 30, 40, noeud4, noeud2);
		new Troncon("r1", 30, 40, noeud2, noeud5);
		new Troncon("r1", 30, 40, noeud5, noeud4);
		new Troncon("r1", 30, 40, noeud5, noeud6);
		new Troncon("r1", 30, 40, noeud4, noeud6);
		new Troncon("r1", 30, 40, noeud6, noeud1);

		zone = new Zone("H4102.xml", noeuds);
	}

	@Before
	public void setUp() throws Exception {
		Livraison livraison1 = new Livraison(1, zone.getNoeuds().get(3));
		Livraison livraison2 = new Livraison(2, zone.getNoeuds().get(5));
		Livraison livraison3 = new Livraison(1, zone.getNoeuds().get(4));

		PlageHoraire plageHoraire1 = new PlageHoraire(new Temps("8:0:0"),
				new Temps("11:0:0"));
		plageHoraire1.addLivraison(livraison1);
		plageHoraire1.addLivraison(livraison2);

		PlageHoraire plageHoraire2 = new PlageHoraire(new Temps("11:0:0"),
				new Temps("12:0:0"));
		plageHoraire2.addLivraison(livraison3);

		ArrayList<PlageHoraire> listePlagesHoraire = new ArrayList<>();
		listePlagesHoraire.add(plageHoraire1);
		listePlagesHoraire.add(plageHoraire2);

		feuilleDeRoute = new FeuilleDeRoute(zone, 1, listePlagesHoraire);
	}

	@Test
	public void testGetNombreLivraisons() {
		int valeur=feuilleDeRoute.getNombreLivraisons();
		assertTrue(String.valueOf(valeur),valeur==3);
	}

	@Test
	public void testCalculerItineraire() {
		feuilleDeRoute.calculerItineraire();
		assertEquals(feuilleDeRoute.getItineraire().size(), 4);
		assertEquals(feuilleDeRoute.getItineraire().get(0).getDepart(),feuilleDeRoute.getEntrepot().getNoeud());
		assertEquals(feuilleDeRoute.getItineraire().get(3).getDepart(),zone.getNoeuds().get(4));
		assertEquals(feuilleDeRoute.getItineraire().get(3).getDestination(),feuilleDeRoute.getEntrepot().getNoeud());
	
	}

	@Test
	public void testDjikstra() {
		Chemin chemin=feuilleDeRoute.Djikstra(zone.getNoeuds().get(1),zone.getNoeuds().get(3));
		assertEquals(chemin.getListeTroncon().size(), 2);
		double coutCalcule=chemin.getCout();
		double coutExpected=zone.getNoeuds().get(1).getListeTronconSortants().get(0).getCout()+zone.getNoeuds().get(2).getListeTronconSortants().get(0).getCout();
		assertTrue(coutCalcule==coutExpected);
	}

	@Test
	public void testMajItineraireSupressionLivraison() {
		feuilleDeRoute.calculerItineraire();
		boolean b=feuilleDeRoute.majItineraireSupressionLivraison(zone.getNoeuds().get(4));
		assertTrue(b);
		assertTrue(feuilleDeRoute.getItineraire().size()==3);
		assertTrue(feuilleDeRoute.getNombreLivraisons()==2);
		assertTrue(feuilleDeRoute.getLivraisonDeNoeud(zone.getNoeuds().get(4))==null);
	}

	@Test
	public void testMajItineraireAjoutLivraison() {
		feuilleDeRoute.calculerItineraire();
		assertTrue(feuilleDeRoute.getLivraisonDeNoeud(zone.getNoeuds().get(2))==null);
		boolean b=feuilleDeRoute.majItineraireAjoutLivraison(zone.getNoeuds().get(4),zone.getNoeuds().get(2));
		assertTrue(b);
		assertTrue(feuilleDeRoute.getItineraire().size()==5);
		assertTrue(feuilleDeRoute.getNombreLivraisons()==4);
		assertFalse(feuilleDeRoute.getLivraisonDeNoeud(zone.getNoeuds().get(2))==null);
	}

	@Test
	public void testGetCouplesNoeuds() {
		ArrayList<Noeud[]> al=feuilleDeRoute.getCouplesNoeuds();
		for (Noeud[] couple : al) {
			if (couple[0]==feuilleDeRoute.getEntrepot().getNoeud()) {
				assertFalse(couple[1]==feuilleDeRoute.getListePlagesHoraire().get(1).getListeLivraisons().get(0).getNoeudLivraison());
			}
			if (couple[1]==feuilleDeRoute.getEntrepot().getNoeud()) {
				assertTrue(couple[0]==feuilleDeRoute.getListePlagesHoraire().get(1).getListeLivraisons().get(0).getNoeudLivraison());
			}
		}
	}

}
