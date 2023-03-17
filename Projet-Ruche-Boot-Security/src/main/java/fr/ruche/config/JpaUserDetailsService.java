package fr.ruche.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.server.Client;
import fr.ruche.dao.IDAOUser;
import fr.ruche.model.Gestionnaire;
import fr.ruche.model.Recolteur;


@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IDAOUser daoUser;
	
	//attention, si on a deux user details providers activés, 
	//un ici et un dans security config, alors il considère qu'il y en a aucun
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		fr.ruche.model.User user = this.daoUser.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur not found"));
		
		UserBuilder userBuilder = User
				.withUsername(username)
				.password(user.getPassword()); //il faut avoir déclaré le passwordEncoder dans le securityConfig !!
		
		if (user.getClass()==Client.class) {
			userBuilder.roles("CLIENT");
		}
		else if (user.getClass()==Gestionnaire.class){
			userBuilder.roles("GESTIONNAIRE");	
		}
		else if (user.getClass()==Recolteur.class){
			userBuilder.roles("RECOLTEUR");	
		}
	
		return userBuilder.build();
	}

	
}
