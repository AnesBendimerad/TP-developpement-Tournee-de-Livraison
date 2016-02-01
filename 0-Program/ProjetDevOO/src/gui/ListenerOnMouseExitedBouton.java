package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class ListenerOnMouseExitedBouton  implements EventHandler{
	private String couleurBoutonNormal="#039BE5";
	@Override
	public void handle(Event arg0) {
		// TODO Auto-generated method stub
		
		Node n=(Node) arg0.getSource();
		n.setStyle("-fx-background-color: "+couleurBoutonNormal+";");
	}
	
}