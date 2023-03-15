package fr.ruche.api;

import java.util.List;
import java.util.Optional;

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

import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.ClientBadRequestException;
import fr.ruche.exception.GestionnaireNotFoundException;
import fr.ruche.exception.UserBadRequestException;
import fr.ruche.exception.UserNotFoundException;
import fr.ruche.exception.WrongOrMissingTypeException;
import fr.ruche.model.Adresse;
import fr.ruche.model.Client;
import fr.ruche.model.Gestionnaire;
import fr.ruche.model.Recolteur;
import fr.ruche.model.User;
import fr.ruche.request.ClientRequest;
import fr.ruche.request.UserRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserApiController {

	@Autowired
	private IDAOUser daoUser ;
	
	//récupérer n'importe quel user par son id 
	@GetMapping("/{idUser}")
	@JsonView(Views.User.class)
	public User findById(@PathVariable int idUser) {
		return this.daoUser.findById(idUser).orElseThrow(UserNotFoundException::new);
	}
	
	//récupérer tous les users 
	@GetMapping
	@JsonView(Views.User.class)
	public List<User> findAll() {
		return this.daoUser.findAll();
	}
	
	//récupérer tous les clients 
	@GetMapping("/clients")
	@JsonView(Views.User.class)
	public List<Client> findAllClient() {
		return this.daoUser.findAllByClient();
	}
	
	//récupérer tous les récolteurs
	@GetMapping("/recolteurs")
	@JsonView(Views.User.class)
	public List<Recolteur> findAllRecolteurs() {
		return this.daoUser.findAllByRecolteur();
	}
	
	//ajouter un gestionnaire ou un récolteur, penser au type!
	@PostMapping
	@JsonView(Views.User.class)
	public User add(@RequestBody @Valid UserRequest userRequest, BindingResult results) {
		if (results.hasErrors()) {
			if (userRequest.getType()==null) {
				throw new WrongOrMissingTypeException();
			}
			else {
				throw new UserBadRequestException();
			}	
		}
		
		//ne fonctionne pas en switch car "user unreachable" en dehors du switch ? 
		User user; 
		if (userRequest.getType().equalsIgnoreCase("gestionnaire")) {
			user = new Gestionnaire(); 	
		}
		else if (userRequest.getType().equalsIgnoreCase("recolteur")) {
			user = new Recolteur(); 	
		}
		else { 
			throw new ClientBadRequestException();
		}

		BeanUtils.copyProperties(userRequest, user); 
		return this.daoUser.save(user); 
		
	}
	
	//ajouter un client
	@PostMapping("/clients")
	@JsonView(Views.User.class)
	public User addClient(@RequestBody @Valid ClientRequest clientRequest, BindingResult results) {
		if (results.hasErrors()) {
			throw new ClientBadRequestException();
		}
		
		Client client = new Client(); 
		BeanUtils.copyProperties(clientRequest, client); 
		Adresse adresse = new Adresse(clientRequest.getNumero(), clientRequest.getRue(), clientRequest.getVille(), clientRequest.getCp());
		client.setAdresse(adresse);
		return this.daoUser.save(client); 
		
	}
	
	//modifier un utilisateur, mais seulement login/password
	@PutMapping("/{idUser}")
	@JsonView(Views.User.class)
	public User modify(@RequestBody @Valid UserRequest userRequest, BindingResult results, @PathVariable int idUser) {
		if (results.hasErrors()) {
			throw new UserBadRequestException();
		}
		
		User user = daoUser.findById(idUser).orElseThrow(UserNotFoundException::new);
		BeanUtils.copyProperties(userRequest, user);
		
		return this.daoUser.save(user);
	}
	
	//modifier un client (avec les nom/prenom/adresse
	@PutMapping("/eshop/{idUser}")
	@JsonView(Views.User.class)
	public User modifyClient(@RequestBody @Valid ClientRequest clientRequest, BindingResult results, @PathVariable int idUser) {
		if (results.hasErrors()) {
			throw new ClientBadRequestException();
		}
		
		Client client = (Client) daoUser.findById(idUser).orElseThrow(UserNotFoundException::new);
		BeanUtils.copyProperties(clientRequest, client);
		Adresse adresse = new Adresse(clientRequest.getNumero(), clientRequest.getRue(), clientRequest.getVille(), clientRequest.getCp());
		client.setAdresse(adresse);
		return this.daoUser.save(client);
	}

	
	//supprimer un utilisateur
	@DeleteMapping("/{idUser}")
	public boolean deleteUser(@PathVariable int idUser) {
		try {
			this.daoUser.deleteById(idUser);
			return true;
			} catch (Exception e) { return false;} 
		}
	
	//------------ Fing gestionnaire by Id ------------
	
	@GetMapping("/gestionnaire/{id}")
	@JsonView(Views.User.class)
	public Gestionnaire findGestionnaireById(@PathVariable int id) {
		
		return this.daoUser.findGestionnaireById(id).orElseThrow(GestionnaireNotFoundException::new);
	}
	
}
