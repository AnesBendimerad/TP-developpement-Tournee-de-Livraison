package gui;

import java.util.HashMap;
import java.util.Iterator;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import application.Noeud;
import application.Troncon;
import application.Zone;

public class VueZone extends Pane{

	
	private HashMap<Integer, VueNoeud> lesVuesNoeuds;
	private HashMap<String, VueTroncon> lesVuesTroncons;
	private Zone zone;
	
	public VueZone (Zone zone){
		super();
		lesVuesNoeuds=new HashMap<Integer, VueNoeud>();
		lesVuesTroncons=new HashMap<String, VueTroncon>();
		this.zone=zone;
		Iterator<Noeud> it=zone.getNoeuds().values().iterator();
		Noeud n;
		VueNoeud vueNoeud;
		while (it.hasNext()){
			n=it.next();
			vueNoeud=new VueNoeud(n,zone.getMaxX(),zone.getMaxY(),zone.getMinX(),zone.getMinY());
			lesVuesNoeuds.put(n.getId(),vueNoeud);
			//ajouter les vues troncons au hashmap
			ajouterLesVuesTronconsAuHashMap(vueNoeud);
		
		}
		
	}
	private void ajouterLesVuesTronconsAuHashMap(VueNoeud vueNoeud) {
		// TODO Auto-generated method stub
		Iterator<VueTroncon> it=vueNoeud.getListeVueTroncon().iterator();
		VueTroncon vueTroncon;
		while (it.hasNext()){
			vueTroncon=it.next();
			lesVuesTroncons.put(vueTroncon.getTroncon().getDepart().getId()+" "+vueTroncon.getTroncon().getDestination().getId(), vueTroncon);
		}
		
	}
	public void ajouterNoeudsEtTronconsAVueZoneAvecListeners(EventHandler listenerClickDuDetail) {
		// TODO Auto-generated method stub
		Iterator<VueNoeud> it=lesVuesNoeuds.values().iterator();
		VueNoeud v;
		while (it.hasNext())
		{
			v= it.next();
			getChildren().add(v);
			v.setOnMouseClicked(listenerClickDuDetail);
			afficherLesTronconsDuNoeud(v,listenerClickDuDetail);
		}
		
		it=lesVuesNoeuds.values().iterator();		
		while (it.hasNext())
		{
			(it.next()).toFront();
		}		
	}
	private void afficherLesTronconsDuNoeud(VueNoeud vueNoeud,EventHandler listenerClickDuDetail) {
		// TODO Auto-generated method stub
		Iterator<VueTroncon> it=vueNoeud.getListeVueTroncon().iterator();
		VueTroncon v;
		while (it.hasNext()){
			v=it.next();
			getChildren().add(v);
			v.setOnMouseClicked(listenerClickDuDetail);
		}
	}
	
	
	
	
	public void initialiser() {
		// TODO Auto-generated method stub
		getChildren().clear();
		lesVuesNoeuds=null;
		lesVuesTroncons=null;
		zone=null;
	}
	public HashMap<Integer, VueNoeud> getLesVuesNoeuds() {
		return lesVuesNoeuds;
	}
	public HashMap<String, VueTroncon> getLesVuesTroncons() {
		return lesVuesTroncons;
	}
	public Zone getZone() {
		return zone;
	}
	
}
