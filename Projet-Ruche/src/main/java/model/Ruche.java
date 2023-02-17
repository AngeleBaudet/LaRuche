package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ruche {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private int cadre ; 
	private boolean limite ; 
	private Vulnerabilite vulnerabilite ; //optionnel
	// private boolean nourissage ; 
	private Recolteur recolteur ; //obligatoire
	private List<Production> productions ; //optionnel, on peut avoir plus qu'une production
	private static List<Ruche> listeRuche= new ArrayList();

	public Ruche() {
	}

	public Ruche(int cadre, boolean limite, Recolteur recolteur) {
		listeRuche.add(this);
		this.cadre = cadre;
		this.limite = limite;
		this.recolteur = recolteur;
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCadre() {
		return cadre;
	}

	public void setCadre(int cadre) {
		this.cadre = cadre;
	}

	public boolean isLimite() {
		return limite;
	}

	public void setLimite(boolean limite) {
		this.limite = limite;
	}

	public Vulnerabilite getVulnerabilite() {
		return vulnerabilite;
	}

	public void setVulnerabilite(Vulnerabilite vulnerabilite) {
		this.vulnerabilite = vulnerabilite;
	}

	public Recolteur getRecolteur() {
		return recolteur;
	}

	public void setRecolteur(Recolteur recolteur) {
		this.recolteur = recolteur;
	}


	public List<Production> getProductions() {
		return productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

	public static List<Ruche> getListeRuche() {
		return listeRuche;
	}

	
	
	public boolean nourrissage(Production productionPollen, Production productionMiel) {
		//determiner famine
		//eau pollen miel 
		//0,5L de sirop tous les 3-4jours en trois sessions
		//12kg de reserve pour l'hivers par ruche
		if (productionPollen.getQuantite()+productionMiel.getQuantite() < 12) {
			return true ;
		}
		else {
			return false ; 
		}
	}

	public Ruche division(Ruche rucheMere, Recolteur recolteur) {
		if (rucheMere.isLimite()) {
			System.out.println("vous avez une nouvelle ruche");
			return new Ruche(5, false, recolteur);
		}
		else {
			System.out.println("pas de nouvelle ruche");
			return rucheMere ; 
		}
	}

	@Override
	public String toString() {
		return "Ruche [id=" + id + ", cadre=" + cadre + ", limite=" + limite + ", vulnerabilite=" + vulnerabilite
				+ ", recolteur=" + recolteur + "]";
	}

	



}
