package iset.master.spring.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import iset.master.spring.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
