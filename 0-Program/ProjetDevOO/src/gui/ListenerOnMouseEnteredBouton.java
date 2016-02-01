package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;





public class ListenerOnMouseEnteredBouton implements EventHandler{
	private String couleurBoutonSurvol="#29B6F6";
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
		
		Node n=(Node) arg0.getSource();
		n.setStyle("-fx-background-color: "+couleurBoutonSurvol+";");
	}
	
}
