package iset.master.spring.repositry;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import iset.master.spring.model.Produit;
import jakarta.transaction.Transactional;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
	@Query("select p from Produit p where p.designation like %:x% ")
	public List<Produit> findByDesignation(@Param("x") String mc);

	@Query("SELECT p FROM Produit p WHERE p.designation LIKE %:x% AND p.prix = :y")
	public List<Produit> findByDesingationAndPrix(@Param("x") String des, @Param("y") long prix);

	@Query("update Produit p set p.designation =:designation where p.id = :id")
	@Modifying // indiquer qu’il s’agit d’une requête d’écriture dans la BD. Cette annotation
				// est définie dans le package : « org.springframework.data.jpa.repository »
	@Transactional // passer la requête dans une transaction (tout ou rien). Cette annotation est
					// définie dans le package : « jakarta.transaction »
	public int mettreAJourDesignation(@Param("designation") String designation, @Param("id") Long idProduit);

	List<Produit> findByPrixGreaterThan(double prixMin);

	List<Produit> findByOrderByPrixAsc();

	List<Produit> findByDateAchatAfter(LocalDate date);

}
