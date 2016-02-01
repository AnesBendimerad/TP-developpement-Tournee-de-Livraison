package application;

/**
 * Classe qui gère le temps
 * 
 * @author H4102
 * 
 *
 */
public class Temps {
	/**
	 * heure
	 */
	private int heure;
	/**
	 * minute
	 */
	private int minute;
	/**
	 * seconde
	 */
	private int seconde;

	/**
	 * construit l'objet temps
	 * 
	 * @param heure heure
	 * @param minute minute
	 * @param seconde seconde
	 */
	public Temps(int heure, int minute, int seconde) {
		super();
		this.heure = heure;
		this.minute = minute;
		this.seconde = seconde;
	}

	/**
	 * construit l'objet temps
	 * 
	 * @param stringHeure
	 *            de format heure:minute:seconde
	 */
	public Temps(String stringHeure) {
		super();
		this.heure = Integer.parseInt(stringHeure.substring(0,
				stringHeure.indexOf(":")));
		String tmp = stringHeure.substring(stringHeure.indexOf(":") + 1);
		this.minute = Integer.parseInt(tmp.substring(0, tmp.indexOf(":")));
		tmp = tmp.substring(tmp.indexOf(":") + 1);
		this.seconde = Integer.parseInt(tmp);
	}

	/**
	 * 
	 * @return la valeur du temps qui est heure*3600+minute*60+seconde
	 */
	public int getTempsValue() {
		return heure * 3600 + minute * 60 + seconde;
	}

	/**
	 * ajoute secondes au temps
	 * 
	 * @param secondes
	 *            nombre de seconde
	 */
	public void addTempsSeconde(int secondes) {
		int val = getTempsValue() + secondes;
		heure = val / 3600;
		minute = (val % 3600) / 60;
		seconde = val % 60;
	}

	/**
	 * soustrait secondes de temps
	 * 
	 * @param secondes
	 *            nombre de seconde
	 */
	public void subTempsSeconde(int secondes) {
		int val = getTempsValue() - secondes;
		heure = val / 3600;
		minute = (val % 3600) / 60;
		seconde = val % 60;
	}
	/**
	 * 
	 * @return heure:minute du temps
	 */
	public String heureMinute() {
		return heure+":"+minute;
	}

	@Override
	public String toString() {
		return heure + ":" + minute + ":" + seconde;
	}

	/**
	 * 
	 * @return objet temps identique a l'appelant
	 */
	public Temps copy() {
		return new Temps(this.toString());
	}

}
