package iset.master.spring.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("respensable")
public class Responsable extends Personne{
	
	@OneToOne(mappedBy = "responsable")
	private Stock stock;

	 private double salaire;

	public Responsable() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Responsable(String nom, String prenom) {
		super(nom, prenom);
		// TODO Auto-generated constructor stub
	}

	public Responsable(String nom, String prenom, Stock stock, double salaire) {
		super(nom, prenom);
		this.stock = stock;
		this.salaire = salaire;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public String toString() {
		return "Responsable [stock=" + stock + ", salaire=" + salaire + "]";
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return super.getNom();
	}

	@Override
	public void setNom(String nom) {
		// TODO Auto-generated method stub
		super.setNom(nom);
	}

	

	


}
