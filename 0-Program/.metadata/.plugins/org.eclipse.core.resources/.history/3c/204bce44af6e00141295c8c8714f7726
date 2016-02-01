package application;

import java.util.ArrayList;

public class Main2 {
	public static void main(String args[]) {
		// EXEMPLE PLAN ---------------------------
		Noeud noeud1=new Noeud(1,100,100);
		Noeud noeud2=new Noeud(2,50,150);
		Noeud noeud3=new Noeud(3,150,150);
		Noeud noeud4=new Noeud(4,150,200);
		Noeud noeud5=new Noeud(5,50,200);
		Noeud noeud6=new Noeud(6,100,300);
		
		ArrayList<Noeud> noeuds=new ArrayList<>();
		noeuds.add(noeud1);noeuds.add(noeud2);noeuds.add(noeud3);noeuds.add(noeud4);
		noeuds.add(noeud5);noeuds.add(noeud6);
		
		
		Troncon troncon1=new Troncon("r1", 30, 40, noeud1, noeud2);
		Troncon troncon2=new Troncon("r1", 30, 300, noeud1, noeud3);
		Troncon troncon3=new Troncon("r1", 30, 30, noeud2, noeud3);
		Troncon troncon4=new Troncon("r1", 30, 40, noeud3, noeud4);
		Troncon troncon5=new Troncon("r1", 30, 40, noeud4, noeud2);
		Troncon troncon6=new Troncon("r1", 30, 40, noeud2, noeud5);
		Troncon troncon7=new Troncon("r1", 30, 40, noeud5, noeud4);
		Troncon troncon8=new Troncon("r1", 30, 40, noeud5, noeud6);
		Troncon troncon9=new Troncon("r1", 30, 40, noeud4, noeud6);
		Troncon troncon10=new Troncon("r1", 30, 40, noeud6, noeud1);
		
		Zone zone=new Zone("aymen.xml",noeuds);
		//--------------------------------------
		// LIVRAISON
		Livraison livraison1=new Livraison(1, noeud3);
		Livraison livraison2=new Livraison(2, noeud5);
		
		PlageHoraire plageHoraire= new PlageHoraire(new Temps("8:0:0"), new Temps("11:0:0"));
		plageHoraire.addLivraison(livraison1);
		plageHoraire.addLivraison(livraison2);
		
		ArrayList<PlageHoraire> listePlagesHoraire=new ArrayList<>();
		listePlagesHoraire.add(plageHoraire);
		//-------------------------------------------
		
		FeuilleDeRoute feuilleDeRoute=new FeuilleDeRoute(zone, 1, listePlagesHoraire);
		
		//--------------------------------------------------------------
		GraphLivraison graphLivraison = new GraphLivraison(feuilleDeRoute);
		System.out.println(graphLivraison.getMaxArcCost());
		
	}

}
