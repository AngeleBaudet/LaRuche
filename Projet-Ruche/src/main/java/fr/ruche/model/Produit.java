package fr.ruche.model;

public enum Produit {
Miel(10), Pollen(12), Cire(15), Gelee_Royale(20);

	private double prix ;

	private Produit(double prix) {
		this.prix = prix;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	} 
	
	
}
