package com.altia.inditex.prueba.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(entityManagerFactoryRef = "consultaEntityManager" ,
transactionManagerRef = "consultaTransactionManager", 
basePackages = {"com.altia.inditex.prueba"})
public class DataSourceConfig {
	

		@Value("${consulta.datasource.username}")
		private String user;
		
		@Value("${consulta.datasource.password}")
		private String password;
		
		@Value("${consulta.datasource.url}")
		private String url;
		@Value("${consulta.datasource.driver-class-name}")
		private String driver;
	
	    
	    @Bean(name = "ConsultaDS", destroyMethod = "close")
	    DataSource dataSourceConsulta() {
			return DataSourceBuilder.create()
					.type(HikariDataSource.class)
					.driverClassName(driver)
					.url(url)
					.username(user)
					.password(password)
					.build();
	    }
	    
	    @Bean("consultaEntityManager")
		EntityManagerFactory consultaEntityManager() {
			LocalContainerEntityManagerFactoryBean lem = 
					new LocalContainerEntityManagerFactoryBean();
			lem.setDataSource(dataSourceConsulta());
			lem.setPersistenceUnitName("CONSULTA");
			lem.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
			lem.setPersistenceProviderClass(HibernatePersistenceProvider.class);
			Properties jpaProperties = new Properties();
	        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        lem.setJpaProperties(jpaProperties);
			lem.afterPropertiesSet();
			return lem.getObject();
		}
	    
	    
		@Bean(name = "consultaTransactionManager")
		public PlatformTransactionManager transactionManager
			(@Qualifier("consultaEntityManager") EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
		}
			
	

}
