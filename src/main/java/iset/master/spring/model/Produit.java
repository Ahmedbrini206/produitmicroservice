package iset.master.spring.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ManyToMany;

@Entity
public class Produit implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	@Column(length = 50)
	private String designation;

	private double prix;
	private int quantite;
	@Temporal(TemporalType.DATE)
	java.util.Date dateAchat;

	@ManyToOne
	@JsonIgnore
	private Categorie categorie;
	@ManyToMany(cascade = { CascadeType.PERSIST })
	@JsonIgnore
	private Collection<Stock> stocks = new ArrayList<Stock>();

	public Produit(String designation, double prix, int quantite, java.util.Date dateAchat, Categorie categorie) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
		this.categorie = categorie;
	}

	public Produit(String designation, double prix, int quantite, Date dateAchat) {
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
	}

	public Produit(String designation, double prix, int quantite, java.util.Date dateAchat) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.dateAchat = dateAchat;
	}

	@JsonIgnore
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public java.util.Date getDateAcha() {
		return this.dateAchat;
	}

	public void setDateAcha(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Produit() {
		super();
	}

	public Collection<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(Collection<Stock> stocks) {
		this.stocks = stocks;
	}

	public Produit(String designation, double prix, int quantite) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}

	public Produit(Long id, String designation, double prix, int quantite) {
		super();
		this.id = id;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite=" + quantite
				+ ", dateAchat=" + dateAchat + ", categorie=" + categorie + "]";
	}

}
