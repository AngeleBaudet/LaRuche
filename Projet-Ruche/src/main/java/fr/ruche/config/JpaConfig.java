package fr.ruche.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement // Permet d'activer AOP sur les annotations @Transactional
@EnableJpaRepositories("fr.ruche.dao")
@PropertySource("classpath:/env.development.local")
public class JpaConfig {
	@Autowired
	private Environment env; // Réceptacle de toutes les props de config
	
	// Bean pour la DataSource (DBCP2)
	@Bean // Attention à ne pas oublier cette annotation ... sinon Spring ne va pas la créer / gérer
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setMaxTotal(env.getProperty("db.total", Integer.class)); // nb connexions simultanées autorisées
		
		return dataSource;
	}
	
	// Bean pour l'EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		Properties props = new Properties();

		props.setProperty("hibernate.hbm2ddl.auto", "update");
		props.setProperty("hibernate.show_sql", "true");
		
		emf.setDataSource(dataSource); // On donne la DataSource
		emf.setPackagesToScan("fr.ruche.model"); // On donne la localisation des classes modèle
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); // On donne le fournisseur d'ORM
		emf.setJpaProperties(props); // On donne les propriétés JPA
		
		return emf;
	}
	
	
	// Bean pour le gestionnaire de transactions
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
