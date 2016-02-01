package gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javafx.scene.paint.Color;
import application.Chemin;
import application.Troncon;

public class VueChemin {
	private VueNoeud vueNoeudDepart;
	private VueNoeud vueNoeudDestination;
	private LinkedList<VueTroncon> listeVuesTroncons;
	private Chemin chemin;
	private String couleurChemin;
	private String couleurLivraisonRetard="#F44336";
	
	public VueChemin(Chemin chemin, String couleurChemin, VueZone vueZone){
		this.chemin=chemin;
		this.couleurChemin=couleurChemin;	
		this.vueNoeudDepart=vueZone.getLesVuesNoeuds().get(chemin.getDepart().getId());
		this.vueNoeudDestination=vueZone.getLesVuesNoeuds().get(chemin.getDestination().getId());
		listeVuesTroncons=new LinkedList<VueTroncon>();
		Iterator<Troncon> itTroncons=chemin.getListeTroncon().iterator();
		Troncon troncon;
		while (itTroncons.hasNext()){
			troncon=itTroncons.next();
			listeVuesTroncons.add(vueZone.getLesVuesTroncons().get(troncon.getDepart().getId()+" "+troncon.getDestination().getId()));
		}
	}

	public void afficher() {
		// TODO Auto-generated method stub
		// on colorie les troncons
		Iterator<VueTroncon> it=listeVuesTroncons.iterator();
		VueTroncon vueTroncon;
		while (it.hasNext()){
			vueTroncon=it.next();
			vueTroncon.rendreChemin(couleurChemin);		
			if (vueNoeudDestination.getLivraison()!=null){
				if (!vueNoeudDestination.getLivraison().isInPlage()){
					vueNoeudDestination.setFill(Color.web(couleurLivraisonRetard));
				}
			}
		}
				
	}
}
