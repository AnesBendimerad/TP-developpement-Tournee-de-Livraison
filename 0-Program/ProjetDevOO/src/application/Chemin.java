package application;

import java.util.ArrayList;

/**
 * Classe repr�sentant un chemin : liste de tron�ons reliant deux noeuds
 * 
 * @author H4102
 *
 */
public class Chemin {
	/**
	 * noeud depart du chemin
	 */
	private Noeud depart;

	/**
	 * noeud destination du chemin
	 */
	private Noeud destination;

	/**
	 * le cout du chemin (dur�e n�cessaire pour parcourir le chemin en seconde)
	 */
	private double cout;

	/**
	 * la liste des tron�ons composant le chemin
	 */
	private ArrayList<Troncon> listeTroncons;

	/**
	 * Construit un chemin avec le noeud de depart, d'arriv� et le cout en temps
	 * (en seconde), la liste des tron�on est initialement vide (finalis�e par
	 * Djikstra)
	 * 
	 * @param depart
	 *            noeud de d�part du chemin
	 * @param destination
	 *            noeud destination du chemin
	 * @param cout
	 *            dur�e n�cessaire pour parcourir le chemin (en seconde)
	 */
	public Chemin(Noeud depart, Noeud destination, double cout) {
		super();
		this.depart = depart;
		this.destination = destination;
		this.listeTroncons = new ArrayList<>();
		this.cout = cout;
	}

	/**
	 * 
	 * @return noeud depart du chemin
	 */
	public Noeud getDepart() {
		return depart;
	}

	/**
	 * 
	 * @return noeud destination du chemin
	 */
	public Noeud getDestination() {
		return destination;
	}

	/**
	 * 
	 * @return le cout du chemin (dur�e n�cessaire pour parcourir le chemin en
	 *         seconde)
	 */
	public double getCout() {
		return cout;
	}

	/**
	 * 
	 * @return la liste des tron�ons composant le chemin
	 */
	public ArrayList<Troncon> getListeTroncon() {
		return listeTroncons;
	}

	/**
	 * 
	 * @param listeTroncons
	 *            la liste des tron�ons composant le chemin
	 */
	public void setListeTroncons(ArrayList<Troncon> listeTroncons) {
		this.listeTroncons = listeTroncons;
	}

	@Override
	public String toString() {
		String S = "Chemin [depart=" + depart + ", destination=" + destination
				+ ", Duree=" + Math.ceil(cout) + "]\r\n";
		for (Troncon troncon : this.getListeTroncon()) {
			S += "\t\t" + troncon.toString() + "\r\n";
		}
		return S;

	}

}
