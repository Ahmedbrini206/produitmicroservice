package iset.master.spring.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import iset.master.spring.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
	
	Stock findByAdresse(String adresse); 

}
