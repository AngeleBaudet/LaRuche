package dao;

import java.util.List;

import model.Ruche;

public interface IDAORuche extends IDAO<Ruche,Integer>{

	public List<Ruche> findRucheByNoussirage();
}
