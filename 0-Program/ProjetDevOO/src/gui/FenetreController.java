package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import application.Entrepot;
import application.FeuilleDeRoute;
import application.Livraison;
import application.LivraisonManager;
import application.Noeud;
import application.PlanManager;
import application.Troncon;
import application.Zone;



public class FenetreController implements Initializable{
	// a supprimer
	int indice=0;
	
	
	@FXML
    private Label infoDetail;
    
    
	@FXML
    private AnchorPane anchor;	
	
	@FXML
    private Pane paneDuPlan;
	
	@FXML
    private Label btnNouvelleFeuille;
	
	@FXML
    private Label btnCalculerItineraire;
	
	@FXML
    private Label btnAjouterLivraison;
		
	@FXML
    private Label btnSupprimerItineraire;
	
	@FXML
    private Label btnGenererFichier;
	
	@FXML
    private Label detailTitre1;
	
	@FXML
    private Label detailTitre2;
	
	@FXML
    private Label detailTitre3;
	
	@FXML
    private Label detailTitre4;
	
	@FXML
    private Label detailTitre5;
	
	@FXML
    private Label detailValeur1;
	
	@FXML
    private Label detailValeur2;
	
	@FXML
    private Label detailValeur3;
	
	@FXML
    private Label detailValeur4;
	
	@FXML
    private Label detailValeur5;
	
	@FXML
    private Label labellivraisonHorsHoraire;
	
	
	@FXML
    private Label remplaceurDeVideDetail;
	
	@FXML
    private Separator separator1;
	
	@FXML
    private Separator separator2;
	
	@FXML
    private Separator separator3;
	
	@FXML
    private Separator separator4;
	
	@FXML
    private Separator separator5;
	
	@FXML
    private MenuItem chargementNouvelleZone;
	
	@FXML
    private Label nomZone;
	
	@FXML
    private Label nomLivraison;
	
	@FXML
    private Label remplaceurDeVidePlan;
	
	@FXML
    private Pane lesCode;
	
	// les constantes:
	public static final int widthPlan=763;
	public static final int  heightPlan=513;
	public static final int marge=20;
	
	
	private ListenerOnMouseEnteredBouton listenerEnter=new ListenerOnMouseEnteredBouton();
	private ListenerOnMouseExitedBouton listenerExited=new ListenerOnMouseExitedBouton();
	private ListenerClickSurElementPourDetail listenerClickDuDetail=new ListenerClickSurElementPourDetail();
	
