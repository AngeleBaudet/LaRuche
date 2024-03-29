package fr.ruche.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.ClientBadRequestException;
import fr.ruche.exception.ClientNotFoundException;
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
import fr.ruche.response.AuthResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserApiController {

	@Autowired
	private IDAOUser daoUser ;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private PasswordEncoder passwordEncoder ; 
	
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
	
	//récupérer tous les gestionnaires
	@GetMapping("/gestionnaires")
	@JsonView(Views.User.class)
	public List<Gestionnaire> findAllGestionaires() {
		return this.daoUser.findAllByGestionnaire();
	}
		
	//findByLoginAndPassword
	//http://localhost:8080/Projet-Ruche/api/user/connexion?login=GeorgeRecolte&password=recolteur
	@GetMapping("/client")
	@JsonView(Views.User.class)
	public User findByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
		return this.daoUser.findByLoginAndPassword(login, password).orElseThrow(null);
	}
	
		
	//Afficher un client avec sa liste d'achat
	@GetMapping("/client/achats/{idUser}")
	@JsonView(Views.Client.class)
	public User findByIdWithAchats(@PathVariable int idUser) {
		return this.daoUser.findByIdWithAchat(idUser).orElseThrow(ClientNotFoundException::new);
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
	@PostMapping("/client")
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
	
	//--------------- RECOLTEUR BY ID ---------------
	@GetMapping("/recolteur/{recolteurId}")
	@JsonView(Views.User.class)
	public Recolteur findRecolteurById(@PathVariable int recolteurId){
		return this.daoUser.findRecolteurById(recolteurId).orElseThrow(UserNotFoundException::new);
	}
	
	//------------ Find gestionnaire by Id ------------
	@GetMapping("/gestionnaire/{id}")
	@JsonView(Views.User.class)
	public Gestionnaire findGestionnaireById(@PathVariable int id) {
		
		return this.daoUser.findGestionnaireById(id).orElseThrow(GestionnaireNotFoundException::new);
	}
	
	
	
	
	//------------ SECURITY ------------
//	@PostMapping("/connexion")
//	public boolean seConnecter(@RequestBody UserRequest userRequest) {
//
//		this.authenticationManager.authenticate(
//				   new UsernamePasswordAuthenticationToken(userRequest.getLogin(), userRequest.getPassword())
//				  );
//		
//		return true;
//	}
	
	@PostMapping("/connexion")
	public AuthResponse seConnecter(@RequestBody UserRequest userRequest) {

		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userRequest.getLogin(), userRequest.getPassword())
				);
		
		return new fr.ruche.response.AuthResponse(true, fr.ruche.config.jwt.JwtUtil.generate(authentication));
	}
	
	@PostMapping("/inscription")
	 public User inscription(@RequestBody UserRequest userRequest) {
	  User user = new Client();
	  
	  user.setLogin(userRequest.getLogin());
	  user.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
	  
	  return this.daoUser.save(user);   }
}
