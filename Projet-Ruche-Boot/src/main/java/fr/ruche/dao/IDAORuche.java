package fr.ruche.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.ruche.model.Ruche;

public interface IDAORuche extends JpaRepository<Ruche, Integer>{

	@Query("SELECT r from Ruche r join fetch r.productions p where p.produit in ('Miel','Pollen') Group by r having Sum(p.stock)>=12")
	public List<Ruche> findRucheByNourissage();
	
	@Query("SELECT r FROM Ruche r JOIN r.recolteur u WHERE u.id = ?1")
	public List<Ruche> findRucheByRecolteur(Integer id);

	public Optional<Ruche> findByIdAndRecolteurId(Integer id,Integer recolteurId);
}
