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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.nit.repository.promotions",
					   entityManagerFactoryRef = "mysqlEmfb",
					   transactionManagerRef = "mysqlTxMgmr")
@EnableTransactionManagement
public class MySqlDBConfig 
{
	@Bean(name = "mysqlDS")
	@ConfigurationProperties(prefix = "mysql.datasource")
	public DataSource createMySqlDs()
	{
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "mysqlEmfb")
	public LocalContainerEntityManagerFactoryBean createMySqlEMFB(EntityManagerFactoryBuilder builder)
	{
		// prepare oracle Hibernate properties
		Map<String, String> props = new HashMap<String, String>();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		props.put("hibernate.hbm2ddl.auto", "update");
		props.put("hibernate.show_sql", "true");
		
		// crate and return LocalContainerEntityManagerFactoryBean class object 
		return builder.dataSource(createMySqlDs())
				.packages("com.nit.model.promotions")
				.properties(props)
				.build();
	}
	
	@Bean(name = "mysqlTxMgmr")
	public JpaTransactionManager createMySqlTxMgmr(@Qualifier("mysqlEmfb")EntityManagerFactory factory)
	{
		return new JpaTransactionManager(factory);
	}
}