	private VueZone vueZone=null;
	private VueFeuilleDeRoute vueFeuilleDeRoute=null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
    	reglerLesListeners();   
    	
	}

	private void afficherZone(Zone zone) {
		// TODO Auto-generated method stub
		vueFeuilleDeRoute=null;
		vueZone=new VueZone(zone);		
		//ajouter les noeuds au paneDuPlan
		vueZone.ajouterNoeudsEtTronconsAVueZoneAvecListeners(listenerClickDuDetail);
		paneDuPlan.getChildren().add(vueZone);
		btnNouvelleFeuille.setDisable(false);
		btnGenererFichier.setDisable(true);
		btnCalculerItineraire.setDisable(true);
		btnAjouterLivraison.setDisable(true);
		btnSupprimerItineraire.setDisable(true);
	}

	public void reglerLesListeners(){
		// on devra factoriser les listeners pour optimiser cette partie
		btnNouvelleFeuille.setOnMouseEntered(listenerEnter);		
		btnNouvelleFeuille.setOnMouseExited(listenerExited);
		btnCalculerItineraire.setOnMouseEntered(listenerEnter);		
		btnCalculerItineraire.setOnMouseExited(listenerExited);
		btnAjouterLivraison.setOnMouseEntered(listenerEnter);		
		btnAjouterLivraison.setOnMouseExited(listenerExited);
		btnSupprimerItineraire.setOnMouseEntered(listenerEnter);		
		btnSupprimerItineraire.setOnMouseExited(listenerExited);
		btnGenererFichier.setOnMouseEntered(listenerEnter);		
		btnGenererFichier.setOnMouseExited(listenerExited);
		infoDetail.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				vueFeuilleDeRoute.afficherLeCheminDIndice(indice);
				indice++;
			}
		});
		
	}
	
	private class ListenerClickSurElementPourDetail implements EventHandler{

		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			masquerToutLesDetails();		
			// l'utilisateur a cliqué soit sur une VueNoeud soit sur une VueTroncon
			// soit sur le vide a la map (dans ce cas on affiche des infos générales
			Node noeudClicke=(Node) arg0.getSource();
			if (noeudClicke instanceof VueNoeud){
				/* c'est un noeud qui est cliqué
				 * trois possibilités:
				 * 1- une livraison
				 * 2- l'entrepot
				 * 3- un noeud simple				
				 */
				if (((VueNoeud) noeudClicke).getLivraison()!=null){
					afficherDetailLivraison(((VueNoeud) noeudClicke).getLivraison());								
				}
				else if (((VueNoeud) noeudClicke).getEntrepot()!=null){
					afficherDetailEntrepot(((VueNoeud) noeudClicke).getEntrepot());
				}
				else{
					// c'est le cas d'un noeud simple
					afficherDetailNoeud(((VueNoeud) noeudClicke).getNoeud());						
				}							
			}
			else if(noeudClicke instanceof VueTroncon)
			{
				// c'est un troncon qui est cliqué
				afficherDetailTroncon(((VueTroncon) noeudClicke).getTroncon());				
			}
		}

		private void afficherDetailEntrepot(Entrepot entrepot) {
			// TODO Auto-generated method stub
			detailTitre1.setText("Entrepôt");
			detailTitre1.setVisible(true);
			detailTitre2.setText("Longitude");
			detailTitre2.setVisible(true);
			detailTitre3.setText("Latitude");
			detailTitre3.setVisible(true);							
			detailValeur1.setText(""+entrepot.getNoeud().getId());
			detailValeur1.setVisible(true);
			detailValeur2.setText(""+entrepot.getNoeud().getX());
			detailValeur2.setVisible(true);
			detailValeur3.setText(""+entrepot.getNoeud().getY());
			detailValeur3.setVisible(true);
			if (entrepot.getHeureDepart()!=null) 
				{					
					detailTitre4.setText("Heure du départ");
					detailTitre4.setVisible(true);			
					detailValeur4.setText(""+entrepot.getHeureDepart().toString());
					detailValeur4.setVisible(true);
					separator4.setVisible(true);
				}
			
			
			if (entrepot.getHeureArrive()!=null) 
				{
					detailTitre5.setText("Heure d'arrivée");
					detailTitre5.setVisible(true);
					detailValeur5.setText(""+entrepot.getHeureArrive().toString());
					detailValeur5.setVisible(true);
					separator5.setVisible(true);
				}
			
			separator1.setVisible(true);
			separator2.setVisible(true);
			separator3.setVisible(true);
			
						
			
		}

		private void afficherDetailTroncon(Troncon troncon) {
			// TODO Auto-generated method stub
			detailTitre1.setText("Rue");
			detailTitre1.setVisible(true);
			detailTitre2.setText("Vitesse");
			detailTitre2.setVisible(true);
			detailTitre3.setText("Longueur");
			detailTitre3.setVisible(true);					
			detailValeur1.setText(""+troncon.getNomRue());
			detailValeur1.setVisible(true);
			detailValeur2.setText(""+troncon.getVitesse());
			detailValeur2.setVisible(true);
			detailValeur3.setText(""+troncon.getLongueur());
			detailValeur3.setVisible(true);
			separator1.setVisible(true);
			separator2.setVisible(true);
			separator3.setVisible(true);
			
		}

		private void afficherDetailNoeud(Noeud noeud) {
			// TODO Auto-generated method stub
			detailTitre1.setText("Noeud");
			detailTitre1.setVisible(true);
			detailTitre2.setText("Longitude");
			detailTitre2.setVisible(true);
			detailTitre3.setText("Latitude");
			detailTitre3.setVisible(true);																				
			detailValeur1.setText(""+noeud.getId());
			detailValeur1.setVisible(true);
			detailValeur2.setText(""+noeud.getX());
			detailValeur2.setVisible(true);
			detailValeur3.setText(""+noeud.getY());
			detailValeur3.setVisible(true);
			separator1.setVisible(true);
			separator2.setVisible(true);
			separator3.setVisible(true);
		
			
		}

		private void afficherDetailLivraison(Livraison livraison) {
			// TODO Auto-generated method stub
			detailTitre1.setText("Livraison");
			detailTitre1.setVisible(true);
			detailTitre2.setText("Coordonnées");
			detailTitre2.setVisible(true);			
			detailTitre3.setText("Plage horaire");
			detailTitre3.setVisible(true);										
			detailValeur1.setText(""+livraison.getIdLivraision());
			detailValeur1.setVisible(true);
			detailValeur2.setText(""+livraison.getNoeudLivraison().getX()+" - "+livraison.getNoeudLivraison().getY());
			detailValeur2.setVisible(true);
			detailValeur3.setText(""+livraison.getPlageHoraire().toString());
			detailValeur3.setVisible(true);
			if (livraison.getHeureArrive()!=null){								
				detailTitre4.setText("Heure d'arrivée");
				detailTitre4.setVisible(true);
				detailValeur4.setText(""+livraison.getHeureArrive().toString());
				detailValeur4.setVisible(true);
				separator4.setVisible(true);
			}
			if (livraison.getHeureDepart()!=null){
				detailTitre5.setText("Heure du départ");
				detailTitre5.setVisible(true);					
				detailValeur5.setText(""+livraison.getHeureDepart().toString());
				detailValeur5.setVisible(true);
				separator5.setVisible(true);
			}			
			separator1.setVisible(true);
			separator2.setVisible(true);
			separator3.setVisible(true);		
			if (!livraison.isInPlage())
			{
				labellivraisonHorsHoraire.setVisible(true);
			}		
		}


		
	}
	private void masquerToutLesDetails() {
		// TODO Auto-generated method stub
		remplaceurDeVideDetail.setVisible(false);
		detailTitre1.setVisible(false);
		detailTitre2.setVisible(false);
		detailTitre3.setVisible(false);
		detailTitre4.setVisible(false);
		detailTitre5.setVisible(false);
		detailValeur1.setVisible(false);
		detailValeur2.setVisible(false);
		detailValeur3.setVisible(false);
		detailValeur4.setVisible(false);
		detailValeur5.setVisible(false);
		separator1.setVisible(false);
		separator2.setVisible(false);
		separator3.setVisible(false);
		separator4.setVisible(false);
		separator5.setVisible(false);
	}
	@FXML
    private void chargerUneNouvelleZone(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Chargement d'une zone");
		fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers xml", "*.xml"));
		File selectedFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
		if (selectedFile!=null){
			Zone zone=PlanManager.chargerZone(selectedFile.getAbsolutePath());
			if (zone!=null){
				// on réinitialise les informations de la fenetre
				reinitialiserAffichage();
				remplaceurDeVidePlan.setVisible(false);
				afficherZone(zone);
				nomZone.setText("Zone "+selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf('.')));
			}
		}
		
	
		
		
	}

	private void reinitialiserAffichage() {
		// TODO Auto-generated method stub
		paneDuPlan.getChildren().clear();		
		if (vueZone!=null) vueZone.initialiser();		
		masquerToutLesDetails();
		remplaceurDeVideDetail.setVisible(true);
		
	}
	
	@FXML
	private void chargerFeuilleDeRoute(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Chargement d'une zone");
		fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Fichiers xml", "*.xml"));
		File selectedFile = fileChooser.showOpenDialog(Main.getPrimaryStage());
		if (selectedFile!=null){
			FeuilleDeRoute feuilleDeRoute=LivraisonManager.chargerLivraisons(selectedFile.getAbsolutePath(), vueZone.getZone());
			if (vueFeuilleDeRoute!=null) 
			{
				// réinitialiser la vue
				Zone zone=vueZone.getZone();
				reinitialiserAffichage();
				afficherZone(zone);
			}
			nomLivraison.setText("Livraison "+selectedFile.getName().substring(0, selectedFile.getName().lastIndexOf('.')));
			vueFeuilleDeRoute=new VueFeuilleDeRoute(vueZone, feuilleDeRoute);			
			
			btnNouvelleFeuille.setDisable(false);
			btnGenererFichier.setDisable(true);
			btnCalculerItineraire.setDisable(false);
			btnAjouterLivraison.setDisable(true);
			btnSupprimerItineraire.setDisable(true);
		}
	}
	@FXML
	private void calculerEtAfficherItineraire(){
		vueFeuilleDeRoute.calculerEtAfficherItineraire(vueZone);
		btnNouvelleFeuille.setDisable(false);
		btnGenererFichier.setDisable(false);
		btnCalculerItineraire.setDisable(true);
		btnAjouterLivraison.setDisable(false);
		btnSupprimerItineraire.setDisable(false);
	}
	
}
