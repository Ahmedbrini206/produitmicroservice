package iset.master.spring;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.text.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import iset.master.spring.model.*;
import iset.master.spring.repositry.CategorieRepository;
import iset.master.spring.repositry.ProduitRepository;
import iset.master.spring.repositry.StockRepository;

@SpringBootApplication
public class JpaSpringBootApplication {

// Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance 
	static ProduitRepository produitRepos;

// Récupérer une implémentation de l'interface "CategorieRepository" par injection de dépendance 
	static CategorieRepository categorieRepos;

// Récupérer une implémentation de l'interface "StockRepository" par injection de dépendance 
	static StockRepository stockRepos;

	public static void main(String[] args) {

// référencer le contexte 
		ApplicationContext contexte = SpringApplication.run(JpaSpringBootApplication.class, args);
// Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance 
		produitRepos = contexte.getBean(ProduitRepository.class);

//Récupérer une implémentation de l'interface "CategorieRepository" par injection de dépendance 
		categorieRepos = contexte.getBean(CategorieRepository.class);

// Récupérer une implémentation de l'interface "StockRepository" par injection de dépendance 
		stockRepos = contexte.getBean(StockRepository.class);

		// créer deux catégories;
		Categorie cat1 = new Categorie("AL", "Alimentaire");
		Categorie cat2 = new Categorie("PL", "Plastique");
//Attacher les deux catégories à la BD (insertion) 
		categorieRepos.save(cat1);
		categorieRepos.save(cat2);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date1 = null;
		java.util.Date date2 = null;
		java.util.Date date3 = null;

		try {
			date1 = sdf.parse("2022-04-15");
			date2 = sdf.parse("2022-02-15");
			date3 = sdf.parse("2022-05-15");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// créer 3 produits
		Produit p1 = new Produit("Yahourt", 0.400, 20, date1, cat1);
		Produit p2 = new Produit("Chocolat", 2000.0, 5, date2, cat1);
		Produit p3 = new Produit("Panier", 1.200, 30, date3, cat2);

		// créer deux stocks;
		Stock s1 = new Stock("1", "Sfax");
		Stock s2 = new Stock("2", "Tunis");

		// Ajouter le produit p1 aux deux stocks s1 et s2
		p1.getStocks().add(s1);
		p1.getStocks().add(s2);

		// enregsitrement dans la BD en utilisant "produitRepository"
		produitRepos.save(p1);
		produitRepos.save(p2);
		produitRepos.save(p3);
		p2.getStocks().add(s1);
		p3.getStocks().add(s2);
		produitRepos.save(p2);
		produitRepos.save(p3);
		
		//produitRepos.deleteById(s1.getId());;

		// Afficher la liste des produits
		afficherTousLesProduits();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	static void afficherTousLesProduits() {
		System.out.println("***************************************");
		// Lister l'ensemble des produits
		System.out.println("Afficher tous les produits...");
		List<Produit> lp = produitRepos.findAll();
		for (Produit p : lp) {
			System.out.println(p);
		}
		System.out.println("***************************************");
	}

	static void afficherTousLesProduitsDeLaCategorie(Long id) {
		System.out.println("***************************************");
		// récupérer l'entité "Catégorie" ayant l'id en paramètres
		Categorie cD = categorieRepos.getOne(id);

		if (cD != null) {
			// Lister l'ensemble des produits
			System.out.println("Afficher tous les produits de la catégorie [" + id + "]");
			Collection<Produit> lC = cD.getProduits();
			for (Produit p : lC) {
				System.out.println(p);
			}
		} else {
			System.out.println("catégorie non existante...");
		}
		System.out.println("***************************************");

	}
}
