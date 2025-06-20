package iset.master.spring.model;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Stock {
	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 50)
	private String code;

	@Column(length = 50)
	private String adresse;

	@ManyToMany(mappedBy = "stocks", cascade = CascadeType.REMOVE)
	private Collection<Produit> produits = new ArrayList<Produit>();

	@OneToOne(cascade = { CascadeType.PERSIST })
	private Responsable responsable;

	@OneToMany(mappedBy = "stock", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	@JsonManagedReference
	private Collection<Employe> employes = new ArrayList<Employe>();

	@JsonIgnore
	public Collection<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Collection<Employe> employes) {
		this.employes = employes;
	}

	public String getCode() {
		return code;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Stock(String code, String adresse, Responsable responsable) {
		super();
		this.code = code;
		this.adresse = adresse;
		this.responsable = responsable;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}

	@Override
	public String toString() {
		return "Stock [id=" + id + ", code=" + code + ", adresse=" + adresse + "]";
	}

	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stock(String code, String adresse) {
		super();
		this.code = code;
		this.adresse = adresse;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
}