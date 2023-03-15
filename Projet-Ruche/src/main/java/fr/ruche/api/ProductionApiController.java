package fr.ruche.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.dao.IDAOProduction;
import fr.ruche.exception.ProductionBadRequestException;
import fr.ruche.exception.ProductionNotFoundException;
import fr.ruche.model.Production;
import fr.ruche.model.Recolteur;
import fr.ruche.model.Ruche;
import fr.ruche.request.ProductionRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/production")
public class ProductionApiController {

	@Autowired
	private IDAOProduction daoProduction;

	//--------------------CRUD------------------------
	//--------- TOUTES LES PRODUCTIONS ---------
	@GetMapping
	@JsonView(Views.Production.class)
	public List<Production> findAll() {
		return this.daoProduction.findAll();
	}
	//---- PRODUCTION EN FONCTION DE L'ID -------
	@GetMapping("/{id}")
	@JsonView(Views.Production.class)
	public Production findById(@PathVariable int id) {
		return this.daoProduction.findById(id).orElseThrow(ProductionNotFoundException::new);
	}
	//----------------- ADD ---------------------
	@PostMapping
	@JsonView(Views.Production.class)
	public Production add(@RequestBody @Valid ProductionRequest productionRequest,BindingResult result) {
		if(result.hasErrors()) {
			throw new ProductionBadRequestException();
		}

		Production production=new Production();

		BeanUtils.copyProperties(productionRequest, production);

		Recolteur recolteur = new Recolteur();
		recolteur.setId(productionRequest.getRecolteurId());

		Ruche ruche = new Ruche();
		ruche.setId(productionRequest.getRucheId());

		//		switch (productionRequest.getTypeProduit()) {
		//		case "miel": {production.setProduit(Produit.Miel);break;}
		//		case "pollen": {production.setProduit(Produit.Pollen);break;}
		//		case "cire": {production.setProduit(Produit.Cire);break;}
		//		case "gelee royale": {production.setProduit(Produit.Gelee_Royale);break;}
		//		default:
		//			throw new IllegalArgumentException("Unexpected value: " + productionRequest.getTypeProduit());
		//		}

		production.setRecolteur(recolteur);
		production.setRuche(ruche);
		production.setAnnee(LocalDate.now());

		return this.daoProduction.save(production);
	}
	//----------------- EDIT --------------------
	@PutMapping("/{id}")
	@JsonView(Views.Production.class)
	public Production edit(@PathVariable int id, @RequestBody @Valid ProductionRequest productionRequest,BindingResult result) {
		if(result.hasErrors()) {
			throw new ProductionBadRequestException();
		}
		Optional<Production> optProduction=this.daoProduction.findById(id);

		Production production=optProduction.orElseThrow(ProductionBadRequestException::new);

		BeanUtils.copyProperties(productionRequest, production);

		Recolteur recolteur = new Recolteur();
		recolteur.setId(productionRequest.getRecolteurId());

		Ruche ruche = new Ruche();
		ruche.setId(productionRequest.getRucheId());

		//		switch (productionRequest.getTypeProduit()) {
		//		case "miel": {production.setProduit(Produit.Miel);break;}
		//		case "pollen": {production.setProduit(Produit.Pollen);break;}
		//		case "cire": {production.setProduit(Produit.Cire);break;}
		//		case "gelee royale": {production.setProduit(Produit.Gelee_Royale);break;}
		//		default:
		//			throw new IllegalArgumentException("Unexpected value: " + productionRequest.getTypeProduit());
		//		}

		production.setRecolteur(recolteur);
		production.setRuche(ruche);
		production.setAnnee(LocalDate.now());

		return this.daoProduction.save(production);
	}
	//----------------- DELETE -------------------
	@DeleteMapping("/{id}")
	@JsonView(Views.Production.class)
	public boolean deleteById(@PathVariable int id) {
		try {
			this.daoProduction.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//--------------------- FIN CRUD -------------------

	//--------------- PROD BY RECOLTEUR ---------------
	@GetMapping("/by-recolteur/{recolteurId}")
	@JsonView(Views.Production.class)
	public List<Production> findProductionByRecolteur(@PathVariable int recolteurId){
		return this.daoProduction.findProductionByRecolteur(recolteurId);
	}
	
	
}
