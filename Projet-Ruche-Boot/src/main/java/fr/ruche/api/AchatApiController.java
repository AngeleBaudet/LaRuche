package fr.ruche.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.dao.IDAOAchat;
import fr.ruche.dao.IDAOProduction;
import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.AchatBadRequestException;
import fr.ruche.exception.AchatNotFoundException;
import fr.ruche.exception.ClientNotFoundException;
import fr.ruche.model.Achat;
import fr.ruche.request.AchatRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/achat")
@CrossOrigin("*")
public class AchatApiController {

	@Autowired
	private IDAOAchat daoAchat;

	@Autowired
	private IDAOUser daoClient;

	@Autowired
	private IDAOProduction daoProduction;

	//----------------------- CRUD -------------------------

	//----------- FIND ALL ------------
	@GetMapping
	@JsonView(Views.Achat.class)
	public List<Achat> findAll() {

		return this.daoAchat.findAll();
	}

	//----------- FIND BY ID ----------
	@GetMapping("/{id}")
	@JsonView(Views.Achat.class)
	public Achat findById(@PathVariable int id) {

		return this.daoAchat.findById(id).orElseThrow(AchatNotFoundException::new);

	}

	//----------- ADD -----------------
	@PostMapping
	@JsonView(Views.Achat.class)
	public Achat add(@Valid @RequestBody AchatRequest achatRequest, BindingResult result) {
		if (result.hasErrors()) {
			throw new AchatBadRequestException();
		}

		Achat achat = new Achat();

		BeanUtils.copyProperties(achatRequest, achat);

		achat.setClient(this.daoClient.findClientById(achatRequest.getIdClient()).orElseThrow(ClientNotFoundException::new));
		achat.setDateAchat(LocalDate.now());


		return this.daoAchat.save(achat);

	}

	//----------- EDIT ----------------
	@PutMapping("/{id}")
	@JsonView(Views.Achat.class)
	public Achat edit(@PathVariable int id, @RequestBody @Valid AchatRequest achatRequest, BindingResult result) {

		if (result.hasErrors()) {
			throw new AchatBadRequestException();
		}

		Achat achat = this.daoAchat.findById(id).orElseThrow(AchatNotFoundException::new);

		BeanUtils.copyProperties(achatRequest, achat);

		achat.setClient(this.daoClient.findClientById(achatRequest.getIdClient()).orElseThrow(ClientNotFoundException::new));
		achat.setDateAchat(LocalDate.now());


		return this.daoAchat.save(achat);

	}

	//----------- DELETE --------------
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
//			this.daoProduit.findById(id).orElseThrow(ProduitNotFoundException::new);
			
			if (!this.daoAchat.existsById(id)) {
				throw new AchatNotFoundException();
			}
			
			this.daoAchat.deleteById(id);
			
			return true;
		}
		
		catch (Exception e) {
			return false;
		}
	}
}
