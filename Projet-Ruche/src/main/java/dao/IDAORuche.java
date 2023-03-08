package dao;

import java.util.List;

import fr.ruche.model.Ruche;

public interface IDAORuche extends IDAO<Ruche,Integer>{

	public List<Ruche> findRucheByNoussirage();
	public List<Ruche> findRucheByRecolteur(Integer id);
}
