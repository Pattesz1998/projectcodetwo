package hu.inf.unideb.projectcodetwo;

import hu.inf.unideb.projectcodetwo.utils.PropertiesUtils;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import java.io.IOException;

import static org.springframework.boot.SpringApplication.*;

@SpringBootApplication
@EntityScan("hu.inf.unideb.projectcodetwo.model")
public class ProjectCodeTwoApplication {

	public static void main(String[] args) {
        run(ProjectCodeTwoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public DataSource dataSource() throws IOException {
		return DataSourceBuilder
				.create()
				.driverClassName(PropertiesUtils.getProperty("hibernate.connection.driver_class"))
				.username(PropertiesUtils.getProperty("hibernate.connection.username"))
				.password(PropertiesUtils.getProperty("hibernate.connection.password"))
				.url(PropertiesUtils.getProperty("hibernate.connection.url")).build();
	}
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
		bean.setDatabase(Database.H2);
		bean.setGenerateDdl(true);
		bean.setShowSql(true);
		return bean;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
																	   JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter);
		bean.setPackagesToScan("hu.inf.unideb.projectcodetwo.model");
		return bean;
	}
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
