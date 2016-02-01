/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;



import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import application.Noeud;
import application.Troncon;
import application.Zone;

/**
 *
 * @author LENOVO
 */
public class Main extends Application {
	private static Stage primaryStage;
	private AnchorPane anchor;
	public static Zone zoneDeTest;
	public static final int hauteurFenetre=639;
	public static final int largeurFenetre=1028;
	@Override
	public void start(Stage primaryStage) {		
		try {
			BorderPane root = new BorderPane();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Fenetre.fxml"));
			Parent racine=(Parent)loader.load();
			FenetreController exp=loader.getController();
			primaryStage.setScene(new Scene(racine));
			primaryStage.setHeight(hauteurFenetre);
			primaryStage.setWidth(largeurFenetre);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Préparation des livraisons");
			primaryStage.getIcons().add(new Image(Main.class.getResource("transporter.png").toExternalForm()));
			primaryStage.show();
				
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void creerZoneDeTest(){
		//zoneDeTest=new Zone("juste pour test");
		Noeud noeud1=new Noeud(1,100,100);
		Noeud noeud2=new Noeud(2,50,150);
		Noeud noeud3=new Noeud(3,150,150);
		Noeud noeud4=new Noeud(4,150,200);
		Noeud noeud5=new Noeud(5,50,200);
		Noeud noeud6=new Noeud(6,100,300);
		
		ArrayList<Noeud> noeuds=new ArrayList<>();
		noeuds.add(noeud1);noeuds.add(noeud2);noeuds.add(noeud3);noeuds.add(noeud4);
		noeuds.add(noeud5);noeuds.add(noeud6);
		
		
		Troncon troncon1=new Troncon("Rue Abdelkader", 10, 40, noeud1, noeud2);
		Troncon troncon2=new Troncon("Rue moulay thani", 20, 300, noeud1, noeud3);
		Troncon troncon3=new Troncon("Rue Anes l'artiste", 30, 30, noeud2, noeud3);
		Troncon troncon4=new Troncon("Rue zina", 40, 40, noeud3, noeud4);
		Troncon troncon5=new Troncon("Rue la rue 1", 50, 40, noeud4, noeud2);
		Troncon troncon6=new Troncon("Rue la rue 1", 30, 40, noeud2, noeud5);
		Troncon troncon7=new Troncon("Rue la rue 1", 30, 40, noeud5, noeud4);
		Troncon troncon8=new Troncon("Rue la rue 1", 30, 40, noeud5, noeud6);
		Troncon troncon9=new Troncon("Rue la rue 1", 30, 40, noeud4, noeud6);
		Troncon troncon10=new Troncon("Rue la rue 1", 30, 40, noeud6, noeud1);
		
		zoneDeTest=new Zone("aymen.xml",noeuds);
				
	}

	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	
}
