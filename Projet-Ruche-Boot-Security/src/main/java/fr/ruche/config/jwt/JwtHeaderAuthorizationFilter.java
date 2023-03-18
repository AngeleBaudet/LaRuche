package fr.ruche.config.jwt;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.qos.logback.core.net.server.Client;
import fr.ruche.dao.IDAOUser;
import fr.ruche.exception.UserBadRequestException;
import fr.ruche.model.Gestionnaire;
import fr.ruche.model.Recolteur;
import fr.ruche.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private IDAOUser daoUser;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		
		String token = null;

		if (header != null) {
			token = header.substring(7);
			
			String username = JwtUtil.getUsername(token).orElseThrow(UserBadRequestException::new);

			User user = this.daoUser.findByLogin(username).orElseThrow(UserBadRequestException::new);
			
			if (user.getClass()==Client.class) {
				//liste d'autorités
				java.util.List<GrantedAuthority> authorities = new ArrayList();
				authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));

				//la même chose que dans apiUtilisateur connexion
				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities); 

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

			else if (user.getClass()==Recolteur.class) {
				java.util.List<GrantedAuthority> authorities = new ArrayList();
				authorities.add(new SimpleGrantedAuthority("ROLE_RECOLTEUR"));

				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities); 

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
			else if (user.getClass()==Gestionnaire.class) {
				java.util.List<GrantedAuthority> authorities = new ArrayList();
				authorities.add(new SimpleGrantedAuthority("ROLE_GESTIONNAIRE"));

				Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities); 

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}

		}
		// sans cette méthode, la chaine s'arrete ici 
		filterChain.doFilter(request, response);

	}

}
