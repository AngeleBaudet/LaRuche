package fr.ruche;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.ruche.config.AppConfig;
import fr.ruche.dao.IDAOUser;
import fr.ruche.model.Recolteur;

public class Application {

	public static void main(String[] args) {
		
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	
	IDAOUser daoUser = context.getBean(IDAOUser.class);
	
	Recolteur u1 = (Recolteur) daoUser.findById(1).orElse(new Recolteur());
	System.out.println(u1);
	
	
	}
}
