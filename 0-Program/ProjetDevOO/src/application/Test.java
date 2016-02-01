package application;

import java.util.ArrayList;


/**
 * Classe de test
 * 
 * @author H4102
 *
 */
public class Test {

	public static void main(String args[]) {

		// EXEMPLE PLAN --------------------------- Noeud noeud1=new
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

		Zone zone = new Zone("aymen.xml", noeuds);

		// -------------------------------------- // LIVRAISON Livraison
		Livraison livraison1 = new Livraison(1, noeud3);
		Livraison livraison2 = new Livraison(2, noeud5);
		Livraison livraison3 = new Livraison(1,noeud4);
		
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

		FeuilleDeRoute feuilleDeRoute = new FeuilleDeRoute(zone, 1,
				listePlagesHoraire);

		// --------------------------------------------------------------
		feuilleDeRoute.calculerItineraire();
		System.out.println(feuilleDeRoute.getStringItiniraire());
		// ----------------------------------------------------------------
		
		Modificator M=new Modificator();
		AjoutLivraisonCommand ALM=new AjoutLivraisonCommand(feuilleDeRoute, noeud1, noeud2);
		System.out.println(M.storeAndExecute(ALM));
		System.out.println(M.undo());
		System.out.println(M.redo());
		
		System.out.println(feuilleDeRoute.getStringItiniraire());
		/*
		 * Zone zone = PlanManager.chargerZone("plan20x20.xml"); FeuilleDeRoute
		 * feuilleDeRoute = LivraisonManager.chargerLivraisons(
		 * "livraison20x20-2.xml", zone); System.out.println("State : " +
		 * feuilleDeRoute.calculerItineraire());
		 * feuilleDeRoute.editerFeuilleDeRoute("C:\\aymen.txt");*
		 */
	}

}