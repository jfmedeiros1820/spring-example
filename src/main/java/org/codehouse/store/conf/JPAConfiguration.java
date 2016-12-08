package org.codehouse.store.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableTransactionManagement
public class JPAConfiguration {

  /**
   * Method to configure the entityManager for the connection
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource,
      Properties aditionalProperties) {

    LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

    factoryBean.setJpaVendorAdapter(vendorAdapter);

    factoryBean.setDataSource(dataSource);

    factoryBean.setJpaProperties(aditionalProperties);

    factoryBean.setPackagesToScan("org.codehouse.store.models");

    return factoryBean;
  }

  @Bean
  @Profile("dev")
  public Properties aditionalProperties() {
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.hbm2ddl.auto", "update");
    return props;
  }

  @Bean
  @Profile("dev")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUsername("root");
    dataSource.setPassword("");
    dataSource.setUrl("jdbc:mysql://localhost:3306/codehouse");
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    return dataSource;
  }

  /**
   * Method to specify how the transaction will be handled.
   * 
   * @author Joao Felipe de Medeiros Moreira
   */
  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }
}
