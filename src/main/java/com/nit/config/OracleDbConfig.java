package com.nit.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.nit.repository.prod",
					   entityManagerFactoryRef = "oraEmfb",
					   transactionManagerRef = "oraTxMgmr")
@EnableTransactionManagement
public class OracleDbConfig 
{
	@Bean(name = "oraDS")
	@ConfigurationProperties(prefix = "oracle.datasource")
	@Primary
	public DataSource createOraDs()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "oraEmfb")
	@Primary
	public LocalContainerEntityManagerFactoryBean createOraEMFB(EntityManagerFactoryBuilder builder)
	{
		// prepare oracle Hibernate properties
		Map<String, String> props = new HashMap<String, String>();
		props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.show_sql", "true");
		
		// crate and return LocalContainerEntityManagerFactoryBean class object 
		return builder.dataSource(createOraDs())
				.packages("com.nit.model.prod")
				.properties(props)
				.build();
	}
	
	@Bean(name = "oraTxMgmr")
	@Primary
	public JpaTransactionManager createOraTxMgmr(@Qualifier("oraEmfb")EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}
}
