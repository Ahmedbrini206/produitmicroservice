package iset.master.spring.repositry;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import iset.master.spring.model.Employe;
import iset.master.spring.model.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

	public Collection<Employe> findAllByStockIsNull();

}
