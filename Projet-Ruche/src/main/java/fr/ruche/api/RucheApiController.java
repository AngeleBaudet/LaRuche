package fr.ruche.api;

import java.util.List;

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

import fr.ruche.dao.IDAORuche;
import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.RucheBadRequestException;
import fr.ruche.exception.RucheNotFoundException;
import fr.ruche.exception.UserNotFoundException;
import fr.ruche.model.Recolteur;
import fr.ruche.model.Ruche;
import fr.ruche.request.RucheRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ruche")
public class RucheApiController {

		@Autowired
		private IDAORuche daoRuche;
		
		@Autowired
		private IDAOUser daoUser;
		
		
		
		@GetMapping
		@JsonView(Views.Ruche.class)
		public	List<Ruche> findAll(){
			
			return this.daoRuche.findAll();
		}
	
		@GetMapping("/{id}")
		@JsonView(Views.Ruche.class)
		public Ruche findById(@PathVariable int id) {
			
			return this.daoRuche.findById(id).orElseThrow(RucheNotFoundException::new);
		}
		
		@GetMapping("/by-recolteur-id/{recolteur-id}")
		@JsonView(Views.Ruche.class)
		public List<Ruche> findRucheByRecolteur(@PathVariable int recolteurId){
			return this.daoRuche.findRucheByRecolteur(recolteurId);
		}
		
		@PostMapping
		@JsonView(Views.Ruche.class)
		public Ruche add(@Valid @RequestBody RucheRequest rucheRequest, BindingResult result) {
			if (result.hasErrors()) {
				throw new RucheBadRequestException();
			}
			
			Ruche ruche = new Ruche();
			Recolteur recolteur = new Recolteur();
			
			BeanUtils.copyProperties(rucheRequest, ruche);
			
			recolteur.setId(rucheRequest.getRecolteurId());
			ruche.setRecolteur(recolteur);
			
			return this.daoRuche.save(ruche);
			
		}
		
		@PutMapping("/{id}")
		@JsonView(Views.Ruche.class)
		public Ruche edit(@PathVariable int id, @Valid @RequestBody RucheRequest rucheRequest, BindingResult result) {
			if (result.hasErrors()) {
				throw new RucheBadRequestException();
			}
			Ruche ruche = this.daoRuche.findById(id).orElseThrow(RucheNotFoundException::new);
			BeanUtils.copyProperties(rucheRequest, ruche);
			
			ruche.setRecolteur(this.daoUser.findRecolteurById(rucheRequest.getRecolteurId()).orElseThrow(UserNotFoundException::new));
			
			return this.daoRuche.save(ruche);
		}
		
		@DeleteMapping("/{id}")
		public boolean deleteById(@PathVariable int id) {
			try {
				this.daoRuche.deleteById(id);
				return true;
			}
			
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
}
