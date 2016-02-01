package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import application.Troncon;

public class VueTroncon extends Group {

	private Troncon troncon;
	private Polygon teteFleche=new Polygon();;
	private Line laLigne=new Line();
	private EventHandler enteredEvent;
	private EventHandler exitedEvent;
	private String couleurTroncon="#BDBDBD";
	private double dimFleche1=7;
	private double dimFleche2=7;
	private int strokeSimpleSurvole=3;
	private int strokeSimpleNonSurvole=1;
	private int strokeCheminSurvole=4;
	private int strokeCheminNonSurvole=2;
	private boolean estChemin=false;
	public VueTroncon(Troncon troncon, int maxX, int maxY, int minX, int minY) {
		super();
		this.troncon = troncon;		
		positionnerLaLigne(maxX,maxY,minX,minY);		
		ajouterSurvolListeners();			
		initialiserTeteFleche();
		colorerTroncon(couleurTroncon);
		getChildren().add(laLigne);
		getChildren().add(teteFleche);
	}

	private void ajouterSurvolListeners() {
		// TODO Auto-generated method stub
		enteredEvent=new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (estChemin)
					laLigne.setStrokeWidth(strokeCheminSurvole);
				else laLigne.setStrokeWidth(strokeSimpleSurvole);
			}
		};
		exitedEvent=new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (estChemin)
					laLigne.setStrokeWidth(strokeCheminNonSurvole);
				else laLigne.setStrokeWidth(strokeSimpleNonSurvole);
			}
		};
		setOnMouseEntered(enteredEvent);
		setOnMouseExited(exitedEvent);
	}

	
	
	
	public Troncon getTroncon() {
		return troncon;
	}

	private void positionnerLaLigne(int maxX, int maxY, int minX, int minY) {
		// TODO Auto-generated method stub
		laLigne.setStartX(FenetreController.marge + (troncon.getDepart().getX() - minX)
				* (FenetreController.widthPlan - FenetreController.marge * 2)
				/ (maxX - minX));
		laLigne.setStartY(FenetreController.marge + (troncon.getDepart().getY() - minY)
				* (FenetreController.heightPlan - FenetreController.marge * 2)
				/ (maxY - minY));
		laLigne.setEndX(FenetreController.marge
				+ (troncon.getDestination().getX() - minX)
				* (FenetreController.widthPlan - FenetreController.marge * 2)
				/ (maxX - minX));
		laLigne.setEndY(FenetreController.marge
				+ (troncon.getDestination().getY() - minY)
				* (FenetreController.heightPlan - FenetreController.marge * 2)
				/ (maxY - minY));
		
		
	}

	public void colorerTroncon(String couleur) {
		// TODO Auto-generated method stub
		laLigne.setStroke(Color.web(couleur));
		teteFleche.setFill(Color.web(couleur));
	}

	private void initialiserTeteFleche() {
		// TODO Auto-generated method stub
		
		teteFleche.getPoints().addAll(0.0,0.0,0.0,dimFleche1,dimFleche2,dimFleche1/2);						
		teteFleche.setLayoutX((laLigne.getEndX() + laLigne.getStartX()) / 2-dimFleche1/2);
		teteFleche.setLayoutY((laLigne.getEndY() + laLigne.getStartY()) / 2-dimFleche2/2);
		
		if (laLigne.getEndX()>=laLigne.getStartX()){
			if (laLigne.getEndY()>=laLigne.getStartY()){
				teteFleche.setRotate(teta()); //v
			}
			else{
				
				teteFleche.setRotate((360-teta())); //v
			}
		}
		else
		{
			if (laLigne.getEndY()>=laLigne.getStartY()){
				teteFleche.setRotate((180-teta())); 
			}
			else{
				teteFleche.setRotate((180+teta())); //v
			}
		}
		
		
	}

	// a rendre privée
	public double teta(){
		return Math.atan(((Math.abs(laLigne.getEndY() - laLigne.getStartY()))/(Math.abs(laLigne.getEndX() - laLigne.getStartX()))))*180/Math.PI;
	}
	public void setEstChemin(boolean b){
		estChemin=b;
	}

	public void rendreChemin(String couleurChemin) {
		// TODO Auto-generated method stub
		colorerTroncon(couleurChemin);
		estChemin=true;
		laLigne.setStrokeWidth(strokeCheminNonSurvole);
	}
	
}
