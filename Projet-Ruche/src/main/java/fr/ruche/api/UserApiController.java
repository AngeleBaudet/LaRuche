package fr.ruche.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.UserNotFoundException;
import fr.ruche.model.User;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserApiController {

	@Autowired
	private IDAOUser daoUser ;
	
	@GetMapping("/{idUser}")
	@JsonView(Views.Common.class)
	public User findById(@PathVariable int idUser) {
		return this.daoUser.findById(idUser).orElseThrow(UserNotFoundException::new);
	}
	
	@GetMapping
	@JsonView(Views.Common.class)
	public List<User> findAll() {
		return this.daoUser.findAll();
	}
	
	
}
