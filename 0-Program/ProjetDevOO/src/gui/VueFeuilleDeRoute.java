package gui;

import java.util.Iterator;
import java.util.LinkedList;

import javafx.scene.paint.Color;
import tsp.SolutionState;
import application.Chemin;
import application.FeuilleDeRoute;
import application.Livraison;
import application.PlageHoraire;

public class VueFeuilleDeRoute {

	private VueZone vueZone;
	private String couleurLivraisonNormale="#009688";	
	private String couleurEntrepot="#673AB7";
	private FeuilleDeRoute feuilleDeRoute;
	private LinkedList<VueChemin> listeVuesChemins;
	private LinkedList<String> listeCouleursChemin;
	private int nbrCouleursChemin=10;
	
	public VueFeuilleDeRoute(VueZone vueZone, FeuilleDeRoute feuilleDeRoute){
		this.vueZone=vueZone;
		this.feuilleDeRoute=feuilleDeRoute;
		creerListeCouleursDesChemins();
		afficherEntrepotEtLivraisons();
	}

	private void afficherEntrepotEtLivraisons() {
		// TODO Auto-generated method stub
		VueNoeud vueNoeud=vueZone.getLesVuesNoeuds().get(feuilleDeRoute.getEntrepot().getNoeud().getId());
		vueNoeud.setEntrepot(feuilleDeRoute.getEntrepot());
		vueNoeud.setFill(Color.web(couleurEntrepot));
		vueNoeud.setRadius(6);
		Iterator<PlageHoraire> itPlageHoraire=feuilleDeRoute.getListePlagesHoraire().iterator();
		Iterator<Livraison> itLivraisons;
		Livraison livraison;
		while (itPlageHoraire.hasNext()){
			itLivraisons=itPlageHoraire.next().getListeLivraisons().iterator();
			while (itLivraisons.hasNext()){
				livraison=itLivraisons.next();
				vueNoeud=vueZone.getLesVuesNoeuds().get(livraison.getNoeudLivraison().getId());
				vueNoeud.setLivraison(livraison);
				vueNoeud.setFill(Color.web(couleurLivraisonNormale));
				vueNoeud.setRadius(6);
			}
		}
	}
	
	

	private void creerListeCouleursDesChemins() {
		// TODO Auto-generated method stub
		listeCouleursChemin=new LinkedList<String>();
		listeCouleursChemin.add("#F44336");
		listeCouleursChemin.add("#E91E63");
		listeCouleursChemin.add("#9C27B0");
		listeCouleursChemin.add("#673AB7");
		listeCouleursChemin.add("#03A9F4");
		listeCouleursChemin.add("#009688");
		listeCouleursChemin.add("#FF5722");
		listeCouleursChemin.add("#795548");	
		listeCouleursChemin.add("#212121");	
		listeCouleursChemin.add("#8BC34A");	
	}

	public SolutionState calculerEtAfficherItineraire(VueZone vueZone) {
		// TODO Auto-generated method stub
		SolutionState sol=feuilleDeRoute.calculerItineraire();
		// on construit les vueChemin 
		listeVuesChemins=new LinkedList<VueChemin>();
		Iterator<Chemin> itChemin=feuilleDeRoute.getItineraire().iterator();
		int indiceCouleur=0;
		
		while (itChemin.hasNext()){
			listeVuesChemins.add(new VueChemin(itChemin.next(),listeCouleursChemin.get(indiceCouleur),vueZone));
			indiceCouleur=(indiceCouleur+1)%nbrCouleursChemin;
		}
		
		
		// on affiche la liste de vues chemins
		afficherListeVuesChemin();
		
		
		return sol;
	}

	private void afficherListeVuesChemin() {
		// TODO Auto-generated method stub
		Iterator<VueChemin> it=listeVuesChemins.iterator();
		while (it.hasNext()){
			it.next().afficher();
		}
	}
	
	
	
	// a supprimer
	public void afficherLeCheminDIndice(int i){
		listeVuesChemins.get(i).afficher();
	}
	
}
