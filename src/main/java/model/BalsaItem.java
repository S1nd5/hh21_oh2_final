package model;

public class BalsaItem {
	
	private long id;
	private double tiheys;
	private double korkeus;
	private double leveys;
	private double paino;
	private double pituus;
	private String grain;
	
	public BalsaItem(long id, double tiheys, double korkeus, double leveys, double paino, double pituus, String grain) {
	     this.id = id;
	     this.tiheys = tiheys;
	     this.korkeus = korkeus;
	     this.leveys = leveys;
	     this.paino = paino;
	     this.pituus = pituus;
	     this.grain = grain;
	}
	
	/* Getters & Setters */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getTiheys() {
		return tiheys;
	}

	public void setTiheys(double tiheys) {
		this.tiheys = tiheys;
	}

	public double getKorkeus() {
		return korkeus;
	}

	public void setKorkeus(double korkeus) {
		this.korkeus = korkeus;
	}

	public double getLeveys() {
		return leveys;
	}

	public void setLeveys(double leveys) {
		this.leveys = leveys;
	}

	public double getPaino() {
		return paino;
	}

	public void setPaino(double paino) {
		this.paino = paino;
	}

	public double getPituus() {
		return pituus;
	}

	public void setPituus(double pituus) {
		this.pituus = pituus;
	}

	public String getGrain() {
		return grain;
	}

	public void setGrain(String grain) {
		this.grain = grain;
	}

	/* Equals and hashCode.. */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalsaItem other = (BalsaItem) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
