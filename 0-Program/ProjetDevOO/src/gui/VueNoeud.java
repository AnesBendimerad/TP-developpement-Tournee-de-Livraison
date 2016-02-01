package gui;

import java.util.Iterator;
import java.util.LinkedList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import application.Entrepot;
import application.Livraison;
import application.Noeud;
import application.Troncon;

public class VueNoeud extends Circle{
	
	
	
	private Noeud noeud;
	private Entrepot entrepot=null;
	private Livraison livraison=null;
	private LinkedList<VueTroncon> listeVueTroncon;
	private String couleurNoeud="#BDBDBD";
	private int tailleSimpleNonSurvole=4;
	private int tailleSimpleSurvole=6;
	private int tailleStationNonSurvole=6;
	private int tailleStationSurvole=8;
	
	
	
	public VueNoeud(Noeud noeud, int maxX, int maxY, int minX, int minY){
		super();
		setRadius(tailleSimpleNonSurvole);
		setFill(Color.web(couleurNoeud));
		this.noeud=noeud;
		setCenterX(FenetreController.marge+(noeud.getX()-minX)*(FenetreController.widthPlan-FenetreController.marge*2)/(maxX-minX));
		setCenterY(FenetreController.marge+(noeud.getY()-minY)*(FenetreController.heightPlan-FenetreController.marge*2)/(maxY-minY));			
		setOnMouseEntered(new EventHandler() {		    
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (estStation()) VueNoeud.this.setRadius(tailleStationSurvole);
				else VueNoeud.this.setRadius(tailleSimpleSurvole);
			}
		});
		setOnMouseExited(new EventHandler() {		    
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (estStation()) VueNoeud.this.setRadius(tailleStationNonSurvole);
				else VueNoeud.this.setRadius(tailleSimpleNonSurvole);
			}
		});			
		creerListeTroncon(maxX,maxY,minX,minY);		
	}

	
	public Noeud getNoeud() {
		return noeud;
	}
	public Entrepot getEntrepot() {
		return entrepot;
	}


	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}


	public Livraison getLivraison() {
		return livraison;
	}


	public void setLivraison(Livraison livraison) {
		this.livraison = livraison;
	}
	public LinkedList<VueTroncon> getListeVueTroncon() {
		return listeVueTroncon;
	}


	private void creerListeTroncon(int maxX, int maxY, int minX, int minY) {
		// TODO Auto-generated method stub
		listeVueTroncon=new LinkedList<>();
		Iterator<Troncon> it=noeud.getListeTronconSortants().iterator();
		while (it.hasNext()){
			listeVueTroncon.add(new VueTroncon(it.next(),maxX,maxY,minX,minY));
		}
	}
	
	private boolean estStation(){
		return ((livraison!=null) || (entrepot!=null));
	}
	
}
