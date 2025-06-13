package iset.master.spring;

import java.util.Collection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import iset.master.spring.repositry.PersonneRepository;
import iset.master.spring.repositry.ResponsableRepository;
import iset.master.spring.repositry.StockRepository;
import iset.master.spring.model.*;

@SpringBootApplication
public class JpaSpringBootApplication1 {
	static StockRepository stockRepos;
	static ResponsableRepository responsableRepos;
	static PersonneRepository personneRepos;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JpaSpringBootApplication1.class, args);

		stockRepos = context.getBean(StockRepository.class);
		responsableRepos = context.getBean(ResponsableRepository.class);
		personneRepos = context.getBean(PersonneRepository.class);
		Responsable r1 = new Responsable("Ben Saleh", "Ali");
		Stock s1 = new Stock("1", "Tunis", r1);
		stockRepos.save(s1);
		Responsable r2 = new Responsable("Ben Ahmed", "Omar");
		Stock s2 = new Stock("2", "Sousse", r2);
		stockRepos.save(s2);
		Responsable r3 = new Responsable("Sallemi", "Samira");
		Stock s3 = new Stock("3", "Sfax", r3);
		stockRepos.save(s3);
		Responsable r4 = new Responsable("Zouari", "Zied");
		responsableRepos.save(r4);
		Stock s4 = new Stock("4", "Gabes");
		stockRepos.save(s4);

		s1.setResponsable(null);
		stockRepos.save(s1);
		s4.setResponsable(r1);
		stockRepos.save(s4);

		s1.setResponsable(r4);
		stockRepos.save(s1);

		// f) Afficher la liste des responsables (chaque responsable avec le stock qui
		// lui est associé s’il existe)
		System.out.println("***********************************************");
		System.out.println("Afficher tous les responsables avec leurs stocks");
		Collection<Responsable> lr = responsableRepos.findAll();
		for (Responsable r : lr) {
			System.out.println(r);
		}
		System.out.println("***************************************");
		Employe e1 = new Employe("Triki", "Samir", 1);
		// Récupérer une référence au stock de "Gabes"
		Stock sg = stockRepos.findByAdresse("Gabes");
		System.out.println(sg.toString());
		e1.setStock(sg);
		System.out.println(e1);
		personneRepos.save(e1);
		System.out.println("***************************************");
		if (sg != null) // référence sur le stock de « Gabes »
		{
			// récupérer tous les employé de Gabes
			Collection<Employe> le = sg.getEmployes();
			for (Employe e : le) {
				if (e != null) {

					// Détacher l’employé du stock
					System.out.println(e.toString());
					System.out.println("ppppppppppppppppppp");

					e.setStock(null);
					System.out.println("ppppppppppppppppppp");
					System.out.println(e.toString());
					// enregistrer dans la table "Employe"
					personneRepos.saveAndFlush(e);
					System.out.println("ppppppppppppppppppp");

				}

			}

			// vider la liste des employés
			le.clear();
			System.out.println("ppppppppppppppppppp11111");
			// supprimer le stock du "Gabes"
			// sg.getEmployes().clear();
			// stockRepos.save(sg);
			System.out.println("ppppppppppppppppppp222222222");
			// stockRepos.delete(sg);
			System.out.println("ppppppppppppppppppp33333333333");
		}
		// Récupérer le stock de "Tunis"
		Stock st = stockRepos.findByAdresse("Tunis");
		// Récupérer tous les employés non associés à un stock donné
		Collection<Employe> le = personneRepos.findAllByStockIsNull();
		if (le != null) {

			for (Employe e : le) {
				// Affecter l'employé au stock de "Tunis"
				e.setStock(st);
				personneRepos.saveAndFlush(e);
			}
		}

	}

}
